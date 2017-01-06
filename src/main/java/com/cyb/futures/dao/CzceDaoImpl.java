package com.cyb.futures.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository("czceDao")
public class CzceDaoImpl {
 @Resource
 JdbcTemplate jdbcTemplate; 
 public Map<String,Object> getHisCloseQutoes(String product,String secuid){
	 String condition ="";
	 if(product!=null&&!"".equals(product)){
		 condition = condition +" and product='"+product+"'";
	 }
	 if(secuid!=null&&!"".equals(secuid)){
		 condition = condition +" and SECUID='"+secuid+"'";
	 }
	 String sql = "select RECORD_dATE ,NVL(close,0) close from czce where 1=1 "+condition;
	 List<Map<String,Object>> data = this.jdbcTemplate.queryForList(sql);
	 List<Double> close = new ArrayList<Double>();
	 List<String> date = new ArrayList<String>();
	 if(data!=null&&data.size()>0){
		 for(Map tmp:data){
			 close.add(Double.valueOf(tmp.get("CLOSE").toString()));
			 date.add(tmp.get("RECORD_DATE").toString());
		 }
	 }
	 Map<String,Object> ret = new HashMap<String, Object>();
	 ret.put("x", date);
	 ret.put("y", close);
	 return ret;
 }
 public Map<String,Object> getHisKQutoes(String product,String secuid){
	 String condition ="";
	 if(product!=null&&!"".equals(product)){
		 condition = condition +" and product='"+product+"'";
	 }
	 if(secuid!=null&&!"".equals(secuid)){
		 condition = condition +" and SECUID='"+secuid+"'";
	 }
	 String sql = "select RECORD_dATE ,NVL(high,0) high ,NVL(open,0) open ,NVL(low,0) low "
	 		+ ",NVL(close,0) close from czce where 1=1 "+condition;
	 List<Map<String,Object>> data = this.jdbcTemplate.queryForList(sql);
	 List<Double> close = new ArrayList<Double>();
	 List<String> date = new ArrayList<String>();
	 List<String> k = new ArrayList<String>();
	 List<List<String>> ks = new ArrayList<List<String>>();
	 if(data!=null&&data.size()>0){
		 for(Map tmp:data){
			 close.add(Double.valueOf(tmp.get("CLOSE").toString()));
			 date.add(tmp.get("RECORD_DATE").toString());
				k = new ArrayList<String>();
        		date.add(tmp.get("RECORD_DATE").toString());
        		k.add(tmp.get("OPEN").toString());
        		k.add(tmp.get("CLOSE").toString());
        		k.add(tmp.get("LOW").toString());
        		k.add(tmp.get("HIGH").toString());
        		ks.add(k);
		 }
	 }
	 Map<String,Object> ret = new HashMap<String, Object>();
	 ret.put("x", date);
	 ret.put("ks", ks);
	 return ret;
 }
}
