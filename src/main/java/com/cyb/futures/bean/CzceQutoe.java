package com.cyb.futures.bean;

public class CzceQutoe {
 /**
   * 					郑州商品交易所历史行情下载(2010)
        交易日期0	|品种月份1	|昨结算2	|今开盘3	|最高价4	|最低价5	|今收盘6	|今结算7	|涨跌1 8	|
        涨跌2 9	|成交量(手)10|空盘量11	|增减量12	|成交额(万元)13	|交割结算价14	|
   */
	public String id="";
	public String secuid="";
	public double preSettle=0.0;
	public double open=0.0;
	public double high=0.0;
	public double low=0.0;
	public double close=0.0;
	public double settle=0.0;
	public double zd1=0.0;
	public double zd2=0.0;
	public double cjl =0.0;
	public double kpl=0.0;
	public double zjl=0.0;
	public double cje=0.0;
	public double jgSettle=0.0;
	public String exchange = "";
	public String product;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSecuid() {
		return secuid;
	}
	public void setSecuid(String secuid) {
		this.secuid = secuid;
	}
	public double getPreSettle() {
		return preSettle;
	}
	public void setPreSettle(double preSettle) {
		this.preSettle = preSettle;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getSettle() {
		return settle;
	}
	public void setSettle(double settle) {
		this.settle = settle;
	}
	public double getZd1() {
		return zd1;
	}
	public void setZd1(double zd1) {
		this.zd1 = zd1;
	}
	public double getZd2() {
		return zd2;
	}
	public void setZd2(double zd2) {
		this.zd2 = zd2;
	}
	public double getCjl() {
		return cjl;
	}
	public void setCjl(double cjl) {
		this.cjl = cjl;
	}
	public double getKpl() {
		return kpl;
	}
	public void setKpl(double kpl) {
		this.kpl = kpl;
	}
	public double getZjl() {
		return zjl;
	}
	public void setZjl(double zjl) {
		this.zjl = zjl;
	}
	public double getCje() {
		return cje;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public void setCje(double cje) {
		this.cje = cje;
	}
	public double getJgSettle() {
		return jgSettle;
	}
	public void setJgSettle(double jgSettle) {
		this.jgSettle = jgSettle;
	}
}
