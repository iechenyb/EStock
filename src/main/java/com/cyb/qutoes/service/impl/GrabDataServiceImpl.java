package com.cyb.qutoes.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyb.qutoes.dao.GrabDataDao;
import com.cyb.qutoes.service.GrabDataService;

@Service("grabDataService")
public class GrabDataServiceImpl implements GrabDataService{
	@Resource(name = "grabDataDao")
	public GrabDataDao dao;
	
	@Override
	public void persisCodeInfor() {
		this.dao.persisCodeInfor();
	}
	@Override
	public List getAllCodeInfor() {
		return this.dao.getAllCodeInfor();
	}
	@Override
	public void persisDayQutoes(String stockCode) {
		this.dao.persisDayQutoes(stockCode);
	}
	@Override
	public List getAllDayQutoes(String stockCode) {
		return this.dao.getAllDayQutoes(stockCode);
	}
	@Override
	public Map getNDaysUpStocks(int days,String condition) {
		return this.dao.getNDaysUpStocks(days,condition);
	}
	@Override
	public Map getNDaysDownStocks(int days,String condition) {
		return this.dao.getNDaysDownStocks(days,condition);
	}
}
