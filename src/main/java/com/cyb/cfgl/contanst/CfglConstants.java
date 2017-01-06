package com.cyb.cfgl.contanst;

import java.util.HashMap;
import java.util.Map;

public class CfglConstants {
  public static Map<String,String> excludeUrl = new HashMap<String,String>();
  static
  {
	excludeUrl.put("/cfgl/login.zc", "/cfgl/login.zc");
	excludeUrl.put("/ws/searchJson.zc", "/ws/searchJson.zc");
	excludeUrl.put("/ws/loginphone.zc", "/ws/loginphone.zc");
  }
}
