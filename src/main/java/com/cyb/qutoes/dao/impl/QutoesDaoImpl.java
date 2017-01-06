package com.cyb.qutoes.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cyb.qutoes.constant.QutoesContants;
import com.cyb.qutoes.constant.SpringUtil;
import com.cyb.utils.DataUtils;
import com.cyb.utils.DateUtil;
import com.cyb.utils.QutoesUtils;
@Repository("qutoesDaoImpl")
public class QutoesDaoImpl {
	@Resource
	JdbcTemplate jdbcTemplate; 
	public Map getDpInfor(){
		String sql_zs =" select (price_-preCLOSE_ ) A1,(price_-preCLOSE_ )/preclose_*100  as A,price_ as price,code_,name_,time_min from (SELECT * FROM MINUTEQUTOESCURRDAY where code_ in('sz399001','sh000001') order by time_MIN  desc) where rownum<=2";
	    List<Map<String,Object>> list_zhzs = this.jdbcTemplate.queryForList(sql_zs);
	    String shzsprice = "0";
	    String szzsprice = "0";
	    String shzsA = "0";
	    String szzsA = "0";
	    String shzsA1 = "0";
	    String szzsA1 = "0";
	    String colorsh="green";
	    String colorsz="green";
	    if(list_zhzs!=null&&list_zhzs.size()>0){
	    	for(Map zhzs :list_zhzs){
	    		String code_ = zhzs.get("CODE_").toString();
	    		if("sz399001".equals(code_)){
	    			szzsprice =zhzs.get("PRICE").toString();
	    			szzsA = DataUtils.roundStr(Double.valueOf(zhzs.get("A").toString()), 2);
	    			szzsA1 = DataUtils.roundStr(Double.valueOf(zhzs.get("A1").toString()), 2);
	    			if(Double.valueOf(szzsA)>0){
	    				szzsA="+"+szzsA;
	    				szzsA1="+"+szzsA1;
	    				colorsz="red";
	    			}
	    		}else if("sh000001".equals(code_)){
	    			shzsprice =zhzs.get("PRICE").toString();
	    			shzsA = DataUtils.roundStr(Double.valueOf(zhzs.get("A").toString()), 2);
	    			shzsA1 = DataUtils.roundStr(Double.valueOf(zhzs.get("A1").toString()), 2);
	    			if(Double.valueOf(shzsA)>0){
	    				shzsA="+"+shzsA;
	    				shzsA1="+"+shzsA1;
	    				colorsh="red";
	    			}
	    		}
	    	}
	    }
	    Map map = new HashMap();
	    map.put("shzsprice", shzsprice);
	    map.put("szzsprice", szzsprice);
	    map.put("shzsA1", shzsA1);
	    map.put("szzsA1", szzsA1);
	    map.put("shzsA", shzsA);
	    map.put("szzsA", szzsA);
	    map.put("colorsh", colorsh);
	    map.put("colorsz", colorsz);
	    return map;
	}
	public Map getMinQutoes(String code){
	   Map map = new HashMap();
	   double amplitude  =  0;
	   String jyrsql = "(select  curjyr from STOCKCONFIG where alias='LASTESTDAY' )";
    	JdbcTemplate dao = (JdbcTemplate) SpringUtil.getBean("jdbcTemplate");
    	String sql = "SELECT substr(oper_time,0,16)  as time_ ,price_,nvl(PRECLOSE_,0) as PRECLOSE_,HIGH_,LOW_,OPEN_,time_min FROM MINUTEQUTOESCURRDAY where record_date_ = "+jyrsql+" and code_='"+code.trim()+"' order by time_ asc";
        List<Map<String,Object>> list = dao.queryForList(sql);
        List<String> y = new ArrayList<String>();
        List<String> x_ = new ArrayList<String>();
        List<String> x = QutoesUtils.spiltTimeList("", 1);//获取所有的坐标
        Map<String,String> allMap = QutoesUtils.spiltTimeMap("", 1);//获取所有的坐标
        double max = 0.0;double min = 0.0;double preclose=0.0;
        double curPrice=0.0;double open = 0.0;
        if(list!=null&&list.size()>0){
        	open = Double.valueOf( list.get(0).get("OPEN_").toString());
        	preclose = Double.valueOf( list.get(0).get("PRECLOSE_").toString());
        	for(Map tmp:list){
        		curPrice = Double.valueOf(tmp.get("PRICE_").toString());
        		String time_ = tmp.get("TIME_MIN").toString();
        		String res = DateUtil.format(time_+"00", "yyyy-MM-dd HH:mm:ss");
        		allMap.put(res.substring(11, res.length()-3), tmp.get("PRICE_").toString());
        		x_.add(res.substring(11, res.length()-3));
        	}
        	max = Double.valueOf(list.get(list.size()-1).get("HIGH_").toString());
        	min = Double.valueOf(list.get(list.size()-1).get("LOW_").toString());
        	if(x!=null&&x.size()>0){
        		map.put("x", JSONArray.fromObject(x));
        	}else{
        		map.put("x", JSONArray.fromObject("[]"));
        	}
        	if(y!=null&&y.size()>0){
        		map.put("y", JSONArray.fromObject(y));
        	}else{
        		map.put("y", JSONArray.fromObject("[]"));
        	}
        	map.put("realmax", max); map.put("realmin", min);
        	Map<String,Double> res = QutoesUtils.calSection(max, min, preclose);
        	max = res.get("max");
        	min = res.get("min");
        }
	    if(preclose!=0){
	    	amplitude = 100*(curPrice-preclose)/preclose;  
	    }
	    map.put("gap", DataUtils.roundStr(curPrice-preclose,2));
	    map.put("price", DataUtils.roundStr(curPrice,2));
	    map.put("A", DataUtils.roundStr(amplitude, 2));//涨跌幅   
	    map.put("name", QutoesContants.STOCKMAP.get(code));  
        map.put("max", max);
        map.put("close", preclose);
        map.put("min", min);
        map.put("code", code);
        map.put("open", open);
        map.put("start","09:30");
        map.put("end","15:30");
        map.put("time", DateUtil.format(new Date(), "yyyy年MM月dd日HH时mm分ss秒"));
        return map;
        		
	}
  }
