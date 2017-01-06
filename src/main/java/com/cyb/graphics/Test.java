package com.cyb.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class Test {
 public static void main(String[] args) {
	 //inspect the formate jdk supported
	System.out.println(Arrays.asList(ImageIO.getReaderFormatNames()));
	System.out.println(Arrays.asList(ImageIO.getWriterFormatNames()));
	File src = new File("d:/chenyb/pic/t1.jpg");
	File dest = new File("d:/chenyb/pic/new/writed.jpg");
	try {
		InputStream streaminput = new FileInputStream(src);
		BufferedImage inputPic = ImageIO.read(src);
		ImageIO.write(inputPic, "jpg", dest);
		Iterator readers_jpg = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader_jpg = (ImageReader) readers_jpg.next();
		System.out.println(reader_jpg);
		Iterator readers_gif = ImageIO.getImageReadersByFormatName("gif");
		ImageReader reader_gif = (ImageReader) readers_gif.next();
		System.out.println(reader_gif);
		ImageInputStream fileStream = ImageIO.createImageInputStream(src);
		ImageInputStream streamStream = ImageIO.createImageInputStream(streaminput);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
 }
}
