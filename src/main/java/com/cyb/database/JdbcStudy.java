package com.cyb.database;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cyb.qutoes.constant.SpringUtil;
import com.cyb.qutoes.vo.Stock;

public class JdbcStudy {
  public void query(){
		JdbcTemplate dao = (JdbcTemplate) SpringUtil.getBean("jdbcTemplate");
		List<Stock> userInfos = dao.query("select * from stock", new Stock());
		System.out.println("######## :"+userInfos);
  }
}
