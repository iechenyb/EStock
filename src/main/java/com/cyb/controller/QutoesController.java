package com.cyb.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cyb.page.Pagination;
import com.cyb.phone.service.PhoneStockServiceImpl;
import com.cyb.qutoes.constant.QutoesContants;
import com.cyb.qutoes.constant.SpringUtil;
import com.cyb.qutoes.vo.KPoint;
import com.cyb.qutoes.vo.User;
import com.cyb.service.UserService;
import com.cyb.utils.DataUtils;
import com.cyb.utils.DateUtil;
import com.cyb.utils.PropertyUtil;
import com.cyb.utils.QutoesUtils;
import com.cyb.utils.UUIDUtils;

@Controller
@RequestMapping("ws")
public class QutoesController {
	Log log =LogFactory.getLog(QutoesController.class);
	@Resource(name = "phoneStockService")
	PhoneStockServiceImpl phoneStockService;
	static Log logger = LogFactory.getLog(QutoesController.class);
	@Resource(name = "userService")
	UserService userService;
	@Resource(name = "jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	@RequestMapping("/line")
	public ModelAndView minQutoes(HttpServletRequest request, String code) {
		String jyrsql = "(select  curjyr from STOCKCONFIG where alias='LASTESTDAY' )";
		ModelAndView view = new ModelAndView();
		if (code != null && !"".equals(code)) {
			// 求大盘指数
			String sql_zs = " select (price_-preCLOSE_ ) A1,(price_-preCLOSE_ )/preclose_*100  as A,price_ as price,code_,name_ from REALTIMEQUTOES    where code_ in('sz399001','sh000001')";
			List<Map<String, Object>> list_zhzs = this.jdbcTemplate
					.queryForList(sql_zs);
			String shzsprice = "0";
			String szzsprice = "0";
			String shzsA = "0";
			String szzsA = "0";
			String colorsh = "green";
			String colorsz = "green";
			if (list_zhzs != null && list_zhzs.size() > 0) {
				for (Map<String,Object> zhzs : list_zhzs) {
					String code_ = zhzs.get("CODE_").toString();
					if ("sz399001".equals(code_)) {
						szzsprice = zhzs.get("PRICE").toString();
						szzsA = DataUtils.roundStr(
								Double.valueOf(zhzs.get("A").toString()), 2);
						if (Double.valueOf(szzsA) > 0) {
							szzsA = "+" + szzsA;
							colorsz = "red";
						}
					} else if ("sh000001".equals(code_)) {
						shzsprice = zhzs.get("PRICE").toString();
						shzsA = DataUtils.roundStr(
								Double.valueOf(zhzs.get("A").toString()), 2);
						if (Double.valueOf(shzsA) > 0) {
							shzsA = "+" + shzsA;
							colorsh = "red";
						}
					}
				}
			}
			JdbcTemplate dao = (JdbcTemplate) SpringUtil
					.getBean("jdbcTemplate");
			String sql = "SELECT substr(oper_time,0,16)  as time_ ,OPEN_,price_,nvl(PRECLOSE_,0) as PRECLOSE_,HIGH_,LOW_,time_min FROM MINUTEQUTOESCURRDAY where record_date_ = "
					+ DateUtil.date2long8(new Date())
					+ " and code_='"
					+ code.trim()
					+ "' order by time_ asc";
			logger.info(sql);
			List<Map<String, Object>> list = dao.queryForList(sql);
			Map<String,Object> map = new HashMap<String,Object>();
			List<String> y = new ArrayList<String>();
			List<String> x_ = new ArrayList<String>();
			List<String> x = QutoesUtils.spiltTimeList("", 1);// 获取所有的坐标
			Map<String, String> allMap = QutoesUtils.spiltTimeMap("", 1);// 获取所有的坐标
			double max = 0.0;
			double min = 0.0;
			double preclose = 0.0;
			double curPrice = 0.0;
			double open = 0.0;
			if (list != null && list.size() > 0) {
				open = Double.valueOf(list.get(0).get("OPEN_").toString());
				preclose = Double.valueOf(list.get(0).get("PRECLOSE_")
						.toString());
				for (Map tmp : list) {
					curPrice = Double.valueOf(tmp.get("PRICE_").toString());
					String time_ = tmp.get("TIME_MIN").toString();
					String res = DateUtil.format(time_ + "00",
							"yyyy-MM-dd HH:mm:ss");
					allMap.put(res.substring(11, res.length() - 3),
							tmp.get("PRICE_").toString());
					x_.add(res.substring(11, res.length() - 3));
				}
				max = Double.valueOf(list.get(list.size() - 1).get("HIGH_")
						.toString());
				min = Double.valueOf(list.get(list.size() - 1).get("LOW_")
						.toString());
				for (int i = 0; i < x.size(); i++) {
					String time_str = x.get(i);// 09:30
					String val_str = allMap.get(time_str).trim();
					y.add(val_str);
				}
				int maxMin = Integer.valueOf(x_.get(x_.size() - 1).replace(":",
						""));
				Iterator<String> x1 = x.iterator();
				Iterator<String> y1 = y.iterator();
				while (x1.hasNext() && y1.hasNext()) {
					int tmpx = Integer.valueOf(x1.next().replace(":", ""));
					String tmpy = y1.next();
					if (tmpy.equals("-") && tmpx < maxMin) {
						// 移除当前的对象
						x1.remove();
						y1.remove();
					}
				}
				if (x != null && x.size() > 0) {
					map.put("x", JSONArray.fromObject(x));
				} else {
					map.put("x", JSONArray.fromObject("[]"));
				}
				if (y != null && y.size() > 0) {
					map.put("y", JSONArray.fromObject(y));
				} else {
					map.put("y", JSONArray.fromObject("[]"));
				}
				view.addObject("y", JSONArray.fromObject(y));
				view.addObject("x", JSONArray.fromObject(x));
				view.addObject("realmax", max);
				view.addObject("realmin", min);
				Map<String, Double> res = QutoesUtils.calSection(max, min,
						preclose);
				max = res.get("max");
				min = res.get("min");
			}
			double amplitude = 0;
			if (preclose != 0) {
				amplitude = 100 * (curPrice - preclose) / preclose;
			}
			view.addObject("shzsprice", shzsprice);
			view.addObject("szzsprice", szzsprice);
			view.addObject("shzsA", shzsA);
			view.addObject("szzsA", szzsA);
			view.addObject("colorsh", colorsh);
			view.addObject("colorsz", colorsz);
			view.addObject("gap", DataUtils.roundStr(curPrice - preclose, 2));
			view.addObject("price", DataUtils.roundStr(curPrice, 2));// 涨跌幅
			view.addObject("A", DataUtils.roundStr(amplitude, 2));// 涨跌幅
			view.addObject("max", max);
			view.addObject("open", open);
			view.addObject("cjl", 0);
			view.addObject("cje", 0);
			view.addObject("close", preclose);
			view.addObject("min", min);
			view.addObject("code", code);
			view.addObject("start", "09:30");
			view.addObject("end", "15:00");
			// view.addObject("exchange", exchange);
		}
		view.setViewName("qutoes/mintuline");
		return view;
	}

	@RequestMapping(value = "/linejson")
	@ResponseBody
	public JSONArray lineJson(HttpServletRequest request, String code) {
		Map<String,Object> map = new HashMap<String,Object>();
		map = phoneStockService.lineJson(code);
		return JSONArray.fromObject(map);
	}

	@RequestMapping("/k")
	// @ResponseBody
	public ModelAndView historyK(HttpServletRequest request, String code) {
		ModelAndView view = new ModelAndView();
		if (code != null && !"".equals(code)) {
			JdbcTemplate dao = (JdbcTemplate) SpringUtil
					.getBean("jdbcTemplate");
			logger.info("查看股票：" + code.trim());
			String sql = "SELECT DAY_,nvl(CLOSE_,0) as PRECLOSE_,nvl(OPEN_,0) AS OPEN_,nvl(HIGH_,0) AS HIGH_,nvl(LOW_,0) AS LOW_  FROM CLOSEQUTOES where   code_='"
					+ code.trim() + "'  and open_ >0 order by time_ asc";
			logger.info(sql);
			List<Map<String, Object>> list = dao.queryForList(sql);
			List<String> dateList = new ArrayList<String>();
			List<List<String>> lst = new ArrayList<List<String>>();
			List<String> k = null;
			if (list != null && list.size() > 0) {
				for (Map tmp : list) {
					k = new ArrayList<String>();
					dateList.add(tmp.get("DAY_").toString());
					k.add(tmp.get("OPEN_").toString());
					k.add(tmp.get("PRECLOSE_").toString());
					k.add(tmp.get("LOW_").toString());
					k.add(tmp.get("HIGH_").toString());
					// 开盘，收盘，最低，最高
					lst.add(k);
				}
			}
			KPoint todayk = dao.queryForObject("SELECT * FROM REALTIMEQUTOES   where code_='"+ code+ "' ", new KPoint());
			k = new ArrayList<String>();
			k.add(todayk.getOpen().toString());
			k.add(todayk.getPrice().toString());
			k.add(todayk.getLow().toString());
			k.add(todayk.getHigh().toString());
			lst.add(k);
			dateList.add(todayk.getRecord_date());
			view.addObject("ks", lst);
			view.addObject("dates", JSONArray.fromObject(dateList));
			view.addObject("code", code);
		}
		view.setViewName("qutoes/KEcharts");
		return view;
	}

	@RequestMapping("/kjson")
	@ResponseBody
	public JSONArray kjson(HttpServletRequest request, String code) {
		Map<String,Object> map = new HashMap<String,Object>();
		if (code != null && !"".equals(code)) {
			JdbcTemplate dao = (JdbcTemplate) SpringUtil
					.getBean("jdbcTemplate");
			logger.info("查看股票：" + code.trim());
			String sql = "SELECT DAY_,nvl(CLOSE_,0) as PRECLOSE_,nvl(OPEN_,0) AS OPEN_,nvl(HIGH_,0) AS HIGH_,nvl(LOW_,0) AS LOW_  FROM CLOSEQUTOES where   code_='"
					+ code.trim() + "'and open_ >0  order by day_  asc";
			logger.info(sql);
			List<Map<String, Object>> list = dao.queryForList(sql);
			List<String> dateList = new ArrayList<String>();
			List<List<String>> lst = new ArrayList<List<String>>();
			List<String> k = null;
			if (list != null && list.size() > 0) {
				for (Map tmp : list) {
					try{
						k = new ArrayList<String>();
						dateList.add(tmp.get("DAY_").toString());
						k.add(tmp.get("OPEN_").toString());
						k.add(tmp.get("PRECLOSE_").toString());
						k.add(tmp.get("LOW_").toString());
						k.add(tmp.get("HIGH_").toString());
						// 开盘，收盘，最低，最高
						lst.add(k);
					}catch(Exception e){
						log.debug(e.toString());
					}
				}
			}else {
				return JSONArray.fromObject("[]");
			}
			long time = DateUtil.date2HHmmss(new Date());
			if(time<=153000&&time>90000){
				// 在行情交易时间内，新增一个当日k点,每个交易日凌晨5点获取上一个交易日的收盘行情，所以在开盘时间9：30-5:00之间都需要
				try {
					String sql_k = "SELECT * FROM realtimequtoes where code_='"+ code+ "' and open_>0";
					logger.debug(sql_k);
					KPoint todayk = dao.queryForObject(sql_k, new KPoint());
					k = new ArrayList<String>();
					k.add(todayk.getOpen().toString());
					k.add(todayk.getPrice().toString());
					k.add(todayk.getLow().toString());
					k.add(todayk.getHigh().toString());
					lst.add(k);
					dateList.add(DateUtil.date2long8(new Date()).toString());
				} catch (Exception e) {
					logger.debug("当日最新K点获取异常！可忽略。" + e.toString());
				}
			}
			map.put("name", QutoesContants.STOCKMAP.get(code.trim()));
			map.put("ks", lst);
			map.put("code", code);
			map.put("dates", dateList);
		}
		return JSONArray.fromObject(map);
	}

	@RequestMapping("/list")
	@ResponseBody
	public JSONArray list(HttpServletRequest request, String code) {
		List<String> lst = new ArrayList<String>();
		lst.add("haha");
		lst.add("123");
		lst.add("67");
		return JSONArray.fromObject(lst);
	}

	@RequestMapping("/map")
	@ResponseBody
	public JSONArray map(HttpServletRequest request, String code) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "haha");
		map.put("age", "20");
		return JSONArray.fromObject(map);
	}

	@RequestMapping("/lm")
	@ResponseBody
	public JSONArray lm(HttpServletRequest request, String code) {
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("name", "haha");
		map1.put("age", "20");
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("name", "xixi");
		map2.put("age", "56");
		List<Map<String,Object>> lst = new ArrayList<Map<String,Object>>();
		lst.add(map2);
		lst.add(map1);
		return JSONArray.fromObject(lst);
	}

	class RetThread implements Callable<String> {

		@Override
		public String call() throws Exception {
			try {
				Thread.sleep(1000);
				int i = 4 / 0;
			} catch (Exception e) {

				return "error!";
			}
			return "success";
		}

	}

	class ComThread implements Runnable {

		@Override
		public void run() {
			int i = 0;
			while (i++ < 5) {
				try {
					Thread.sleep(1000);
					int t = 4 / 0;
				} catch (Exception e) {
					logger.info("error!");
				}
			}

		}
	}

	@RequestMapping(value = "/url")
	@ResponseBody
	public String getPushMap() {
		String url = PropertyUtil.getValueByKey("App", "pushurl");
		return url;
	}

	@RequestMapping(value = "/codes")
	public ModelAndView codes(String condition) {
		ModelAndView view = new ModelAndView();
		view.setViewName("qutoes/codes");
		if (condition == null || "".equals(condition)
				|| "null".equals(condition)) {
			view.addObject("lst", JSONArray.fromObject("[]"));
		} else {
			List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
			Iterator it = QutoesContants.STOCKMAP.entrySet().iterator();
			Map<String, Object> map = null;
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				if (key.contains(condition) || value.contains(condition)) {
					map = new HashMap<String, Object>();
					map.put("code", key);
					map.put("name", value);
					lst.add(map);
				}
			}
			view.addObject("lst", lst);
		}
		return view;
	}

	@RequestMapping(value = "/search")
	public ModelAndView search(String condition)
			throws UnsupportedEncodingException {
		logger.info("查询条件：" + condition);
		ModelAndView view = new ModelAndView();
		view.setViewName("qutoes/index");
		if (condition == null || "".equals(condition)
				|| "null".equals(condition)) {
			view.addObject("lst", JSONArray.fromObject("[]"));
		} else {
			List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
			Iterator it = QutoesContants.STOCKMAP.entrySet().iterator();
			Map<String, Object> map = null;
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				if (key.contains(condition) || value.contains(condition)) {
					map = new HashMap<String, Object>();
					map.put("code", key);
					map.put("name", value);
					lst.add(map);
				}
			}
			view.addObject("lst", lst);
		}
		view.addObject("condition", condition);
		return view;
	}

	@RequestMapping(value = "/searchJson")
	@ResponseBody
	public JSONArray searchJson(String condition)
			throws UnsupportedEncodingException {
		logger.info("查询条件：" + condition);
		String s1 = new String(condition.getBytes("ISO-8859-1"), "GBK");
		String condition1 = new String(condition.getBytes("ISO-8859-1"),
				"utf-8");
		logger.info("查询条件：" + condition1 + "," + s1);
		Map<String, Object> view = new HashMap<String, Object>();
		if (condition == null || "".equals(condition)
				|| "null".equals(condition)) {
			view.put("lst", JSONArray.fromObject("[]"));
		} else {
			List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
			Iterator it = QutoesContants.STOCKMAP.entrySet().iterator();
			Map<String, Object> map = null;
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				if (key.contains(condition1) || value.contains(condition1)) {
					map = new HashMap<String, Object>();
					map.put("code", key);
					map.put("name", value);
					lst.add(map);
				}
			}
			int count = 192;
			if (lst.size() < 192) {
				count = lst.size();
			}
			view.put("lst", lst.subList(0, count));
		}
		view.put("condition", condition);
		return JSONArray.fromObject(view);
	}

	@RequestMapping(value = "/retObj")
	// , produces = "application/json"
	@ResponseBody
	public User retObject(HttpServletRequest request, String code) {
		User user = new User();
		user.setUsername("chenyb");
		logger.info(user);
		return user;
	}

	public static void main(String[] args) {
		String x = "2016-01-05 09:30";
		System.out.println(x.substring(11, x.length()));
		// AdminController.RetThread task1 = new AdminController().new
		// RetThread();
		// task1.call();//String ret =
		// System.out.println("main thread over! task1 ");
		/*
		 * AdminController.ComThread task1 = new AdminController().new
		 * ComThread(); new Thread(task1).start();
		 */

		String str = "sdchenfs哈喽f";
		System.out.println(str.contains("哈喽123"));
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("1111", "2222");
		Iterator it = tempMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			System.out
					.println(tempMap + "," + "key=" + key + " value=" + value);
		}
	}

	/**
	 * 用户自选股列表
	 * 
	 * @param request
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/concern", method = RequestMethod.GET)
	public JSONArray userConcern(HttpServletRequest request, String username) {
		try {
			if(username==null||"".equals(username)){ 
			     username = request.getSession().getAttribute("username").toString(); 
			}
			if (username == null){
			   return JSONArray.fromObject("[]");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(e.toString());
			return JSONArray.fromObject("[]");
		}
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		Map<String, String> stock = null;
		List<Map<String, Object>> lst = this.userService
				.getUserConcernCode(username.toString());
		for (int i = 0; i < lst.size(); i++) {
			stock = new HashMap<String, String>();
			Object code = lst.get(i).get("CODE_");
			if (code != null) {
				stock.put("CODEURL", code.toString());
				stock.put("CODE_",
						code.toString().substring(2, code.toString().length()));
				Object name = QutoesContants.STOCKMAP.get(code);
				if (name != null) {
					stock.put("NAME_", name.toString());
				}
			} else {
				stock.put("CODE_", "");
			}
			data.add(stock);
			stock = null;
		}
		logger.info("### " + data);
		return JSONArray.fromObject(data);
	}

	/**
	 * 添加自选股票
	 * 
	 * @param request
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addZxgp")
	public String addZxgp(HttpServletRequest request, String code) {
		String username = (String) request.getSession().getAttribute("username");
		JdbcTemplate dao = (JdbcTemplate) SpringUtil.getBean("jdbcTemplate");
		try {
			String findSql = "select count(*) from userconcern where code_='"
					+ code + "' and userid='" + username + "'";
			String insertSql = "insert into userconcern values ('"
					+ UUIDUtils.getUUID() + "','" + code + "','" + username
					+ "',sysdate)";
			logger.info(findSql + "\n" + insertSql);
			int count = dao.queryForInt(findSql);
			if (count > 0) {
				return "true";
			} else {
				dao.execute(insertSql);
			}
			return "true";
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "false";
		}
	}

	@ResponseBody
	@RequestMapping({ "/industrys" })
	public JSONArray getIndustrys(HttpServletRequest request, String code) {
		int top = 0;
		if (QutoesContants.INDUSTRYSORT.size() >= 25)
			top = 25;
		else {
			top = QutoesContants.INDUSTRYSORT.size();
		}
		return JSONArray
				.fromObject(QutoesContants.INDUSTRYSORT.subList(0, top));
	}

	// 自选股
	@RequestMapping({ "/myConcerns" })
	public ModelAndView getConcern(HttpServletRequest request) {
		Object user = request.getSession().getAttribute("username");
		String username = "";
		if (user != null || !"".equals(user)) {
			username = user.toString();
		}
		String sql = " select * from realtimequtoes where "
				+ "  code_ in (SELECT code_ FROM USERCONCERN where  userid='"
				+ username + "') order by VOLUME desc";
		List<Map<String, Object>> data = this.jdbcTemplate.queryForList(sql);
		if (data != null && data.size() > 0) {
			int count = data.size();
			for (int i = 0; i < count; i++) {
				double curPrice = Double.valueOf(data.get(i).get("PRICE_")
						.toString());
				double prePrice = Double.valueOf(data.get(i).get("PRECLOSE_")
						.toString());
				double openPrice = Double.valueOf(data.get(i).get("OPEN_")
						.toString());
				double number = Double.valueOf(data.get(i).get("VOLUME")
						.toString());
				double money = Double.valueOf(data.get(i).get("TURNVOLUME")
						.toString());
				data.get(i).put("VOLUME",
						DataUtils.roundDouble(number / 10000, 2));
				data.get(i).put("TURNVOLUME",
						DataUtils.roundDouble(money / 10000, 2));
				String code = data.get(i).get("CODE_").toString();
				data.get(i).put("CODEONLY", code.substring(2, code.length()));
				data.get(i).put("A1",
						DataUtils.roundDouble(curPrice - prePrice, 2));

				if (openPrice == 0) {// 停牌
					data.get(i).put("A", "--");
					data.get(i).put("color", "gray");
				} else {
					data.get(i).put(
							"A",
							DataUtils.roundDouble((curPrice - prePrice) * 100
									/ prePrice, 2)
									+ "%");
					if ((curPrice - prePrice) > 0) {
						data.get(i).put("color", "red");
					} else if ((curPrice - prePrice) < 0) {
						data.get(i).put("color", "green");
					} else {
						data.get(i).put("A", "--");
						data.get(i).put("color", "gray");
					}
				}
			}
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("qutoes/myconcern");
		view.addObject("data", data);
		view.addObject("title", "自选股票");
		return view;
	}

	// 自选股
	@RequestMapping({ "/myConcernJson" })
	@ResponseBody
	public JSONArray myConcern(HttpServletRequest request) {
		Object user = request.getSession().getAttribute("username");
		String username = "";
		if (user != null || !"".equals(user)) {
			username = user.toString();
		}
		String sql = " select * from realtimequtoes where "
				+ "  code_ in (SELECT code_ FROM USERCONCERN where  userid='"
				+ username + "') order by VOLUME desc";
		System.out.println("自选股票列表：" + sql);
		List<Map<String, Object>> data = this.jdbcTemplate.queryForList(sql);
		try {
			if (data != null && data.size() > 0) {
				int count = data.size();
				for (int i = 0; i < count; i++) {
					double curPrice = Double.valueOf(data.get(i).get("PRICE_")
							.toString());
					double prePrice = Double.valueOf(data.get(i)
							.get("PRECLOSE_").toString());
					double openPrice = Double.valueOf(data.get(i).get("OPEN_")
							.toString());
					double number = Double.valueOf(data.get(i).get("VOLUME")
							.toString());
					double money = Double.valueOf(data.get(i).get("TURNVOLUME")
							.toString());
					data.get(i).put("VOLUME",
							DataUtils.roundDouble(number / 10000, 2));
					data.get(i).put("TURNVOLUME",
							DataUtils.roundDouble(money / 10000, 2));
					String code = data.get(i).get("CODE_").toString();
					data.get(i).put("CODEONLY",
							code.substring(2, code.length()));
					data.get(i).put("A1",
							DataUtils.roundDouble(curPrice - prePrice, 2));
					if (openPrice == 0) {// 停牌
						data.get(i).put("A", "--");
						data.get(i).put("color", "gray");
					} else {
						data.get(i).put(
								"A",
								DataUtils.roundDouble((curPrice - prePrice)
										* 100 / prePrice, 2)
										+ "%");
						if ((curPrice - prePrice) > 0) {
							data.get(i).put("color", "red");
						} else if ((curPrice - prePrice) < 0) {
							data.get(i).put("color", "green");
						} else {
							data.get(i).put("A", "--");
							data.get(i).put("color", "gray");
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", data);
		map.put("time", DateUtil.format(new Date(), "yyyy年MM月dd日HH时mm分ss秒"));
		JSONArray ret = JSONArray.fromObject(map);
		return ret;

	}
  public Integer getTotal(String flag){
	  String sql = "select count(*) as total from realtimequtoes "
				+ " where inDUSTRY <> 'zhzs' ";
		boolean SQLAPP = false;
		if ("sz".equals(flag)) {// 上涨
			sql = sql
					+ " and (price_-preclose_)>0 and open_>0 ";
			SQLAPP = true;
		} else if ("xd".equals(flag)) {// 下跌
			SQLAPP = true;
			sql = sql
					+ " and (price_-preclose_)<0 and open_> 0 ";
		} else if ("tp".equals(flag)) {// 停牌
			sql = sql + " and open_=0 ";
			SQLAPP = true;
		} else if ("0".equals(flag)) {
			sql = sql + " and zdbz=" + flag
					+ " and open_> 0 ";
		} else {
			sql = sql + " and zdbz=" + flag
					+ " and open_> 0 ";
		}
		logger.info("sql= " + sql);
		int total  = this.jdbcTemplate.queryForInt(sql);
		return total;
  }
	// 涨跌分析
	@RequestMapping({ "/zdfx" })
	public ModelAndView zdfx(HttpServletRequest request, String flag,Integer page,String cmd) {
		String title = "";
		ModelAndView view = new ModelAndView();
		view.setViewName("qutoes/qutoesList");
		/*
		 * Object user = request.getSession().getAttribute("username"); String
		 * username = ""; if(user!=null||!"".equals(user)){ username =
		 * user.toString(); }
		 */
		int total = getTotal(flag);
		Pagination initPage =  new Pagination(0,Integer.valueOf(PropertyUtil.get("pageSize")),total);
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
		Pagination page_ =  new Pagination(page,Integer.valueOf(PropertyUtil.get("pageSize")),total);
		String sql = "select * from realtimequtoes where inDUSTRY <> 'zhzs' ";//and rownum>="+page_.getOffset()+" and rownum<="+page_.getCurrentPage()*15;
		boolean SQLAPP = false;
		if ("sz".equals(flag)) {// 上涨
			sql = sql
					+ " and (price_-preclose_)>0 and open_>0 order by VOLUME desc";
			SQLAPP = true;
			title = "上涨榜";
		} else if ("xd".equals(flag)) {// 下跌
			SQLAPP = true;
			sql = sql
					+ " and (price_-preclose_)<0 and open_> 0 order by VOLUME desc";
			title = "下跌榜";
		} else if ("tp".equals(flag)) {// 停牌
			sql = sql + " and open_=0 order by VOLUME desc";
			SQLAPP = true;
			title = "停牌榜";
		} else if ("0".equals(flag)) {
			sql = sql + " and zdbz=" + flag
					+ " and open_> 0 order by VOLUME desc";
			title = "平盘榜";
		} else {
			sql = sql + " and zdbz=" + flag
					+ " and open_> 0 order by VOLUME desc";
		}
		String sql2 = " select xh,* from("+
				" select  rownum as xh,* from("+
				sql+
				" ) "+
				" ) where  xh>="+page_.getOffset()+" and xh<="+page_.getCurrentPage()*page_.getPageSize();
		logger.info("sql= " + sql2);
		List<Map<String, Object>> data = this.jdbcTemplate.queryForList(sql2);
		List<Map<String, Object>> ztData = new ArrayList<Map<String, Object>>();
		if (data != null && data.size() > 0) {
			for (int i = 0; i < data.size(); i++) {
				double curPrice = Double.valueOf(data.get(i).get("PRICE_")
						.toString());
				double prePrice = Double.valueOf(data.get(i).get("PRECLOSE_")
						.toString());
				double openPrice = Double.valueOf(data.get(i).get("OPEN_")
						.toString());
				double number = Double.valueOf(data.get(i).get("VOLUME")
						.toString());
				double money = Double.valueOf(data.get(i).get("TURNVOLUME")
						.toString());
				data.get(i).put("VOLUME",
						DataUtils.roundDouble(number / 10000, 2));
				data.get(i).put("TURNVOLUME",
						DataUtils.roundDouble(money / 10000, 2));
				String code = data.get(i).get("CODE_").toString();
				data.get(i).put("CODEONLY", code.substring(2, code.length()));
				data.get(i).put("A1",
						DataUtils.roundDouble(curPrice - prePrice, 2));
				if (openPrice == 0) {// 停牌
					data.get(i).put("A", "--");
					data.get(i).put("color", "gray");
				} else {
					double A = DataUtils.roundDouble((curPrice - prePrice)
							* 100 / prePrice, 2);
					data.get(i).put(
							"A",
							DataUtils.roundDouble((curPrice - prePrice) * 100
									/ prePrice, 2)
									+ "%");
					if ((curPrice - prePrice) > 0) {
						data.get(i).put("color", "red");
					} else if ((curPrice - prePrice) < 0) {
						data.get(i).put("color", "green");
					} else {
						data.get(i).put("A1", "--");
						data.get(i).put("A", "--");
						data.get(i).put("color", "gray");
					}
				}
			}
		}

		Collections.sort(ztData, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				try {
					double val1 = Double.valueOf(o1.get("A").toString());
					double val2 = Double.valueOf(o2.get("A").toString());
					return val1 > val2 ? (val1 == val2 ? 0 : -1) : 1;
				} catch (NumberFormatException e) {
					return 1;
				}

			}
		});
		if ("1".equals(flag)) {
			title = "涨停榜";
		} else if ("2".equals(flag)) {
			title = "跌停榜";
		} else if ("tp".equals(flag)) {
			title = "停牌榜";
		} else {
		}
		view.addObject("flag", flag);
		view.addObject("page", page_);
		view.addObject("data", data);
		view.addObject("title", title);
		return view;
	}

	public int getHyTotal(String flag){
		String condition="";
		if(!flag.equals("all")){
			condition = condition+" where  inDUSTRY  ='"+ flag+"'";
		}
		String sql = " select count(*) from realtimequtoes "+ condition;
		return this.jdbcTemplate.queryForInt(sql);
	}
	// 行业分类
	@RequestMapping({ "/hyfl" })
	public ModelAndView hyfl(HttpServletRequest request, String flag,Integer page,String cmd) {
		try{
		int total = getHyTotal(flag);
		Pagination page_ = null;// new Pagination(0,Integer.valueOf(PropertyUtil.get("pageSize")),total);
		Pagination page_1 =  new Pagination(0,Integer.valueOf(PropertyUtil.get("pageSize")),total);
		int pageCount = page_1.getPageCount();
		if(cmd!=null&&!"".equals(cmd)){
			if(cmd.equals("pre")){
				page_ =  new Pagination(page-1,Integer.valueOf(PropertyUtil.get("pageSize")),total);
			}else if(cmd.equals("next")){
				page_ =  new Pagination(page+1,Integer.valueOf(PropertyUtil.get("pageSize")),total);
			}else if(cmd.equals("first")){
				page_ =  new Pagination(1,Integer.valueOf(PropertyUtil.get("pageSize")),total);
			}else if(cmd.equals("last")){
				page_ =  new Pagination(pageCount,Integer.valueOf(PropertyUtil.get("pageSize")),total);
			}
		}else{
			page_ =  new Pagination(1,Integer.valueOf(PropertyUtil.get("pageSize")),total);
		}
		String condition="";
		if(!flag.equals("all")){
			condition = condition+"  and  inDUSTRY  ='"+ flag+"' ";
		}
		String sql = 
				"select xh,* from ("+
						"	select rownum xh,* from ( "+
				" select * from REALTIMEQUTOES where 1=1  "
				+ condition
				+ " order by VOLUME desc"+
				" )"+
				" ) where xh>="+page_.getOffset()+" and xh<="+page_.getCurrentPage()*page_.getPageSize() ;
		
		List<Map<String, Object>> data = this.jdbcTemplate.queryForList(sql);
		if (data != null && data.size() > 0) {
			for (int i = 0; i < data.size(); i++) {
				String code = data.get(i).get("CODE_").toString();
				data.get(i).put("CODEONLY", code.substring(2, code.length()));
				double curPrice = Double.valueOf(data.get(i).get("PRICE_")
						.toString());
				double prePrice = Double.valueOf(data.get(i).get("PRECLOSE_")
						.toString());
				double openPrice = Double.valueOf(data.get(i).get("OPEN_")
						.toString());
				double number = Double.valueOf(data.get(i).get("VOLUME")
						.toString());
				double money = Double.valueOf(data.get(i).get("TURNVOLUME")
						.toString());
				data.get(i).put("VOLUME",
						DataUtils.roundDouble(number / 10000, 2));
				data.get(i).put("TURNVOLUME",
						DataUtils.roundDouble(money / 10000, 2));
				data.get(i).put("A1",
						DataUtils.roundDouble(curPrice - prePrice, 2));
				if (openPrice == 0) {// 停牌
					data.get(i).put("A", "--");
					data.get(i).put("color", "gray");
				} else {
					data.get(i).put(
							"A",
							DataUtils.roundDouble((curPrice - prePrice) * 100
									/ prePrice, 2)
									+ "%");
					if ((curPrice - prePrice) > 0) {
						data.get(i).put("color", "red");
					} else if ((curPrice - prePrice) < 0) {
						data.get(i).put("color", "green");
					} else {
						data.get(i).put("A", "--");
						data.get(i).put("color", "gray");
					}
				}
			}
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("qutoes/qutoesListhyfl");
		view.addObject("data", data);
		view.addObject("flag", flag);
		view.addObject("page", page_);
		if(flag.equals("all")){
			view.addObject("title", "全部行业");
		}else{
			view.addObject("title", QutoesContants.INDUSTRYSORTMAP.get(flag));
		}
		 return view;
		}catch(Exception e){
			e.printStackTrace();
			return new ModelAndView();
			
		}
	}

	@RequestMapping({ "/deleteMyConcern" })
	public ModelAndView deleteMyConcern(HttpServletRequest request,
			String code, String userid) {
		Object user = request.getSession().getAttribute("username");
		String username = "";
		if (user != null || !"".equals(user)) {
			username = user.toString();
		}
		String sql = "delete USERCONCERN where code_='" + code
				+ "' and userid='" + username + "'";
		logger.info("删除自选：" + sql);
		this.jdbcTemplate.execute(sql);
		ModelAndView view = new ModelAndView();
		view.setViewName("qutoes/myconcern");
		view.addObject("title", "");
		return view;
	}
    public Integer getTotalZs(){
    	String sql = " select count(*) from REALTIMEQUTOES  where  inDUSTRY = 'zhzs'";
    	return this.jdbcTemplate.queryForInt(sql);
    }
	@RequestMapping({ "/morezs" })
	public ModelAndView morezs(HttpServletRequest request,Integer page,String cmd) {
		ModelAndView view = new ModelAndView();
		try {
			int total = getTotalZs();
			Pagination initPage =  new Pagination(0,Integer.valueOf(PropertyUtil.get("pageSize")),total);
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
			Pagination page_ =  new Pagination(page,Integer.valueOf(PropertyUtil.get("pageSize")),total);
			String sql = 
					"select xh,* from ("+
						"	select rownum xh,* from ( "+
										" select * from REALTIMEQUTOES  where inDUSTRY = 'zhzs' order by code_ asc"+
							" )"+
					 " ) where xh>="+page_.getOffset()+" and xh<="+page_.getCurrentPage()*page_.getPageSize() ;
			List<Map<String, Object>> data = this.jdbcTemplate.queryForList(sql);
			if (data != null && data.size() > 0) {
				int count = data.size();
				for (int i = 0; i < count; i++) {
					double curPrice = Double.valueOf(data.get(i).get("PRICE_")
							.toString());
					double prePrice = Double.valueOf(data.get(i).get("PRECLOSE_")
							.toString());
					double openPrice = Double.valueOf(data.get(i).get("OPEN_")
							.toString());
					double number = Double.valueOf(data.get(i).get("VOLUME")
							.toString());
					double money = Double.valueOf(data.get(i).get("TURNVOLUME")
							.toString());
					data.get(i)
							.put("VOLUME", DataUtils.roundStr(number / 10000, 2));
					data.get(i).put("TURNVOLUME",
							DataUtils.roundStr(money / 10000, 2));
					String code = data.get(i).get("CODE_").toString();
					data.get(i).put("CODEONLY", code.substring(2, code.length()));
					data.get(i).put("A1",
							DataUtils.roundDouble(curPrice - prePrice, 2));

					if (openPrice == 0) {// 停牌
						data.get(i).put("A", "--");
						data.get(i).put("color", "gray");
					} else {
						data.get(i).put(
								"A",
								DataUtils.roundDouble((curPrice - prePrice) * 100
										/ prePrice, 2)
										+ "%");
						if ((curPrice - prePrice) > 0) {
							data.get(i).put("color", "red");
						} else if ((curPrice - prePrice) < 0) {
							data.get(i).put("color", "green");
						} else {
							data.get(i).put("A", "--");
							data.get(i).put("color", "gray");
						}
					}
				}
			}
			
			view.setViewName("qutoes/qutoesListzs");
			view.addObject("data", data);
			view.addObject("page", page_);
			view.addObject("title", "沪深指数");
			return view;
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();
			return view;
		} 
	}

}
