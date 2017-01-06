package com.cyb.qutoes.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

public class QutoesContants {
  public static String WEBPATH = null;
  public static List<Map<String,Object>> STOCKLIST= null;
  //<code,name> = <sh600868,meiyanjixiang>
  public static Map<String,Object> STOCKMAP = null;
  public static int NAME = 0;
  public static int OPEN = 1;
  public static int PRECLOSE = 2;
  public static int PERICE = 3;
  public static int HIGH = 4;
  public static int LOW = 5;
  public static int BUYONE = 6;
  public static int SALEONE = 7;
  public static int COLUMN = 8;
  public static int COLUMNCASH = 9;
  public static int DAY = 30;
  public static int TIME = 31;
  public static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
  public static final Map<String ,List<String>> KLineOfTODAY= new HashMap<String, List<String>>();
  //http://blog.sina.com.cn/s/blog_49d43d390102drix.html
  /*0：”大秦铁路”，股票名字；
  1：”27.55″，今日开盘价；
  2：”27.25″，昨日收盘价；
  3：”26.91″，当前价格；
  4：”27.55″，今日最高价；
  5：”26.20″，今日最低价；
  6：”26.91″，竞买价，即“买一”报价；
  7：”26.92″，竞卖价，即“卖一”报价；
  8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
  9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
  10：”4695″，“买一”申请4695股，即47手；
  11：”26.91″，“买一”报价；
  12：”57590″，“买二”
  13：”26.90″，“买二”
  14：”14700″，“买三”
  15：”26.89″，“买三”
  16：”14300″，“买四”
  17：”26.88″，“买四”
  18：”15100″，“买五”
  19：”26.87″，“买五”
  20：”3100″，“卖一”申报3100股，即31手；
  21：”26.92″，“卖一”报价
  (22, 23), (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
  30：”2008-01-11″，日期；
  31：”15:05:32″，时间；*/
public static final String LASTESTDAY = null;
public static List<Map<String,String>> INDUSTRYSORT = null;
public static Map<String,String> INDUSTRYSORTMAP = null;
//<sh600868,A>
public static Map<String,String> CODEINDUSTRY = null;
}
