package com.cyb.utils;

import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {
	 public static String srcPath = "D:/chenyb/pic/t.jpg";
	 public static String toPath = "D:/chenyb/pic/new/t1.jpg";
	 public static int x = 600;
	 public static int y = 600;
	 public static int width = 300;
	 public static int height = 200 ;
	 public static String readImageFormat = "jpg";
	 public static String writeImageFormat = "jpg";
	/**
	 * 裁剪图片
	 * @param srcPath 源文件
	 * @param toPath 裁剪后新文件
	 * @param x 左上角点x轴
	 * @param y 左上角点y轴
	 * @param width 宽
	 * @param height 高
	 * @param readImageFormat
	 * @param writeImageFormat
	 * @throws IOException
	 */
	public static void cropImage(String srcPath,String toPath,
		      int x,int y,int width,int height,
		      String readImageFormat,String writeImageFormat) throws IOException{   
		        FileInputStream fis = null ;
		        ImageInputStream iis =null ;
		        try{   
		            //读取图片文件
		         fis = new FileInputStream(srcPath); 
		            Iterator it = ImageIO.getImageReadersByFormatName(readImageFormat); 
		            ImageReader reader = (ImageReader) it.next(); 
		            //获取图片流 
		            iis = ImageIO.createImageInputStream(fis);  
		            reader.setInput(iis,true) ;
		            ImageReadParam param = reader.getDefaultReadParam(); 
		            //定义一个矩形
		            Rectangle rect = new Rectangle(x, y, width, height); 
		            //提供一个 BufferedImage，将其用作解码像素数据的目标。 
		            param.setSourceRegion(rect);
		            BufferedImage bi = reader.read(0,param);                
		            //保存新图片 
		            ImageIO.write(bi, writeImageFormat, new File(toPath));     
		        }finally{
		            if(fis!=null)
		             fis.close();       
		            if(iis!=null)
		               iis.close(); 
		        } 
		    }
	//resize image
	public static void resizeImage(String srcImagePath,String toImagePath,int width,int height) throws IOException{
	     FileOutputStream out = null;
	     try{
	      //读入文件  
	            File file = new File(srcImagePath);  
	            // 构造Image对象  
	            BufferedImage src = javax.imageio.ImageIO.read(file);  
	            // 放大边长
	            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	            //绘制放大后的图片
	            tag.getGraphics().drawImage(src, 0, 0, width, height, null);  
	            out = new FileOutputStream(toImagePath);  
	            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
	            encoder.encode(tag);  
	     }catch(Exception e){
	      e.printStackTrace();
	     }finally{
	      if(out != null){
	                out.close();  
	      }
	     }
	    }
	/**
     * 按倍率缩小图片
     * @param srcImagePath 读取图片路径
     * @param toImagePath 写入图片路径
     * @param widthRatio 宽度缩小比例
     * @param heightRatio  高度缩小比例
     * @throws IOException
     */
    public static void reduceImageByRatio(String srcImagePath,String toImagePath,int widthRatio,int heightRatio) throws IOException{
     FileOutputStream out = null;
     try{
      //读入文件  
            File file = new File(srcImagePath);  
            // 构造Image对象  
            BufferedImage src = javax.imageio.ImageIO.read(file);  
            int width = src.getWidth();  
            int height = src.getHeight();  
            // 缩小边长 
            BufferedImage tag = new BufferedImage(width / widthRatio, height / heightRatio, BufferedImage.TYPE_INT_RGB);  
            // 绘制 缩小  后的图片 
            tag.getGraphics().drawImage(src, 0, 0, width / widthRatio, height / heightRatio, null);  
            out = new FileOutputStream(toImagePath);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(tag);  
     }catch(Exception e){
      e.printStackTrace();
     }finally{
      if(out != null){
                out.close();  
      }
     }
    }
    /**
     * 按倍率放大图片
     * @param srcImagePath 读取图形路径
     * @param toImagePath 写入入行路径
     * @param widthRatio 宽度放大比例
     * @param heightRatio 高度放大比例
     * @throws IOException
     */
    public static void enlargementImageByRatio(String srcImagePath,String toImagePath,int widthRatio,int heightRatio) throws IOException{
     FileOutputStream out = null;
     try{
      //读入文件  
            File file = new File(srcImagePath);  
            // 构造Image对象  
            BufferedImage src = javax.imageio.ImageIO.read(file);  
            int width = src.getWidth();  
            int height = src.getHeight();  
            // 放大边长
            BufferedImage tag = new BufferedImage(width * widthRatio, height * heightRatio, BufferedImage.TYPE_INT_RGB);  
            //绘制放大后的图片
            tag.getGraphics().drawImage(src, 0, 0, width * widthRatio, height * heightRatio, null);  
            out = new FileOutputStream(toImagePath);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(tag);  
     }catch(Exception e){
      e.printStackTrace();
     }finally{
      if(out != null){
                out.close();  
      }
     }
    }
    /**
     * 长高等比例缩小图片
     * @param srcImagePath 读取图片路径
     * @param toImagePath 写入图片路径
     * @param ratio 缩小比例
     * @throws IOException
     */
    public static  void reduceImageEqualProportion(String srcImagePath,String toImagePath,int ratio) throws IOException{
     FileOutputStream out = null;
     try{
      //读入文件  
            File file = new File(srcImagePath);  
            // 构造Image对象  
            BufferedImage src = javax.imageio.ImageIO.read(file);  
            int width = src.getWidth();  
            int height = src.getHeight();  
            // 缩小边长 
            BufferedImage tag = new BufferedImage(width / ratio, height / ratio, BufferedImage.TYPE_INT_RGB);  
            // 绘制 缩小  后的图片 
            tag.getGraphics().drawImage(src, 0, 0, width / ratio, height / ratio, null);  
            out = new FileOutputStream(toImagePath);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(tag);  
     }catch(Exception e){
      e.printStackTrace();
     }finally{
      if(out != null){
                out.close();  
      }
     }
    }
    /**
     * 图片灰化操作
     * @param srcImage 读取图片路径
     * @param toPath 写入灰化后的图片路径
     * @param imageFormat 图片写入格式
     */ 
    public static void grayImage(String srcImage,String toPath,String imageFormat){
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
    /**
     * 横向拼接图片（两张）
     * @param firstSrcImagePath 第一张图片的路径
     * @param secondSrcImagePath 第二张图片的路径
     * @param imageFormat 拼接生成图片的格式
     * @param toPath 拼接生成图片的路径
     */
    public void joinImagesHorizontal(String firstSrcImagePath, String secondSrcImagePath,String imageFormat, String toPath){  
     try {  
      //读取第一张图片    
      File  fileOne  =  new  File(firstSrcImagePath);    
            BufferedImage  imageOne = ImageIO.read(fileOne);    
            int  width  =  imageOne.getWidth();//图片宽度    
            int  height  =  imageOne.getHeight();//图片高度    
            //从图片中读取RGB    
            int[]  imageArrayOne  =  new  int[width*height];    
            imageArrayOne  =  imageOne.getRGB(0,0,width,height,imageArrayOne,0,width);    
           
            //对第二张图片做相同的处理    
            File  fileTwo  =  new  File(secondSrcImagePath);    
            BufferedImage  imageTwo  =  ImageIO.read(fileTwo); 
            int width2 = imageTwo.getWidth();
            int height2 = imageTwo.getHeight();
            int[]   ImageArrayTwo  =  new  int[width2*height2];    
            ImageArrayTwo  =  imageTwo.getRGB(0,0,width,height,ImageArrayTwo,0,width);    
            //ImageArrayTwo  =  imageTwo.getRGB(0,0,width2,height2,ImageArrayTwo,0,width2); 
           
            //生成新图片
            //int height3 = (height>height2 || height==height2)?height:height2;
            BufferedImage  imageNew  =  new  BufferedImage(width*2,height,BufferedImage.TYPE_INT_RGB);    
            //BufferedImage  imageNew  =  new  BufferedImage(width+width2,height3,BufferedImage.TYPE_INT_RGB);    
            imageNew.setRGB(0,0,width,height,imageArrayOne,0,width);//设置左半部分的RGB  
            imageNew.setRGB(width,0,width,height,ImageArrayTwo,0,width);//设置右半部分的RGB 
            //imageNew.setRGB(width,0,width2,height2,ImageArrayTwo,0,width2);//设置右半部分的RGB    
           
            File  outFile  =  new  File(toPath);    
            ImageIO.write(imageNew,  imageFormat,  outFile);//写图片
        } catch (Exception e) {  
         e.printStackTrace();  
        }  
    }
    /**
     * 纵向拼接图片（两张）
     * @param firstSrcImagePath 读取的第一张图片
     * @param secondSrcImagePath 读取的第二张图片
     * @param imageFormat 图片写入格式
     * @param toPath 图片写入路径
     */
    public static  void joinImagesVertical(String firstSrcImagePath, String secondSrcImagePath,String imageFormat, String toPath){  
        try {  
         //读取第一张图片    
            File  fileOne  =  new  File(firstSrcImagePath);    
            BufferedImage  imageOne = ImageIO.read(fileOne);    
            int  width  =  imageOne.getWidth();//图片宽度    
            int  height  =  imageOne.getHeight();//图片高度    
            //从图片中读取RGB    
            int[]  imageArrayOne  =  new  int[width*height];    
            imageArrayOne  =  imageOne.getRGB(0,0,width,height,imageArrayOne,0,width);    
       
            //对第二张图片做相同的处理    
            File  fileTwo  =  new  File(secondSrcImagePath);    
            BufferedImage  imageTwo  =  ImageIO.read(fileTwo); 
            int width2 = imageTwo.getWidth();
            int height2 = imageTwo.getHeight();
            int[]   ImageArrayTwo  =  new  int[width2*height2];    
            ImageArrayTwo  =  imageTwo.getRGB(0,0,width,height,ImageArrayTwo,0,width);    
            //ImageArrayTwo  =  imageTwo.getRGB(0,0,width2,height2,ImageArrayTwo,0,width2); 
       
            //生成新图片
            //int width3 = (width>width2 || width==width2)?width:width2;
            BufferedImage  imageNew  =  new  BufferedImage(width,height*2,BufferedImage.TYPE_INT_RGB);    
            //BufferedImage  imageNew  =  new  BufferedImage(width3,height+height2,BufferedImage.TYPE_INT_RGB);    
            imageNew.setRGB(0,0,width,height,imageArrayOne,0,width);//设置上半部分的RGB    
            imageNew.setRGB(0,height,width,height,ImageArrayTwo,0,width);//设置下半部分的RGB
            //imageNew.setRGB(0,height,width2,height2,ImageArrayTwo,0,width2);//设置下半部分的RGB    
       
            File  outFile  =  new  File(toPath);    
            ImageIO.write(imageNew,  imageFormat,  outFile);//写图片
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
	public static void main(String[] args) {
		try {
			//剪切图片
			cropImage(srcPath, toPath, x, y, width, height,readImageFormat,writeImageFormat);
			//按指定的长宽重置图形大小
			resizeImage(srcPath, toPath, 400, 400);
			//按指定长和宽的比例缩小图形
			reduceImageByRatio(srcPath, toPath, 3, 3);
			//按指定长和宽的比例放大图形
			enlargementImageByRatio(srcPath, toPath, 2, 2);
			//长高等比例缩小
			reduceImageEqualProportion(srcPath, toPath, 4);
			//图片灰化
//			String srcImage = "D:/chenyb/pic/t1.jpg";
//		     String toPath = "D:/chenyb/pic/new/t1_gray.jpg";
//		     String imageFormat = "jpg";
//			grayImage(srcImage, toPath, imageFormat);
			
			/*//横向拼接图片
			String firstSrcImagePath = "D:/chenyb/pic/t1.jpg";
		    String secondSrcImagePath = "D:/chenyb/pic/t2.jpg";
		    String imageFormat = "jpg";
		    String toPath = "D:/chenyb/pic/new/t12.jpg";
		    joinImagesHorizontal(firstSrcImagePath, secondSrcImagePath, imageFormat, toPath);
		   */
			 String firstSrcImagePath = "D:/test/desk/001-002-join.jpg";
		     String secondSrcImagePath = "D:/test/desk/003-004-join.jpg";
		     String imageFormat = "jpg";
		     String toPath = "D:/test/desk/all-join.jpg";
		     joinImagesVertical(firstSrcImagePath, secondSrcImagePath, imageFormat, toPath);//纵向拼接图片
		     
		} catch (IOException e) {
			e.printStackTrace();
		}
	     
	}
}
