package com.cyb.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import sun.misc.*; 
public class Base64Encoder {
	private static BASE64Encoder encoder = new BASE64Encoder();  
    private static BASE64Decoder decoder = new BASE64Decoder();  
    public static String encodeBufferBase64(byte[] buff)  
    {  
        return buff == null?null:encoder.encodeBuffer(buff).trim();  
    }  
    public static byte[] decodeBufferBase64(String s)  
    {  
        try  
        {  
            return s == null ? null : decoder.decodeBuffer(s);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }   
        return null;  
    }  
    public static String encodeBytes(byte[] bytes) throws IOException  
    {  
        return new BASE64Encoder().encode(bytes).replace("\n", "").replace("\r", "");  
    }  
  
    /** 
     * base64解码 
     *  
     * @param bytes 
     *            字符数组 
     * @return 
     * @throws IOException 
     */  
    public static String decodeBytes(byte[] bytes) throws IOException  
    {  
        return new String(new BASE64Decoder().decodeBuffer(new String(bytes)));  
    }  
	public static String getBase64(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new BASE64Encoder().encode(b);
//			s = new BASE64Encoder().encodeUTFBytes(b);
		}
		return s;
	}

	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public static String encode(String content){
		if(content!=null&&!"".equals(content)){
			return encodeBufferBase64(content.getBytes());
		}else{
			return "";
		} 
	}
	public static String decode(String content){
		if(content!=null&&!"".equals(content)){
			return new String(decodeBufferBase64(content));
		}else{
			return "";
		} 
	}
	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
		String str1="我爱你";  
		String str2="123456";  
        String s=encode(str1);  
        System.out.println(s);  
        String strs=decode(s);   
        System.out.println(strs);  
        System.out.println("-----------");
        String s1=encode(str2);  
        System.out.println(s1);  
        String strs1=decode(s1);   
        System.out.println(strs1);  
	}

	
}
