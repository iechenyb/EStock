package com.cyb.futures.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.cyb.futures.contants.CzceContants;
import com.cyb.utils.UUIDUtils;

public class CzceUtils {
	public static void main(String[] args) {
		File file = new File("C:\\Users\\DHUser\\Downloads\\datahistory2010.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
			String record = null;
			reader.readLine();
			reader.readLine();
			List<String> sqls = new ArrayList<String>();
			int count =0;
			String sql = "";
			String insert = "insert into CZCE(id,record_date,secuid,exchange,product,"
					+ "presettle,open,high,low,close,settle,zd1,zd2,"
					+ "cjss,kpl,zjl,cje,jgjsj,import_time) ";
			StringBuffer value = new StringBuffer("");
			while ((record = reader.readLine()) != null&&!record.equals("")) {
				String obj[] = record.trim().replace("|", "#").split("#");
				if(obj.length>0){				
					//System.out.println(obj[CzceContants.CZCE_SECUID]);
					value.append("values ");
					value.append("(");
					value.append("'"+UUIDUtils.getUUID()+"'");
					value.append(","+obj[CzceContants.CZCE_DATE].replace("-", ""));
					value.append(",'"+obj[CzceContants.CZCE_SECUID].trim()+"'");
					value.append(",'CZCE'");
					value.append(",'"+obj[CzceContants.CZCE_SECUID].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_PRESETTLE].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_OPEN].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_HIGH].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_LOW].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_CLOSE].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_SETTLE].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_ZD1].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_ZD2].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_CJL].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_KPL].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_ZJL].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_CJE].trim()+"'");
					value.append(",'"+obj[CzceContants.CZCE_JGSETTLE].trim()+"'");
					value.append(",sysdate");
					value.append(")");
					sqls.add(insert+value);
				}
				count++;
			}
			System.out.println("处理记录总数："+count);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (reader != null)
				try {
					reader.close();
				} catch (IOException localIOException1) {
				}
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException localIOException2) {
				}
		}
	}
}
