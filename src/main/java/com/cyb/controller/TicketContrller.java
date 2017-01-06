package com.cyb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cyb.page.Pagination;
import com.cyb.service.UserService;
import com.cyb.utils.PropertyUtil;
@RequestMapping("ticket")
@Controller
public class TicketContrller {
	public  static Map pageParam = new HashMap();
	volatile static int number = 10;//volatile
	static{
		pageParam.put("limit", 0);
		pageParam.put("reqs", 0);
		pageParam.put("windows", 0);
		pageParam.put("sema", 0);
	}
	@Resource(name="userService")
	public UserService userService;
	/**
	 * 
	 * @param reqs 请求数量，客户端数量
	 * @param limit 每个客户端最多购买票数
	 * @param windows 窗口数
	 * @param sema 信号量
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/sale")
	//@ResponseBody
	public ModelAndView startSale(int reqs,int limit,int windows,int sema ,Map data){
		pageParam.put("limit", limit);
		pageParam.put("reqs", reqs);
		pageParam.put("windows", windows);
		pageParam.put("sema", sema);
		String ret = "";
		Long start = System.currentTimeMillis();
		ModelAndView mav = new ModelAndView();
		for(int windowid=1;windowid<=windows;windowid++){
			try {
				 //new ConcurrentTest(100,1).startSale();
				 //ret = new TicketThread(reqs,limit).call();
				 ExecutorService executor = Executors.newCachedThreadPool();
				 TicketThread task = new TicketThread(reqs,limit,windowid,sema);
			     Future<String> result = executor.submit(task);
			     mav.addObject("infor", result.get());
			     executor.shutdown();
		         /*FutureTask<String> futureTask = new FutureTask<String>(task);
		         executor.submit(futureTask);
		         mav.addObject("infor", futureTask.get());
		         executor.shutdown();*/
			} catch (Exception e) {
				e.printStackTrace();
			}//open ith window
		}
		int total = userService.getTotal();
		Pagination initPage =  new Pagination(0,Integer.valueOf(PropertyUtil.get("ticketpageSize")),total);
		List<Map> list = userService.getList(initPage);
		System.out.println("list = "+list);
		for(Map map:list){
			map.put("reqnum", map.get("REQSTR").toString().split("#")[1]);
		}
		data.put("list", list);
		mav.addObject("reqs", reqs);
		mav.addObject("limit", limit);
		mav.addObject("windows", windows);
		mav.addObject("sema", sema);
		mav.addObject("page", initPage);
		mav.addObject("list", list);
		mav.addObject("ttotal", this.userService.getRealTradeNum());
		mav.addObject("total", this.userService.getNums());
		mav.setViewName("/ticket/lstStu");
		mav.addObject("param",pageParam);
		Long end = System.currentTimeMillis();
		if(end-start>=1000){
		   mav.addObject("time", (end-start)/1000+"s");
		}else{
		   mav.addObject("time", (end-start)+"ms");
		}
		return mav;
	}
//	@ResponseBody   //返回list的时候，必须加  @responseBody注解
	@RequestMapping(value = "/list")
	public ModelAndView getSaleLogsList(Map data,Integer page,String cmd) {
		int total = userService.getTotal();
		Pagination initPage =  new Pagination(0,Integer.valueOf(PropertyUtil.get("ticketpageSize")),total);
		int pageCount = initPage.getPageCount();
		if(cmd!=null&&!"".equals(cmd)){
			if(cmd.equals("pre")){
				if(page>1){
					page = page-1;
				}else{
					page=1;
				}
			}else if(cmd.equals("next")){
				if(page>pageCount){
					page = pageCount;
				}else{
					page = page +1;
				}
			}else if(cmd.equals("first")){
				page =1;
			}else if(cmd.equals("last")){
				page = pageCount;
			}
		}else{
			page=1;
		}
		Pagination page_ =  new Pagination(page,Integer.valueOf(PropertyUtil.get("ticketpageSize")),total);
        List<Map> list = userService.getList(page_);
		System.out.println("list = "+list);
		for(Map map:list){
			map.put("reqnum", map.get("REQSTR").toString().split("#")[1]);
		}
		data.put("list", list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("param",pageParam);
		mav.addObject("total", this.userService.getNums());
		mav.addObject("ttotal", this.userService.getRealTradeNum());
		mav.addObject("page", page_);
		mav.setViewName("/ticket/lstStu");
		return mav;
	}
	@RequestMapping(value = "/clear")
	public ModelAndView clear() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ticket/lstStu");
		this.userService.delLog();
		mav.addObject("total", this.userService.getNums());
		return mav;
	}
	@RequestMapping(value = "/add100")
	public ModelAndView add100(Integer num) {
		int curr = this.userService.add100(num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ticket/lstStu");
		mav.addObject("total", curr);
		mav.addObject("ttotal", this.userService.getRealTradeNum());
		return mav;
	}
	@ResponseBody   //返回list的时候，必须加  @responseBody注解
	@RequestMapping(value = "/buy")
	public JSONArray buySome(Long number,Long threadNo,Long window,HttpServletRequest req){
		String queryStr = req.getQueryString();
		this.userService.log(threadNo, number, window.toString(), queryStr, 0);
		Map map = new HashMap();
		return JSONArray.fromObject(map);
	}
}
