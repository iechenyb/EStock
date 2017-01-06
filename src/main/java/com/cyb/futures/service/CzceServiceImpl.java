package com.cyb.futures.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyb.futures.dao.CzceDaoImpl;

@Service(value="CzceService")
public class CzceServiceImpl {
	@Resource(name="czceDao")
	public CzceDaoImpl dao;
	public Map<String,Object> getHisKQutoes(String product,String secuid){
		 return this.dao.getHisKQutoes(product, secuid);
	}
	public Map<String,Object> getHisCloseQutoes(String product,String secuid){
		 return this.dao.getHisCloseQutoes(product, secuid);
	}
}
