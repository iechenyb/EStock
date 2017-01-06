package com.cyb.phone.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyb.controller.QutoesController;
import com.cyb.phone.contants.EContants;
import com.cyb.qutoes.constant.QutoesContants;
import com.cyb.qutoes.constant.SpringUtil;
import com.cyb.qutoes.vo.KPoint;
import com.cyb.utils.DataUtils;
import com.cyb.utils.DateUtil;
import com.cyb.utils.QutoesUtils;
@Service("phoneStockDao")
public class PhoneStockDaoImpl {
	static Log log = LogFactory.getLog(QutoesController.class);
	@Resource(name="jdbcTemplate")
	JdbcTemplate jdbcTemplate; 
	@Resource(name="sessionFactory")
	SessionFactory sessionFactory;
	 public Map myConern(String username){
		 String sql= " select * from REALTIMEQUTOES  where   "+
				  "  code_ in (SELECT code_ FROM USERCONCERN where  userid='"+username+"') order by VOLUME desc";
		 System.out.println("手机端自选股："+sql);
		 List<Map<String, Object>> data = this.jdbcTemplate.queryForList(sql);
		 try {
			if(data!=null&&data.size()>0){
				 int count = data.size();
				 for(int i=0;i<count;i++){
					 double curPrice = Double.valueOf(data.get(i).get("PRICE_").toString());
					 double prePrice = Double.valueOf(data.get(i).get("PRECLOSE_").toString());
					 double openPrice = Double.valueOf(data.get(i).get("OPEN_").toString());
					 double number = Double.valueOf(data.get(i).get("VOLUME").toString());
					 double money = Double.valueOf(data.get(i).get("TURNVOLUME").toString());
					 data.get(i).put("VOLUME",DataUtils.roundDouble(number/10000,2));
					 data.get(i).put("TURNVOLUME",DataUtils.roundDouble(money/10000,2));
					 String code = data.get(i).get("CODE_").toString();
					 data.get(i).put("CODEONLY", code.substring(2, code.length()));
					 data.get(i).put("A1", DataUtils.roundDouble(curPrice-prePrice,2));
					 if(openPrice==0){//ͣ
						 data.get(i).put("A", "--");
						 data.get(i).put("color", "gray");
					 }else{
						 data.get(i).put("A", DataUtils.roundDouble((curPrice-prePrice)*100/prePrice,2)+"%");
						 if((curPrice-prePrice) > 0){
							 data.get(i).put("color", "red");
						 }else if ((curPrice-prePrice) < 0){
						     data.get(i).put("color", "green");
						 }else{
							 data.get(i).put("A", "--");
							 data.get(i).put("color", "gray");
						 }
					 }
				 }
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		 Map map = new HashMap();
		 map.put("data", data);
		 JSONArray ret = JSONArray.fromObject(map);
		 return map;
   }
	 @RequestMapping({"/zdfx"})
	  public Map zdfx(String type){
		  String sql="select * from REALTIMEQUTOES where  inDUSTRY <> 'zhzs'  ";
		  boolean SQLAPP = false;
		  if("sz".equals(type)){//上涨
			  sql = sql+" and (price_-preclose_)>0 and open_>0 order by VOLUME desc";
			  SQLAPP = true;
		  }else if("xd".equals(type)){//下跌
			  SQLAPP = true;
			  sql = sql+" and (price_-preclose_)<0 and open_> 0 order by VOLUME desc";
		  }else if("tp".equals(type)){//停牌
	    	  sql = sql+" and open_=0 order by VOLUME desc";
	    	  SQLAPP = true;
		  }else {
			  sql = sql+" and zdbz="+type+" and open_> 0 order by VOLUME desc";
		  }
	  	  log.info("sql= "+sql);
		  List<Map<String, Object>> data = this.jdbcTemplate.queryForList(sql);
		  if(data!=null&&data.size()>0){
				 for(int i=0;i<data.size();i++){
					 double curPrice = Double.valueOf(data.get(i).get("PRICE_").toString());
					 double prePrice = Double.valueOf(data.get(i).get("PRECLOSE_").toString());
					 double openPrice = Double.valueOf(data.get(i).get("OPEN_").toString());
					 double number = Double.valueOf(data.get(i).get("VOLUME").toString());
					 double money = Double.valueOf(data.get(i).get("TURNVOLUME").toString());
					 data.get(i).put("VOLUME",DataUtils.roundDouble(number/10000,2));
					 data.get(i).put("TURNVOLUME",DataUtils.roundDouble(money/10000,2));
					 String code = data.get(i).get("CODE_").toString();
					 data.get(i).put("CODEONLY", code.substring(2, code.length()));
					 data.get(i).put("A1", DataUtils.roundDouble(curPrice-prePrice,2));
					 if(openPrice==0){//停牌
						 data.get(i).put("A", "--");
						 data.get(i).put("color", "gray");
					 }else{
						 double A = DataUtils.roundDouble((curPrice-prePrice)*100/prePrice,2);
						 data.get(i).put("A", DataUtils.roundDouble((curPrice-prePrice)*100/prePrice,2)+"%");
						 if((curPrice-prePrice) > 0){
							 data.get(i).put("color", "red");
						 }else if ((curPrice-prePrice) < 0){
						     data.get(i).put("color", "green");
						 }else{
							 data.get(i).put("A1", "--");
							 data.get(i).put("A", "--");
							 data.get(i).put("color", "gray");
						 }
					 }
				 }
			 }
		  Map map = new HashMap<String,Object>();
		  map.put("data", data);
		 return map;
	  }
	 public Map hyfl(String flag){
		 String sql= " select * from REALTIMEQUTOES where rownum<=100 and  inDUSTRY  ='"+flag+"' order by VOLUME desc";
		 List<Map<String, Object>> data = this.jdbcTemplate.queryForList(sql);
		 if(data!=null&&data.size()>0){
			 for(int i=0;i<data.size();i++){
				 String code = data.get(i).get("CODE_").toString();
				 data.get(i).put("CODEONLY", code.substring(2, code.length()));
				 double curPrice = Double.valueOf(data.get(i).get("PRICE_").toString());
				 double prePrice = Double.valueOf(data.get(i).get("PRECLOSE_").toString());
				 double openPrice = Double.valueOf(data.get(i).get("OPEN_").toString());
				 double number = Double.valueOf(data.get(i).get("VOLUME").toString());
				 double money = Double.valueOf(data.get(i).get("TURNVOLUME").toString());
				 data.get(i).put("VOLUME",DataUtils.roundDouble(number/10000,2));
				 data.get(i).put("TURNVOLUME",DataUtils.roundDouble(money/10000,2));
				 data.get(i).put("A1", DataUtils.roundDouble(curPrice-prePrice,2));
				 if(openPrice==0){//停牌
					 data.get(i).put("A", "--");
					 data.get(i).put("color", "gray");
				 }else{
					 data.get(i).put("A", DataUtils.roundDouble((curPrice-prePrice)*100/prePrice,2)+"%");
					 if((curPrice-prePrice) > 0){
						 data.get(i).put("color", "red");
					 }else if ((curPrice-prePrice) < 0){
					     data.get(i).put("color", "green");
					 }else{
						 data.get(i).put("A", "--");
						 data.get(i).put("color", "gray");
					 }
				 }
			 }
		 }
		 Map map = new HashMap<String,Object>();
		 map.put("data", data);
		 return map;
	  }
	 public Map kjson(String code){
		    Map map = new HashMap();
		  	if(code!=null&&!"".equals(code)){
		    	JdbcTemplate dao = (JdbcTemplate) SpringUtil.getBean("jdbcTemplate");
		    	log.info("查看股票："+code.trim());
		    	String sql = "SELECT DAY_,nvl(CLOSE_,0) as PRECLOSE_,nvl(OPEN_,0) AS OPEN_,nvl(HIGH_,0) AS HIGH_,nvl(LOW_,0) AS LOW_  FROM CLOSEQUTOES where   code_='"+code.trim()+"'and open_ >0  order by day_  asc";
		    	log.info(sql);
		    	List<Map<String,Object>> list = dao.queryForList(sql);
		        List<String> dateList = new ArrayList<String>();
		        List<List<String>> lst = new ArrayList<List<String>>();
		        List<String> k = null;
		        if(list!=null&&list.size()>0){
		        	for(Map tmp:list){
		        		k = new ArrayList<String>();
		        		dateList.add(tmp.get("DAY_").toString());
		        		k.add(tmp.get("OPEN_").toString());
		        		k.add(tmp.get("PRECLOSE_").toString());
		        		k.add(tmp.get("LOW_").toString());
		        		k.add(tmp.get("HIGH_").toString());
		        		// 开盘，收盘，最低，最高
		        		lst.add(k);
		        	}
		        }else{
		        	return null;
		        }
		        //在行情交易时间内，新增一个当日k点,每个交易日凌晨5点获取上一个交易日的收盘行情，所以在开盘时间9：30-5:00之间都需要
		        try {
		        	String sql_k="SELECT * FROM realtimequtoes where code_='"+code+"'";
		        	log.info(sql_k);
		        	KPoint todayk = dao.queryForObject(sql_k, new KPoint());
					k = new ArrayList<String>();
					k.add(todayk.getOpen().toString());
					k.add(todayk.getPrice().toString());
					k.add(todayk.getLow().toString());
					k.add(todayk.getHigh().toString());
					lst.add(k);
					dateList.add(todayk.getRecord_date());
				} catch (Exception e) {
		        	log.info("当日最新K点获取异常！可忽略。"+e.toString());
				}
		        map.put("name", QutoesContants.STOCKMAP.get(code.trim()));
		        map.put("ks", lst);
		        map.put("code", code);
		        map.put("dates", dateList);
		  	}
		      return map;
		  }
	  public Map lineJson(String code){
		    double max = 0.0;double min = 0.0;
	        double preclose=0.0;double curPrice=0.0;
	        double open = 0.0;double price=0.0;double cje=0.0;double cjl=0.0;
		  	Map<String,Object> map = new HashMap<String,Object>();
		  	try{
			//String jyrsql = "(select  curjyr from STOCKCONFIG where alias='LASTESTDAY' )";
		  	if(code!=null&&!"".equals(code)){
		  		String sql_zs =" select (price_-preCLOSE_ ) A1,(price_-preCLOSE_ )/preclose_*100  as A,high_,low_,preclose_,open_,price_ as price,code_,name_ from realtimequtoes where  code_ in('sz399001','sh000001','"+code+"') ";
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
			    	for(Map<String,Object> zhzs :list_zhzs){
			    		String code_ = zhzs.get("CODE_").toString();
			    		if("sz399001".equals(code_)){
			    			szzsprice =zhzs.get("PRICE").toString();
			    			szzsA = DataUtils.roundStr(Double.valueOf(zhzs.get("A").toString()), 2);
			    			szzsA1 = DataUtils.roundStr(Double.valueOf(zhzs.get("A1").toString()), 2);
			    			if(Double.valueOf(szzsA)>0){
			    				szzsA="+"+szzsA;
			    				szzsA1="+"+szzsA1;
			    				colorsz="red";
			    			}else{
			    				/*szzsA="-"+szzsA;
			    				szzsA1="-"+szzsA1;*/
			    			}
			    		}else if("sh000001".equals(code_)){
			    			shzsprice =zhzs.get("PRICE").toString();
			    			shzsA = DataUtils.roundStr(Double.valueOf(zhzs.get("A").toString()), 2);
			    			shzsA1 = DataUtils.roundStr(Double.valueOf(zhzs.get("A1").toString()), 2);
			    			if(Double.valueOf(shzsA)>0){
			    				shzsA="+"+shzsA;
			    				shzsA1="+"+shzsA1;
			    				colorsh="red";
			    			}else{
			    				/*shzsA1="-"+shzsA1;
			    				shzsA="-"+shzsA;*/
			    			}
			    		}
			    		/*else if(code.equals(code_)&&!code.equals("sz399001")&&!code.equals("sh000001")){
			    			open =  Double.valueOf(zhzs.get("OPEN_").toString());
			    			min = Double.valueOf(zhzs.get("LOW_").toString());
			    			max = Double.valueOf(zhzs.get("HIGH_").toString());
			    			preclose=Double.valueOf(zhzs.get("PRECLOSE_").toString());
			    			price=Double.valueOf(zhzs.get("PRICE").toString());
			    		}*/
			    	}
			    }
			    
			    	String sql = "SELECT substr(oper_time,0,16)  as time_ ,price_,nvl(PRECLOSE_,0) as PRECLOSE_,HIGH_,LOW_,OPEN_,time_min FROM MINUTEQUTOESCURRDAY where code_='"+code.trim()+"' order by time_ asc";
			        List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql);
			        List<String> x = new ArrayList<String>();
			        List<String> y = new ArrayList<String>();
			        Map<String, String> xMap = QutoesUtils.spiltTimeMap("", 1);//获取所有的坐标			       
			        if(list!=null&&list.size()>0){
			        	for(Map<String,Object> tmp:list){
			        		curPrice = Double.valueOf(tmp.get("PRICE_").toString());
			        		String time_ = tmp.get("TIME_MIN").toString();
			        		String res = DateUtil.format(time_+"00", "yyyy-MM-dd HH:mm:ss");
			        		String xVal = res.substring(11, res.length()-3);
			        		x.add(res.substring(11, res.length()-3));
			        		if(xMap.containsKey(xVal)){
			        			xMap.put(xVal, curPrice+"");
			        		}
			        		y.add(curPrice+"");
			        	}
			        	open = Double.valueOf(list.get(list.size()-1).get("OPEN_").toString());
			        	preclose = Double.valueOf(list.get(list.size()-1).get("PRECLOSE_").toString());			        	
			        	String newSql = "SELECT price_,high_,low_,VOLUME,TURNVOLUME FROM REALTIMEQUTOES where code_='"+code.trim()+"'";
			        	List<Map<String,Object>> listNew = this.jdbcTemplate.queryForList(newSql);
			        	max = Double.valueOf(listNew.get(0).get("HIGH_").toString());
			        	min = Double.valueOf(listNew.get(0).get("LOW_").toString());
			        	cje = Double.valueOf(listNew.get(0).get("VOLUME").toString());
			        	cjl = Double.valueOf(listNew.get(0).get("TURNVOLUME").toString());
			        	if(max==0||min==0){
			        		max = Double.valueOf(list.get(list.size()-1).get("HIGH_").toString());
				        	min = Double.valueOf(list.get(list.size()-1).get("LOW_").toString());
			        	}
			        	curPrice =  Double.valueOf(listNew.get(0).get("PRICE_").toString());
			        	List<String> tmpy = new ArrayList<String>();
			        	tmpy.addAll(xMap.values());
			        	int maxIndex = QutoesUtils.getIndexOfNotNullValueList(tmpy);
			        	x.clear();
			        	y.clear();
			        	if(maxIndex>0){
			        		for(int i=1;i<maxIndex-1;i++){
			        			if(tmpy.get(i).equals("-")){
			        				tmpy.set(i, tmpy.get(i-1));
			        			}
			        		}
			        		x.addAll(xMap.keySet());
		        			y.addAll(tmpy);
			        	}else{
			        		x.addAll(xMap.keySet());
		        			y.addAll(xMap.values());
			        	}		        	
	        			
			        	if(x!=null&&x.size()>0){
			        		map.put("x", JSONArray.fromObject(x));
			        	}else{
			        		map.put("x", JSONArray.fromObject("[]"));
			        	}
			        	y.add(String.valueOf(curPrice));
			        	if(y!=null&&y.size()>0){
			        		map.put("y", JSONArray.fromObject(y));
			        	}else{
			        		map.put("y", JSONArray.fromObject("[]"));
			        	}
			        	map.put("realmax", max); map.put("realmin", min);
			        	Map<String,Double> res = QutoesUtils.calSection(max, min, preclose);
			        	max = res.get("max");
			        	min = res.get("min");
			        }else{
			        	return new HashMap<String,Object>();
			        }
		        double amplitude  =  0;
			    if(preclose!=0){
			    	amplitude = 100*(curPrice-preclose)/preclose;  
			    }
			    map.put("shzsprice", shzsprice);
			    map.put("szzsprice", szzsprice);
			    map.put("shzsA", shzsA);
			    map.put("szzsA", szzsA);
			    map.put("shzsA1", shzsA1);
			    map.put("szzsA1", szzsA1);
			    map.put("colorsh", colorsh);
			    map.put("colorsz", colorsz);
			    map.put("gap", DataUtils.roundStr(curPrice-preclose,2));
			    map.put("price", DataUtils.roundStr(curPrice,2));
			    map.put("A", DataUtils.roundStr(amplitude, 2));//涨跌幅   
			    map.put("name", QutoesContants.STOCKMAP.get(code));  
		        map.put("max", max);
		        map.put("close", preclose);
		        map.put("min", min);
		        map.put("code", code);
		        map.put("open", open);
		        map.put("cjl", cjl);
		        map.put("cje", cje);
		        map.put("start","09:30");
		        map.put("end","15:30");
		        map.put("time", DateUtil.format(new Date(), "yyyy年MM月dd日HH时mm分ss秒"));
		  	}
		      return map;	
		  	}catch(Exception e){
	        	e.printStackTrace(); 
	        	return map;
	        }
		  }  
	  public Map dpzs(String code0){
		  String sql= " select * from REALTIMEQUTOES where inDUSTRY = 'zhzs' order by code_ asc";
		 List<Map<String, Object>> data = this.jdbcTemplate.queryForList(sql);
		 if(data!=null&&data.size()>0){
			 int count = data.size();
			 for(int i=0;i<count;i++){
				 double curPrice = Double.valueOf(data.get(i).get("PRICE_").toString());
				 double prePrice = Double.valueOf(data.get(i).get("PRECLOSE_").toString());
				 double openPrice = Double.valueOf(data.get(i).get("OPEN_").toString());
				 double number = Double.valueOf(data.get(i).get("VOLUME").toString());
				 double money = Double.valueOf(data.get(i).get("TURNVOLUME").toString());
				 data.get(i).put("VOLUME",DataUtils.roundDouble(number/10000,2));
				 data.get(i).put("TURNVOLUME",DataUtils.roundDouble(money/10000,2));
				 String code = data.get(i).get("CODE_").toString();
				 data.get(i).put("CODEONLY", code.substring(2, code.length()));
				 data.get(i).put("A1", DataUtils.roundDouble(curPrice-prePrice,2));
				 if(openPrice==0){//停牌
					 data.get(i).put("A", "--");
					 data.get(i).put("color", "gray");
				 }else{
					 data.get(i).put("A", DataUtils.roundDouble((curPrice-prePrice)*100/prePrice,2)+"%");
					 if((curPrice-prePrice) > 0){
						 data.get(i).put("color", "red");
					 }else if ((curPrice-prePrice) < 0){
					     data.get(i).put("color", "green");
					 }else{
						 data.get(i).put("A", "--");
						 data.get(i).put("color", "gray");
					 }
				 }
			 }
		 }
		  Map map = new HashMap();
		  map.put("data", data);
		  return map;
	  }
	  public List<Map<String, Object>> staticsCompany(){
		 String sql = "SELECT count(*) num,zdbz FROM REALTIMEQUTOES where open_>0 group by ZDBZ order by zdbz asc";
		 List<Map<String, Object>> data = this.jdbcTemplate.queryForList(sql);
		 Map<String, Object>  data_  = null;
		 List<Map<String, Object>> retdata = new ArrayList<Map<String,Object>>();
		 if(data!=null&&data.size()>0){
			 for(Map tmp :data){
				 data_ = new LinkedHashMap<String, Object>();
				 data_.put("name", EContants.zdbzMap.get(tmp.get("ZDBZ").toString()));
				 data_.put("type", EContants.zdbzMapParam.get(tmp.get("ZDBZ").toString()));
				 data_.put("value", tmp.get("num").toString());
				 retdata.add(data_);
			 }
		 }
		 return retdata;
	  }
	  public Map closeQutoes(String code){
		  String sql = "SELECT record_DATE_ x, price_ y,name_ name FROM CLOSEQUTOES  where code_ ='"+code+"' and open_>0 order by recORD_DATE_  asc";
		  Map<String,Object> map = new HashMap<String,Object>();
		  List<String> x = new ArrayList<String>();
		  List<String> y = new ArrayList<String>();
		  double max = 0.0;
		  double min = 0.0;
		  List<Map<String,Object>> data= this.jdbcTemplate.queryForList(sql);
		  if(data!=null&&data.size()>0){
			  map.put("name", data.get(0).get("NAME").toString());
			  max = min = Double.valueOf(data.get(0).get("Y").toString());
			  for(Map tmp:data){
				  double pricetmp = Double.valueOf(tmp.get("Y").toString());
				  if(pricetmp>max){
					  max = pricetmp;
				  }
				  if(pricetmp<=min){
					  min = pricetmp;
				  }
				  x.add(tmp.get("X").toString());
				  y.add(tmp.get("Y").toString());
			  }
		  }
		  map.put("x", x);
		  map.put("y", y);
		  map.put("max", max+0.1);
		  map.put("min", min-0.1);
		  return map;
	  }
	  public Map hsindiator(){
		  Map map = new LinkedHashMap();
		  String sql_zs="SELECT (price_-preCLOSE_ ) A1, (price_-preCLOSE_ )/preclose_*100  as A,price_ as price,code_,name_ FROM REALTIMEQUTOES "
		    		+ "where industry='zhzs' and open_>0 group by code_";
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
		    map.put("shzsprice", shzsprice);
		    map.put("szzsprice", szzsprice);
		    map.put("shzsA", shzsA);
		    map.put("szzsA", szzsA);
		    map.put("shzsA1", shzsA1);
		    map.put("szzsA1", szzsA1);
		    map.put("colorsh", colorsh);
		    map.put("colorsz", colorsz);
		    return map;
	  }
}
