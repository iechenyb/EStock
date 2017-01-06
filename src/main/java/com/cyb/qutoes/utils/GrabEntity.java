package com.cyb.qutoes.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class GrabEntity {
//	public static String cfcenter = "http://quote.eastmoney.com/stocklist.html";
//	public static String codeFilePath = "D:\\chenyb\\cash\\programme";
//	public static String codeFileName = "code.html";
	/*public static void grabCodeFromCFCenter(){
		try {
			File codeFile = new File(codeFilePath+File.separator+codeFileName);
			if(!codeFile.exists()){
				downLoadFromUrl(cfcenter,codeFileName,codeFilePath);
			}
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(codeFile),"GBK"));
			String content = "";
			while((content = reader.readLine())!=null){
				if(content.contains("<li><a")){
					System.out.println(content);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	 public static void  downLoadFromUrl(String urlStr,String savePath) throws IOException{
	        URL url = new URL(urlStr);  
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	                //设置超时间为3秒
	        conn.setConnectTimeout(3*1000);
	        //防止屏蔽程序抓取而返回403错误
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

	        //得到输入流
	        InputStream inputStream = conn.getInputStream();  
	        //获取自己数组
	        byte[] getData = readInputStream(inputStream);    

	        //文件保存位置
	        File file = new File(savePath);    
	        FileOutputStream fos = new FileOutputStream(file);     
	        fos.write(getData); 
	        if(fos!=null){
	            fos.close();  
	        }
	        if(inputStream!=null){
	            inputStream.close();
	        }
	        System.out.println("info:"+url+" download success"); 
	    }
	 public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
	        byte[] buffer = new byte[1024];  
	        int len = 0;  
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	        while((len = inputStream.read(buffer)) != -1) {  
	            bos.write(buffer, 0, len);  
	        }  
	        bos.close();  
	        return bos.toByteArray();  
	    }  
  public static List<String> match(String source, String element, String attr) {
			List<String> result = new ArrayList<String>();
			String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s.*?>";
			Matcher m = Pattern.compile(reg).matcher(source);
			while (m.find()) {
				String r = m.group(1);
				result.add(r);
			}
			return result;
	}
    public static Map<String,String> getCodeInfor(String li){
    	Map<String,String> data = new HashMap<String, String>();
        Parser parser = Parser.createParser(li, "gbk");	
        RegexFilter filter = new RegexFilter("[<a]*");
        try {
			NodeList tds = parser.extractAllNodesThatMatch(filter);
			Node a  = null ;
			String exchange = "" ;
			String code = "" ;
			String name = "" ;
			for(int i=0;i<tds.size();i++){
				try {
					a = tds.elementAt(i);
					if(a.getParent()!=null){
						String aHtml = a.getParent().toHtml();//<a target='_blank' href='http://quote.eastmoney.com/sz300447.html' >全信股份(300447)</a>
						List<String> list = match(aHtml.replace(">", " >"), "a","href");
						String href = list.get(0);//http://quote.eastmoney.com/sz300447.html
						String codeInfor = href.substring(
								href.lastIndexOf("/") + 1, href.length() - 5);
						exchange = codeInfor.substring(0, 2);
						code = codeInfor.substring(2, codeInfor.length());
					}
					String aTips = a.toHtml();//全信股份(300447)
					name = aTips.substring(0, aTips.lastIndexOf("("));
					if(code!=null&&!"".equals(code)&&!"null".equals(code)){
						data.put("name", name);
						data.put("code", code);
						data.put("exchange", exchange);
					}
					
				} catch (Exception e) {
//					e.printStackTrace();
					/*if(a!=null){
						System.out.println("###"+a.toHtml()+"处理异常！");
					}*/
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
        return data;
    }
    public static String grabJsonDataFromURL(String urlStr){
    	URL url =null;  
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        BufferedReader reader = null;
        String data = "";
		try {
			url = new URL(urlStr);  
			conn = (HttpURLConnection)url.openConnection();
			 //设置超时间为3秒
	        conn.setConnectTimeout(3*1000);
			//得到输入流
            inputStream = conn.getInputStream();  
            reader  = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
            data = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}  
    	return data;
    }
	public static void main(String[] args) {
		String str = "<li><a target=_blank href=http://quote.eastmoney.com/sz300498.html>温氏股份(300498)</a></li>";
		Map<String,String> data = getCodeInfor(str);
		String data0 ="var hq_str_sz300498=\"温氏股份,47.750,47.820,46.560,47.750,46.270,46.550,46.560,3578083,167478143.410,7300,46.550,17435,46.540,200,46.510,7300,46.500,400,46.480,900,46.560,1700,46.590,6200,46.600,2100,46.620,400,46.630,2015-12-24,11:35:55,00\"";
		System.out.println(data0.replace("\"", ""));
		String da = data0.replace("\"", "").split("=")[1];
		System.out.println(da);
		
	}
}
