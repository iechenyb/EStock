package com.cyb.qutoes.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cyb.qutoes.service.GrabDataService;

@Controller
@RequestMapping("analysis")
public class TradeController {
    static Logger logger = LoggerFactory.getLogger(TradeController.class);
    @Resource(name="grabDataService")
    GrabDataService grabservice;
    @RequestMapping("/persisCodeInfor")
    public ModelAndView persisCodeInfor(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
        List<Map> lst = this.grabservice.getAllCodeInfor();
        view.addObject("list", lst);
        view.setViewName("/qutoes/qutoesList");
        return view;
    }
    //n日内连续跌
    @RequestMapping("/ndayup")
    public ModelAndView LXZ(HttpServletRequest request,int days,String maxPrice,String minPrice){
    	ModelAndView view = new ModelAndView();
    	if("".equals(days)){
    		days=1;
    	}
    	String con = "";
    	if(maxPrice!=null&&!"".equals(maxPrice)){
    		con=con+" and price_<="+maxPrice+" ";
    	}
    	if(minPrice!=null&&!"".equals(minPrice)){
    		con=con+" and price_>="+minPrice+" ";
    	}
        List lst = (List) this.grabservice.getNDaysUpStocks(days,con).get("list");
        view.addObject("list", lst);
        view.setViewName("/qutoes/nday");
        return view;
    }
    //n日内连续跌
    @RequestMapping("/ndaydown")
    public ModelAndView LXD(HttpServletRequest request,int days,String maxPrice,String minPrice){
    	ModelAndView view = new ModelAndView();
    	String con = "";
    	if("".equals(days)){
    		days=1;
    	}
    	if(maxPrice!=null&&!"".equals(maxPrice)){
    		con=con+" and price_<="+maxPrice+" ";
    	}
    	if(minPrice!=null&&!"".equals(minPrice)){
    		con=con+" and price_>="+minPrice+" ";
    	}
        List lst = (List) this.grabservice.getNDaysDownStocks(days,con).get("list");
        view.addObject("list", lst);
        view.setViewName("/qutoes/nday");
        return view;
    }
    
}
