package com.cyb.qutoes.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import com.cyb.qutoes.constant.QutoesContants;
import com.cyb.qutoes.dao.GrabDataDao;
import com.cyb.qutoes.utils.GrabEntity;
import com.cyb.utils.DateUtil;
import com.cyb.utils.PropertyUtil;
import com.cyb.utils.UUIDUtils;
@Repository("grabDataDao")
public class GrabDataDaoImpl implements GrabDataDao {
	@Resource
	JdbcTemplate jdbcTemplate; 
	
	@Override
	public void persisCodeInfor() {
		String delSql = "delete from stock ";//清除记录表
		this.jdbcTemplate.execute(delSql);
		BufferedReader reader = null;
		try {
			String savePath = QutoesContants.WEBPATH+PropertyUtil.getValueByKey("App", "codeFilePath")+File.separator+PropertyUtil.getValueByKey("App", "codeFileName"); 
			File codeFile = new File(savePath);
			if(!codeFile.exists()){
				GrabEntity.downLoadFromUrl(PropertyUtil.getValueByKey("App", "cfcenter"),savePath);
			}
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(codeFile),"GBK"));
			String content = "";
			List<String> sqls = new ArrayList<String>();
			Map<String,String> infor= null;
			while((content = reader.readLine())!=null){
				if(content.contains("<li><a")){
					System.out.println(content.replace("\"", ""));
					infor = GrabEntity.getCodeInfor(content.replace("\"", ""));
					if(infor.get("code")!=null||!"null".equals(infor.get("code"))||!"".equals(infor.get("code"))){
						sqls.add("insert into stock(id_,code_,exchange_,name_,oper_time) values ('"+UUIDUtils.getUUID()+"','"+infor.get("code")+"','"+infor.get("exchange")+"','"+infor.get("name")+"',sysdate)");
					}
				}
			}
			this.jdbcTemplate.batchUpdate(sqls.toArray(new String[sqls.size()]));
			this.jdbcTemplate.execute("delete from stock  where code_='null'");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(reader !=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				reader = null; 
			}
		}
	}

	@Override
	public List getAllCodeInfor() {
		List list = null;
		try {
			 list = this.jdbcTemplate.queryForList("select * from stock ");
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Map getNDaysUpStocks(int days,String condition){
		Map map = null;
		List<Map<String,Object>> ret=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> stocks = this.getAllCodeInfor();
		for(int i=0;i<stocks.size();i++){
			String code = stocks.get(i).get("EXCHANGE_").toString()+stocks.get(i).get("CODE_").toString();
			String sql="select nvl(min(close_-open_),0) as A "+
					"from ("+
				    " select record_date_, close_,open_ from closequtoes  where code_='"+code+"' and open_>0"
				            + condition  
				    		+ " order by record_date_ desc"+
				    " )  where rownum <="+days;
			int min = this.jdbcTemplate.queryForInt(sql);
			if(min>0){
				map = new HashMap<String, Object>();
				map.put("code",code);
				map.put("name",stocks.get(i).get("NAME_").toString());
				ret.add(map);
			}
			code="";
		}
		Map r = new HashMap<String,Object>();
		r.put("list",ret);
		return r;
	}
	public Map getNDaysDownStocks(int days,String condition){
		Map map = null;
		List<Map<String,Object>> ret=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> stocks = this.getAllCodeInfor();
		for(int i=0;i<stocks.size();i++){
			String code = stocks.get(i).get("EXCHANGE_").toString()+stocks.get(i).get("CODE_").toString();
			String sql="select nvl(max(close_-open_),0) as A "+
					"from ("+
				    " select record_date_, close_,open_ from closequtoes  where code_='"+code+"'  and open_>0 "
				    + condition  
				    + "order by record_date_ desc"+
				    " )  where rownum <="+days;
			int min = this.jdbcTemplate.queryForInt(sql);
			if(min<0){
				map = new HashMap<String, Object>();
				map.put("code",code);
				map.put("name",stocks.get(i).get("NAME_").toString());
				ret.add(map);
			}
			code="";
		}
		Map r = new HashMap<String,Object>();
		r.put("list",ret);
		return r;
	}
//	public void persisDayQutoes(String stockCode){
//		Long currDate = DateUtil.date2long8(new Date());
//		String dayQutoesUrl = PropertyUtil.getValueByKey("App", "dayqutoes");
//		this.jdbcTemplate.execute("delete from dayqutoes where record_date_ = "+currDate);
//		List<Map<String, Object>> stocks = this.jdbcTemplate.queryForList("select * from stock ");
//		String code = "";
//		String exchange = "";
//		String retData = "";
//		StringBuffer values = new StringBuffer("");
//		String insert = "insert into dayqutoes(id_,record_date_,name_,code_,open_,preclose_,high_,low_,price_,close_,day_,time_,oper_time) values " ;
//		List<String> sqls = new ArrayList<String>();
//		try {
//			for(Map stock :QutoesContants.STOCKMAP){
//				try {
//					code = stock.get("code_").toString();
//					exchange = stock.get("exchange_").toString();
//					retData = GrabEntity.grabJsonDataFromURL(dayQutoesUrl+exchange+code);
//					retData = retData.replace("\"", "");
//					String dataStr = retData.split("=")[1];
//					if(!";".equals(dataStr)){
//						String[] dataArr = dataStr.split(",");
//						values.append("(");
//						values.append("'"+UUIDUtils.getUUID()+"' ,");
//						values.append(""+currDate+",");
//						values.append("'"+dataArr[QutoesContants.NAME]+"' ,");
//						values.append("'"+exchange+code+"' ,");
//						values.append(""+dataArr[QutoesContants.OPEN]+" ,");
//						values.append(""+dataArr[QutoesContants.PRECLOSE]+" ,");
//						values.append(""+dataArr[QutoesContants.HIGH]+" ,");
//						values.append(""+dataArr[QutoesContants.LOW]+" ,");
//						values.append(""+dataArr[QutoesContants.PERICE]+" ,");
//						values.append(""+dataArr[QutoesContants.PERICE]+" ,");
//						values.append("'"+dataArr[QutoesContants.DAY]+"' ,");
//						values.append("'"+dataArr[QutoesContants.TIME]+"' ,");
//						values.append("sysdate");
//						values.append(")");
////						System.out.println(insert+values);
//						sqls.add(insert+values);
//					}
//					values = new StringBuffer("");
//				} catch (Exception e) {
//					System.out.println("数据获取失败："+dayQutoesUrl+exchange+code);
//				}
//			}
//			this.jdbcTemplate.batchUpdate(sqls.toArray(new String[sqls.size()]));
//		} catch (DataAccessException e) {
//			System.out.println("数据获取失败："+dayQutoesUrl+exchange+code);
//		}
//	}

	@Override
	public List getAllDayQutoes(String stockCode) {
		return null;
	}

	@Override
	public void persisDayQutoes(String stockCode) {
		// TODO Auto-generated method stub
		LocalSessionFactoryBean x;
	}
}
