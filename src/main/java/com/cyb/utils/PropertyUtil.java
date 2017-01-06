package com.cyb.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.qutoes.constant.QutoesContants;

public class PropertyUtil {

	private static Properties p = null;
	static Log log = LogFactory.getLog(PropertyUtil.class);
	public synchronized static void init(String propertyName) throws Exception {
		InputStream inputstream = null;
		try {
			if (p == null) 
			{
				p = new Properties();
				String filePath = QutoesContants.WEBPATH + "WEB-INF"+ File.separator +"classes"+ File.separator + propertyName + ".properties";
				log.info("初始化属性文件:"+filePath);
				inputstream = new FileInputStream(filePath);
				p.load(inputstream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(inputstream!=null){
				inputstream.close();
				inputstream = null;
			}
		}
	}

   public static String getValueByKey(String propertyName, String key) {
		String result = "";
		try {
			init(propertyName);
			result = p.getProperty(key);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String get(String key) {
		String result = "";
		try {
			init("App");
			result = p.getProperty(key);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static void main(String[] s) {
		 System.out.println(PropertyUtil.getValueByKey("App.properties","cfcenter"));
	}
}