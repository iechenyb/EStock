package com.cyb.phone.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyb.phone.dao.PhoneStockDaoImpl;
@Service("phoneStockService")
public class PhoneStockServiceImpl {
	@Resource(name="phoneStockDao")
	PhoneStockDaoImpl dao;
   public Map myConern(String username){
	   return this.dao.myConern(username);
   }
   public Map zdfx(String type){
	   return this.dao.zdfx(type);
   }
   public Map hyfl(String flag){
	   return this.dao.hyfl(flag);
   }
   public Map kline(String code){
	   return this.dao.kjson(code);
   }
   public Map lineJson(String code){
	   return this.dao.lineJson(code);
   }
   public Map dpzs(String code){
	   return this.dao.dpzs(code);
   }
   public List<Map<String, Object>> staticsCompany(){
	   return this.dao.staticsCompany();
   }
   public Map closeQutoes(String code){
	   return this.dao.closeQutoes(code);
   }
   public Map hsindiator(){
	   return this.dao.hsindiator();
   }
}
