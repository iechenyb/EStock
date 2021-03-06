package com.cyb.futures.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class KPoint implements RowMapper<KPoint>{
  public String record_date;
  public Double open=0.0;
  public Double high=0.0;
  public Double low=0.0;
  public Double close=0.0;
	@Override
	public KPoint mapRow(ResultSet rs, int arg1) throws SQLException {
		KPoint k = new KPoint();
		k.setRecord_date(rs.getString("RECORD_DATE"));
		k.setHigh(rs.getDouble("HIGH"));
		k.setOpen(rs.getDouble("OPEN"));
		k.setLow(rs.getDouble("LOW"));
		k.setClose(rs.getDouble("CLOSE"));
		return k;
	}
	public String getRecord_date() {
		return record_date;
	}
	public void setRecord_date(String record_date) {
		this.record_date = record_date;
	}
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getClose() {
		return close;
	}
	public void setClose(Double close) {
		this.close = close;
	}
}
