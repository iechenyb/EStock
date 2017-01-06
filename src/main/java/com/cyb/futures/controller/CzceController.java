package com.cyb.futures.controller;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.futures.service.CzceServiceImpl;
//@RequestMapping("czce")
//@Controller
public class CzceController {
// @Resource(name="czceService")
 public CzceServiceImpl czceService;
 
// @RequestMapping(value="/close")
// @ResponseBody
 public JSONArray hisClose(String product,String secuid){
	 return JSONArray.fromObject(czceService.getHisCloseQutoes(product, secuid));
 }
 
// @RequestMapping(value="/ks")
// @ResponseBody
 public JSONArray hisKs(String product,String secuid){
	 return  JSONArray.fromObject(czceService.getHisCloseQutoes(product, secuid));
 }
}
