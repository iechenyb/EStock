package com.cyb.utils;

import com.cyb.controller.UserController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;

public class FileUtils
{
  public static Logger log = Logger.getLogger(UserController.class);
  static File tmpDir = null;
  static File saveDir = null;

  public static void readFileByBytes(String fileName)
  {
    File file = new File(fileName);
    file.getName();
    InputStream in = null;
    try {
      System.out.println("以字节为单位读取文件内容，一次读一个字节：");

      in = new FileInputStream(file);
      int tempbyte;
      while ((tempbyte = in.read()) != -1)
      {
        System.out.write(tempbyte);
      }
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    try {
      System.out.println("以字节为单位读取文件内容，一次读多个字节：");

      byte[] tempbytes = new byte[100];
      int byteread = 0;
      in = new FileInputStream(fileName);
      showAvailableBytes(in);

      while ((byteread = in.read(tempbytes)) != -1)
        System.out.write(tempbytes, 0, byteread);
    }
    catch (Exception e1) {
      e1.printStackTrace();

      if (in != null)
        try {
          in.close();
        }
        catch (IOException localIOException1)
        {
        }
    }
    finally
    {
      if (in != null)
        try {
          in.close();
        }
        catch (IOException localIOException2)
        {
        }
    }
  }

  public static void readFileByChars(String fileName)
  {
    File file = new File(fileName);
    Reader reader = null;
    try {
      System.out.println("以字符为单位读取文件内容，一次读一个字节：");

      reader = new InputStreamReader(new FileInputStream(file));
      int tempchar;
      while ((tempchar = reader.read()) != -1)
      {
        if ((char)tempchar != '\r') {
          System.out.print((char)tempchar);
        }
      }
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      System.out.println("以字符为单位读取文件内容，一次读多个字节：");

      char[] tempchars = new char[30];
      int charread = 0;
      reader = new InputStreamReader(new FileInputStream(fileName));

      while ((charread = reader.read(tempchars)) != -1)
      {
        if ((charread == tempchars.length) && 
          (tempchars[(tempchars.length - 1)] != '\r'))
          System.out.print(tempchars);
        else {
          for (int i = 0; i < charread; i++) {
            if (tempchars[i] == '\r') {
              continue;
            }
            System.out.print(tempchars[i]);
          }
        }
      }
    }
    catch (Exception e1)
    {
      e1.printStackTrace();

      if (reader != null)
        try {
          reader.close();
        }
        catch (IOException localIOException)
        {
        }
    }
    finally
    {
      if (reader != null)
        try {
          reader.close();
        }
        catch (IOException localIOException1)
        {
        }
    }
  }

  public static StringBuffer readFileByLines(String fileName)
  {
    StringBuffer content = new StringBuffer("");
    File file = new File(fileName);
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));

      String tempString = null;
      int line = 1;

      while ((tempString = reader.readLine()) != null)
      {
        content.append(tempString.trim() + ",");
      }

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();

      if (reader != null)
        try {
          reader.close();
        }
        catch (IOException localIOException1)
        {
        }
    }
    finally
    {
      if (reader != null)
        try {
          reader.close();
        }
        catch (IOException localIOException2) {
        }
    }
    return content;
  }

  public static void readFileByRandomAccess(String fileName)
  {
    RandomAccessFile randomFile = null;
    try {
      System.out.println("随机读取一段文件内容：");

      randomFile = new RandomAccessFile(fileName, "r");

      long fileLength = randomFile.length();

      int beginIndex = fileLength > 4L ? 4 : 0;

      randomFile.seek(beginIndex);
      byte[] bytes = new byte[10];
      int byteread = 0;

      while ((byteread = randomFile.read(bytes)) != -1)
        System.out.write(bytes, 0, byteread);
    }
    catch (IOException e) {
      e.printStackTrace();

      if (randomFile != null)
        try {
          randomFile.close();
        }
        catch (IOException localIOException1)
        {
        }
    }
    finally
    {
      if (randomFile != null)
        try {
          randomFile.close();
        }
        catch (IOException localIOException2)
        {
        }
    }
  }

  private static void showAvailableBytes(InputStream in)
  {
    try
    {
      System.out.println("当前字节输入流中的字节数为:" + in.available());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void copyFile(String src, String dest) throws IOException {
    try {
      FileInputStream in = new FileInputStream(src);
      File file = new File(dest);
      if (!file.exists()) {
        file.createNewFile();
      }
      FileOutputStream out = new FileOutputStream(file);

      byte[] buffer = new byte[1024];
      int c;
      while ((c = in.read(buffer)) != -1)
      {
        for (int i = 0; i < c; i++)
          out.write(buffer[i]);
      }
      in.close();
      out.close();
    } catch (Exception e) {
      log.info(e.toString());
    }
  }

  public static void copyFile(File src, String dest) throws IOException {
    try {
      FileInputStream in = new FileInputStream(src);
      File file = new File(dest);
      if (!file.exists())
        file.createNewFile();
      FileOutputStream out = new FileOutputStream(file);

      byte[] buffer = new byte[1024];
      int c;
      while ((c = in.read(buffer)) != -1)
      {
        for (int i = 0; i < c; i++)
          out.write(buffer[i]);
      }
      in.close();
      out.close();
    } catch (Exception e) {
      log.info(e.toString());
    }
  }

  public static void copyFileByStream(InputStream src, String dest) {
    try {
      File file = new File(dest);
      if (!file.exists())
        file.createNewFile();
      FileOutputStream out = new FileOutputStream(dest);

      byte[] buffer = new byte[1024];
      int c;
      while ((c = src.read(buffer)) != -1)
      {
        for (int i = 0; i < c; i++)
          out.write(buffer[i]);
      }
      src.close();
      out.close();
    } catch (FileNotFoundException e) {
      log.info(e.toString());
    } catch (IOException e) {
      log.info(e.toString());
    }
  }

  public static String MutilUpload(HttpServletRequest request) {
    String fileId = "";
    try {
      initByProperties();
      if (ServletFileUpload.isMultipartContent(request)) {
        DiskFileItemFactory dff = new DiskFileItemFactory();
        dff.setRepository(tmpDir);
        dff.setSizeThreshold(1024000);
        ServletFileUpload sfu = new ServletFileUpload(dff);
        sfu.setFileSizeMax(5000000L);
        sfu.setSizeMax(10000000L);
        FileItemIterator fii = sfu.getItemIterator(request);
        while (fii.hasNext()) {
          FileItemStream fis = fii.next();
          if ((!fis.isFormField()) && (fis.getName().length() > 0)) {
            String realFileName = fis.getName();
            String fileName = UUIDUtils.getUUID();
            String subfix = realFileName.substring(realFileName.indexOf("."), realFileName.length());
            BufferedInputStream in = new BufferedInputStream(fis.openStream());
            BufferedOutputStream out = new BufferedOutputStream(
              new FileOutputStream(new File(saveDir + File.separator + fileName + subfix)));
            Streams.copy(in, out, true);
            in.close();
            out.close();
          }
        }
      }
    } catch (Exception e) {
      log.info(e.toString());
      return "";
    }
    return fileId;
  }
  public static void initByProperties() throws ServletException {
    String tmpPath = PropertyUtil.getValueByKey("App", "defaultTampUploadPath");
    String savePath = PropertyUtil.getValueByKey("App", "defaultUploadPath");
    tmpDir = new File(tmpPath);
    saveDir = new File(savePath);
    if (!tmpDir.isDirectory())
      tmpDir.mkdir();
    if (!saveDir.isDirectory())
      saveDir.mkdir(); 
  }

  public static void init() throws ServletException {
    String tmpPath = "d://tmpdir";
    String savePath = "d://updir";
    tmpDir = new File(tmpPath);
    saveDir = new File(savePath);
    if (!tmpDir.isDirectory())
      tmpDir.mkdir();
    if (!saveDir.isDirectory())
      saveDir.mkdir(); 
  }
  public static List<File> readDirectory(String path){
	  File file = new File(path);
	  List<File> files = new ArrayList<File>();
	  if(file.exists()&&file.isDirectory()){
		  File childs[] = file.listFiles();
		  for(int i=0;i<childs.length;i++){
			  if(childs[i].isFile()){
				  files.add(childs[i]);
			  }
		  }
	  }
	  return files;
  }
  public static void main(String[] args) {
    String fileName = "d:\\file\\industry.txt";
    System.out.println(readFileByLines(fileName));
  }
}