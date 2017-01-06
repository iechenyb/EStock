package com.cyb.qutoes.service;

import java.util.List;
import java.util.Map;

public interface GrabDataService {
	public void persisCodeInfor();
	public List getAllCodeInfor();
	public void persisDayQutoes(String stockCode);
	public List getAllDayQutoes(String stockCode);
	public Map getNDaysUpStocks(int days,String condition);
	public Map getNDaysDownStocks(int days,String condition);
}
