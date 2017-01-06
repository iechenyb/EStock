package com.cyb.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageMergeUtils {
	 public static String negativeImagePath = "D:/chenyb/pic/t1.jpg";
	 public static String additionImagePath = "D:/chenyb/pic/t2.jpg";
	 public static int x = 200;
	 public static int y = 200;
	 public static String toPath = "D:/chenyb/pic/new/t1t2.jpg";
	 /**
	     * 合并图片(按指定初始x、y坐标将附加图片贴到底图之上)
	     * @param negativeImagePath 背景图片路径
	     * @param additionImagePath 附加图片路径
	     * @param x 附加图片的起始点x坐标
	     * @param y  附加图片的起始点y坐标
	     * @param toPath 图片写入路径
	     * @throws IOException
	     */
	    public static void mergeBothImage(String negativeImagePath,String additionImagePath,int x,int y,String toPath ) throws IOException{
	     InputStream is= null;
	     InputStream is2= null;
	     OutputStream os = null;
	     try{
	      is=new FileInputStream(negativeImagePath);
	            is2=new FileInputStream(additionImagePath);
	            BufferedImage image=ImageIO.read(is);
	            BufferedImage image2=ImageIO.read(is2);
	            Graphics g=image.getGraphics();
	            g.drawImage(image2,x,y,null);
	            os = new FileOutputStream(toPath);
	            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
	            enc.encode(image);
	     }catch(Exception e){
	      e.printStackTrace();
	     }finally{
	      if(os != null){
	       os.close();
	      }
	      if(is2 != null){
	       is2.close();
	      }
	      if(is != null){
	       is.close();
	      }
	     }
	    }
	    
	    /** 
	     * 将一组图片一次性附加合并到底图上
	     * @param negativeImagePath  源图像（底图）路径
	     * @param additionImageList 附加图像信息列表
	     * @param imageFormat 图像写入格式
	     * @param toPath 图像写入路径
	     * @throws IOException
	     */
	    public static void mergeImageList(String negativeImagePath,List additionImageList,String imageFormat, String toPath) throws IOException{
	     InputStream is= null;
	     InputStream is2= null;
	     OutputStream os = null;
	     try{
	      is=new FileInputStream(negativeImagePath);
	      BufferedImage image=ImageIO.read(is);
	      //Graphics g=image.getGraphics();
	      Graphics2D g = image.createGraphics();;
	      BufferedImage image2 = null;
	      if(additionImageList != null){
	       for(int i=0;i<additionImageList.size();i++){
	        //解析附加图片信息：x坐标、 y坐标、 additionImagePath附加图片路径
	        //图片信息存储在一个数组中
	        String[] additionImageInfo = (String[]) additionImageList.get(i);
	        int x = Integer.parseInt(additionImageInfo[0]);
	        int y = Integer.parseInt(additionImageInfo[1]);
	        String additionImagePath = additionImageInfo[2];
	        //读取文件输入流，并合并图片
	        is2 = new FileInputStream(additionImagePath);
	        //System.out.println(x+"  :  "+y+"  :  "+additionImagePath);
	        image2 = ImageIO.read(is2);
	                 g.drawImage(image2,x,y,null);
	       }
	      }
	            os = new FileOutputStream(toPath);
	            ImageIO.write(image,  imageFormat,  os);//写图片
	            //JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
	            //enc.encode(image);
	     }catch(Exception e){
	      e.printStackTrace();
	     }finally{
	      if(os != null){
	       os.close();
	      }
	      if(is2 != null){
	       is2.close();
	      }
	      if(is != null){
	       is.close();
	      }
	     }
	    }
	    
	    /**
	     * 将附加图片合并到底图的左上角
	     * @param negativeImagePath 底图路径
	     * @param additionImagePath 附加图片路径
	     * @param toPath 合成图片写入路径
	     * @throws IOException
	     */
	    public static void mergeBothImageTopleftcorner(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
	     InputStream is= null;
	     InputStream is2= null;
	     OutputStream os = null;
	     try{
	      is=new FileInputStream(negativeImagePath);
	            is2=new FileInputStream(additionImagePath);
	            BufferedImage image=ImageIO.read(is);
	            BufferedImage image2=ImageIO.read(is2);
	            Graphics g=image.getGraphics();
	            g.drawImage(image2,0,0,null);
	            os = new FileOutputStream(toPath);
	            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
	            enc.encode(image);
	     }catch(Exception e){
	      e.printStackTrace();
	     }finally{
	      if(os != null){
	       os.close();
	      }
	      if(is2 != null){
	       is2.close();
	      }
	      if(is != null){
	       is.close();
	      }
	     }
	    }
	    
	    /**
	     * 将附加图片合并到底图的右上角
	     * @param negativeImagePath 底图路径
	     * @param additionImagePath 附加图片路径
	     * @param toPath 合成图片写入路径
	     * @throws IOException
	     */
	    public static void mergeBothImageToprightcorner(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
	     InputStream is= null;
	     InputStream is2= null;
	     OutputStream os = null;
	     try{
	      is=new FileInputStream(negativeImagePath);
	            is2=new FileInputStream(additionImagePath);
	            BufferedImage image=ImageIO.read(is);
	            BufferedImage image2=ImageIO.read(is2);
	            Graphics g=image.getGraphics();
	            g.drawImage(image2,image.getWidth()-image2.getWidth(),0,null);
	            os = new FileOutputStream(toPath);
	            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
	            enc.encode(image);
	     }catch(Exception e){
	      e.printStackTrace();
	     }finally{
	      if(os != null){
	       os.close();
	      }
	      if(is2 != null){
	       is2.close();
	      }
	      if(is != null){
	       is.close();
	      }
	     }
	    }
	    
	    /**
	     * 将附加图片合并到底图的左下角
	     * @param negativeImagePath 底图路径
	     * @param additionImagePath 附加图片路径
	     * @param toPath 合成图片写入路径
	     * @throws IOException
	     */
	    public static void mergeBothImageLeftbottom(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
	     InputStream is= null;
	     InputStream is2= null;
	     OutputStream os = null;
	     try{
	      is=new FileInputStream(negativeImagePath);
	            is2=new FileInputStream(additionImagePath);
	            BufferedImage image=ImageIO.read(is);
	            BufferedImage image2=ImageIO.read(is2);
	            Graphics g=image.getGraphics();
	            g.drawImage(image2,0,image.getHeight()-image2.getHeight(),null);
	            os = new FileOutputStream(toPath);
	            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
	            enc.encode(image);
	     }catch(Exception e){
	      e.printStackTrace();
	     }finally{
	      if(os != null){
	       os.close();
	      }
	      if(is2 != null){
	       is2.close();
	      }
	      if(is != null){
	       is.close();
	      }
	     }
	    }
	    
	    /**
	     * 将附加图片合并到底图的左下角
	     * @param negativeImagePath 底图路径
	     * @param additionImagePath 附加图片路径
	     * @param toPath 合成图片写入路径
	     * @throws IOException
	     */
	    public static void mergeBothImageRightbottom(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
	     InputStream is= null;
	     InputStream is2= null;
	     OutputStream os = null;
	     try{
	      is=new FileInputStream(negativeImagePath);
	            is2=new FileInputStream(additionImagePath);
	            BufferedImage image=ImageIO.read(is);
	            BufferedImage image2=ImageIO.read(is2);
	            Graphics g=image.getGraphics();
	            g.drawImage(image2,image.getWidth()-image2.getWidth(),image.getHeight()-image2.getHeight(),null);
	            os = new FileOutputStream(toPath);
	            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
	            enc.encode(image);
	     }catch(Exception e){
	      e.printStackTrace();
	     }finally{
	      if(os != null){
	       os.close();
	      }
	      if(is2 != null){
	       is2.close();
	      }
	      if(is != null){
	       is.close();
	      }
	     }
	    }
	    
	    /**
	     * 将附加图片合并到底图的正中央
	     * @param negativeImagePath 底图路径
	     * @param additionImagePath 附加图片路径
	     * @param toPath 合成图片写入路径
	     * @throws IOException
	     */
	    public  static void mergeBothImageCenter(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
	     InputStream is= null;
	     InputStream is2= null;
	     OutputStream os = null;
	     try{
	      is=new FileInputStream(negativeImagePath);
	            is2=new FileInputStream(additionImagePath);
	            BufferedImage image=ImageIO.read(is);
	            BufferedImage image2=ImageIO.read(is2);
	            Graphics g=image.getGraphics();
	            g.drawImage(image2,image.getWidth()/2-image2.getWidth()/2,image.getHeight()/2-image2.getHeight()/2,null);
	            os = new FileOutputStream(toPath);
	            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
	            enc.encode(image);
	     }catch(Exception e){
	      e.printStackTrace();
	     }finally{
	      if(os != null){
	       os.close();
	      }
	      if(is2 != null){
	       is2.close();
	      }
	      if(is != null){
	       is.close();
	      }
	     }
	    }
	    
	    /**
	     * 将附加图片合并到底图的上边中央
	     * @param negativeImagePath 底图路径
	     * @param additionImagePath 附加图片路径
	     * @param toPath 合成图片写入路径
	     * @throws IOException
	     */
	    public static void mergeBothImageTopcenter(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
	     InputStream is= null;
	     InputStream is2= null;
	     OutputStream os = null;
	     try{
	      is=new FileInputStream(negativeImagePath);
	            is2=new FileInputStream(additionImagePath);
	            BufferedImage image=ImageIO.read(is);
	            BufferedImage image2=ImageIO.read(is2);
	            Graphics g=image.getGraphics();
	            g.drawImage(image2,image.getWidth()/2-image2.getWidth()/2,0,null);
	            os = new FileOutputStream(toPath);
	            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
	            enc.encode(image);
	     }catch(Exception e){
	      e.printStackTrace();
	     }finally{
	      if(os != null){
	       os.close();
	      }
	      if(is2 != null){
	       is2.close();
	      }
	      if(is != null){
	       is.close();
	      }
	     }
	    }
	    
	    /**
	     * 将附加图片合并到底图的下边中央
	     * @param negativeImagePath 底图路径
	     * @param additionImagePath 附加图片路径
	     * @param toPath 合成图片写入路径
	     * @throws IOException
	     */
	    public static void mergeBothImageBottomcenter(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
	     InputStream is= null;
	     InputStream is2= null;
	     OutputStream os = null;
	     try{
	      is=new FileInputStream(negativeImagePath);
	            is2=new FileInputStream(additionImagePath);
	            BufferedImage image=ImageIO.read(is);
	            BufferedImage image2=ImageIO.read(is2);
	            Graphics g=image.getGraphics();
	            g.drawImage(image2,image.getWidth()/2-image2.getWidth()/2,image.getHeight()-image2.getHeight(),null);
	            os = new FileOutputStream(toPath);
	            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
	            enc.encode(image);
	     }catch(Exception e){
	      e.printStackTrace();
	     }finally{
	      if(os != null){
	       os.close();
	      }
	      if(is2 != null){
	       is2.close();
	      }
	      if(is != null){
	       is.close();
	      }
	     }
	    }
	    
	    /**
	     * 将附加图片合并到底图的左边中央
	     * @param negativeImagePath 底图路径
	     * @param additionImagePath 附加图片路径
	     * @param toPath 合成图片写入路径
	     * @throws IOException
	     */
	    public static void mergeBothImageLeftcenter(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
	     InputStream is= null;
	     InputStream is2= null;
	     OutputStream os = null;
	     try{
	      is=new FileInputStream(negativeImagePath);
	            is2=new FileInputStream(additionImagePath);
	            BufferedImage image=ImageIO.read(is);
	            BufferedImage image2=ImageIO.read(is2);
	            Graphics g=image.getGraphics();
	            g.drawImage(image2,0,image.getHeight()/2-image2.getHeight()/2,null);
	            os = new FileOutputStream(toPath);
	            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
	            enc.encode(image);
	     }catch(Exception e){
	      e.printStackTrace();
	     }finally{
	      if(os != null){
	       os.close();
	      }
	      if(is2 != null){
	       is2.close();
	      }
	      if(is != null){
	       is.close();
	      }
	     }
	    }
	    
	    /**
	     * 将附加图片合并到底图的右边中央
	     * @param negativeImagePath 底图路径
	     * @param additionImagePath 附加图片路径
	     * @param toPath 合成图片写入路径
	     * @throws IOException
	     */
	    public static void mergeBothImageRightcenter(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
	     InputStream is= null;
	     InputStream is2= null;
	     OutputStream os = null;
	     try{
	      is=new FileInputStream(negativeImagePath);
	            is2=new FileInputStream(additionImagePath);
	            BufferedImage image=ImageIO.read(is);
	            BufferedImage image2=ImageIO.read(is2);
	            Graphics g=image.getGraphics();
	            g.drawImage(image2,image.getWidth()-image2.getWidth(),image.getHeight()/2-image2.getHeight()/2,null);
	            os = new FileOutputStream(toPath);
	            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
	            enc.encode(image);
	     }catch(Exception e){
	      e.printStackTrace();
	     }finally{
	      if(os != null){
	       os.close();
	      }
	      if(is2 != null){
	       is2.close();
	      }
	      if(is != null){
	       is.close();
	      }
	     }
	    }
	    /**
	     * 图片灰化操作
	     * @param srcImage 读取图片路径
	     * @param toPath 写入灰化后的图片路径
	     * @param imageFormat 图片写入格式
	     */ 
	    public void grayImage(String srcImage,String toPath,String imageFormat){
	     try{
	      BufferedImage src = ImageIO.read(new File(srcImage));
	         ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
	         ColorConvertOp op = new ColorConvertOp(cs, null);
	         src = op.filter(src, null);
	         ImageIO.write(src, imageFormat, new File(toPath));
	     }catch(Exception e){
	      e.printStackTrace();
	     }
	    }
	 public static void main(String[] args) {
		 try {
			//按指定坐标合并图片
			mergeBothImage(negativeImagePath, additionImagePath, x, y, toPath);
			//合并到左上角
			mergeBothImageTopleftcorner(additionImagePath, negativeImagePath, toPath);
			//合并到右上角
			mergeBothImageToprightcorner( additionImagePath,negativeImagePath, toPath);
			//合并到左下角
			mergeBothImageLeftbottom(additionImagePath,negativeImagePath,  toPath);
			//合并到右下角
			mergeBothImageRightbottom(additionImagePath,negativeImagePath,  toPath);
			//合并到正中央
			mergeBothImageCenter(additionImagePath,negativeImagePath,  toPath);
			//合并到上边中央
			mergeBothImageTopcenter(additionImagePath,negativeImagePath,  toPath);
			//合并到下边中央
			mergeBothImageBottomcenter(additionImagePath,negativeImagePath,  toPath);
			//合并到左边中央
			mergeBothImageLeftcenter(additionImagePath,negativeImagePath,  toPath);
			//合并到右边中央
			mergeBothImageRightcenter(additionImagePath,negativeImagePath,  toPath);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	 
}
