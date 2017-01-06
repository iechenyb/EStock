package com.cyb.graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class DrawGrapics {
	/**
	 * 绘制一个由 x 和 y 坐标数组定义的闭合多边形
	 * 
	 * @param srcImagePath
	 *            源图片路径
	 * @param xPoints
	 *            x坐标数组
	 * @param yPoints
	 *            y坐标数组
	 * @param nPoints
	 *            坐标点的个数
	 * @param polygonColor
	 *            线条颜色
	 * @param imageFormat
	 *            图像写入格式
	 * @param toPath
	 *            图像写入路径
	 * @throws IOException
	 */
	public static void drawPolygon(String srcImagePath, int[] xPoints,
			int[] yPoints, int nPoints, Color polygonColor, String imageFormat,
			String toPath) throws IOException {
		FileOutputStream fos = null;
		try {
			// 获取图片
			BufferedImage image = ImageIO.read(new File(srcImagePath));
			// 根据xy点坐标绘制闭合多边形
			Graphics2D g2d = image.createGraphics();
			g2d.setColor(polygonColor);
			g2d.drawPolygon(xPoints, yPoints, nPoints);
			fos = new FileOutputStream(toPath);
			ImageIO.write(image, imageFormat, fos);
			g2d.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	public static void testDrawPolygon() {
		String srcImagePath = "D:/chenyb/pic/blank.jpg";
		int[] xPoints = { 0, 0, 330, 20 };
		int[] yPoints = { 0, 300, 0, 20 };
		int nPoints = 4;
		String toPath = "D:/chenyb/pic/new/dubianxing.png";
		try {
			// 根据坐标数组绘制多边形
			drawPolygon(srcImagePath, yPoints, xPoints, nPoints, Color.MAGENTA,
					"png", toPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在源图片上设置水印文字
	 * 
	 * @param srcImagePath
	 *            源图片路径
	 * @param alpha
	 *            透明度（0<alpha<1）
	 * @param font
	 *            字体（例如：宋体）
	 * @param fontStyle
	 *            字体格式(例如：普通样式--Font.PLAIN、粗体--Font.BOLD )
	 * @param fontSize
	 *            字体大小
	 * @param color
	 *            字体颜色(例如：黑色--Color.BLACK)
	 * @param inputWords
	 *            输入显示在图片上的文字
	 * @param x
	 *            文字显示起始的x坐标
	 * @param y
	 *            文字显示起始的y坐标
	 * @param imageFormat
	 *            写入图片格式（png/jpg等）
	 * @param toPath
	 *            写入图片路径
	 * @throws IOException
	 */
	public static void alphaWords2Image(String srcImagePath, float alpha,
			String font, int fontStyle, int fontSize, Color color,
			String inputWords, int x, int y, String imageFormat, String toPath)
			throws IOException {
		FileOutputStream fos = null;
		try {
			BufferedImage image = ImageIO.read(new File(srcImagePath));
			// 创建java2D对象
			Graphics2D g2d = image.createGraphics();
			// 用源图像填充背景
			g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(),
					null, null);
			// 设置透明度
			AlphaComposite ac = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, alpha);
			g2d.setComposite(ac);
			// 设置文字字体名称、样式、大小
			g2d.setFont(new Font(font, fontStyle, fontSize));
			g2d.setColor(color);// 设置字体颜色
			g2d.drawString(inputWords, x, y); // 输入水印文字及其起始x、y坐标
			g2d.dispose();
			fos = new FileOutputStream(toPath);
			ImageIO.write(image, imageFormat, fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	/**
	 * 在源图像上设置图片水印 ---- 当alpha==1时文字不透明（和在图片上直接输入文字效果一样）
	 * 
	 * @param srcImagePath
	 *            源图片路径
	 * @param appendImagePath
	 *            水印图片路径
	 * @param alpha
	 *            透明度
	 * @param x
	 *            水印图片的起始x坐标
	 * @param y
	 *            水印图片的起始y坐标
	 * @param width
	 *            水印图片的宽度
	 * @param height
	 *            水印图片的高度
	 * @param imageFormat
	 *            图像写入图片格式
	 * @param toPath
	 *            图像写入路径
	 * @throws IOException
	 */
	public static void alphaImage2Image(String srcImagePath,
			String appendImagePath, float alpha, int x, int y, int width,
			int height, String imageFormat, String toPath) throws IOException {
		FileOutputStream fos = null;
		try {
			BufferedImage image = ImageIO.read(new File(srcImagePath));
			// 创建java2D对象
			Graphics2D g2d = image.createGraphics();
			// 用源图像填充背景
			g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(),
					null, null);
			// 设置透明度
			AlphaComposite ac = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, alpha);
			g2d.setComposite(ac);
			// 设置水印图片的起始x/y坐标、宽度、高度
			BufferedImage appendImage = ImageIO.read(new File(appendImagePath));
			g2d.drawImage(appendImage, x, y, width, height, null, null);
			g2d.dispose();
			fos = new FileOutputStream(toPath);
			ImageIO.write(image, imageFormat, fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	public static void testshuiyin() {
		String srcImagePath = "D:/chenyb/pic/t1.jpg";
		String appendImagePath = "D:/chenyb/pic/t2.jpg";
		float alpha = 0.2F;
		String font = "宋体";
		int fontStyle = Font.PLAIN;
		int fontSize = 32;
		Color color = Color.RED;
		String inputWords = "图片上设置水印文字 ---- 宋体 普通字体 32号字 红色 透明度0.5";
		int x = 20;
		int y = 40;
		String imageFormat = "jpg";
		String toPath = "D:/chenyb/pic/new/shuiyin.jpg";
		try {
			// 设置文字水印
			alphaWords2Image(srcImagePath, alpha, font, fontStyle, fontSize,
					color, inputWords, x, y, imageFormat, toPath);
			// 设置图片水印
			alphaImage2Image(srcImagePath, appendImagePath, alpha, x, y, 300,
					200, imageFormat, toPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 画折线 / 线段 ---- 2个点即画线段，多个点画折线
	 * 
	 * @param srcImagePath
	 *            源图片路径
	 * @param xPoints
	 *            x坐标数组
	 * @param yPoints
	 *            y坐标数组
	 * @param nPoints
	 *            点的数量
	 * @param lineColor
	 *            线条颜色
	 * @param toPath
	 *            图像写入路径
	 * @param imageFormat
	 *            图片写入格式
	 * @throws IOException
	 */
	public static void drawPolyline(String srcImagePath, int[] xPoints,
			int[] yPoints, int nPoints, Color lineColor, String toPath,
			String imageFormat) throws IOException {
		FileOutputStream fos = null;
		try {
			// 获取源图片
			BufferedImage image = ImageIO.read(new File(srcImagePath));
			// 根据xy点坐标绘制连接线
			Graphics2D g2d = image.createGraphics();
			// 设置线条颜色
			g2d.setColor(lineColor);
			g2d.drawPolyline(xPoints, yPoints, nPoints);
			// 图像写出路径
			fos = new FileOutputStream(toPath);
			ImageIO.write(image, imageFormat, fos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	public static void testDrawPolyline() {
		String srcImagePath = "D:/test/fileSource/003.jpg";
		int[] xPoints = { 100, 150, 200, 240, 300 };
		int[] yPoints = { 200, 60, 280, 160, 100 };
		int nPoints = 5;
		Color lineColor = Color.RED;
		String toPath = "D:/test/desk/polyline-003.jpg";
		String imageFormat = "jpg";
		try {
			// 画折线
			drawPolyline(srcImagePath, xPoints, yPoints, nPoints, lineColor,
					toPath, imageFormat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 画线段
	 * 
	 * @param srcImagePath
	 *            源图片路径
	 * @param x1
	 *            第一个点x坐标
	 * @param y1
	 *            第一个点y坐标
	 * @param x2
	 *            第二个点x坐标
	 * @param y2
	 *            第二个点y坐标
	 * @param lineColor
	 *            线条颜色
	 * @param toPath
	 *            图像写入路径
	 * @param imageFormat
	 *            图像写入格式
	 * @throws IOException
	 */
	public static void drawLine(String srcImagePath, int x1, int y1, int x2,
			int y2, Color lineColor, String toPath, String imageFormat)
			throws IOException {
		FileOutputStream fos = null;
		try {
			// 获取源图片
			BufferedImage image = ImageIO.read(new File(srcImagePath));
			// 根据xy点坐标绘制连接线
			Graphics2D g2d = image.createGraphics();
			g2d.setColor(lineColor);
			g2d.drawLine(x1, y1, x2, y2);
			fos = new FileOutputStream(toPath);
			ImageIO.write(image, imageFormat, fos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	public static void testDrawLine() {
		String srcImagePath = "D:/chenyb/pic/blank.jpg";
		int[] xPoints = { 100, 150, 200, 240, 300 };
		int[] yPoints = { 200, 60, 280, 160, 100 };
		int nPoints = 5;
		String toPath = "D:/chenyb/pic/new/polyLine.jpg";
		String imageFormat = "jpg";
		int x1 = 50;
		int y1 = 100;
		int x2 = 600;
		int y2 = 150;
		Color lineColor = Color.BLUE;
		try {
			// 画线段
			drawLine(srcImagePath, x1, y1, x2, y2, lineColor, toPath,
					imageFormat);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 画单点 ---- 实际上是画一个填充颜色的圆 ---- 以指定点坐标为中心画一个小半径的圆形，并填充其颜色来充当点
	 * 
	 * @param srcImagePath
	 *            源图片颜色
	 * @param x
	 *            点的x坐标
	 * @param y
	 *            点的y坐标
	 * @param width
	 *            填充的宽度
	 * @param height
	 *            填充的高度
	 * @param ovalColor
	 *            填充颜色
	 * @param imageFormat
	 *            写入图片格式
	 * @param toPath
	 *            写入路径
	 * @throws IOException
	 */
	public static void drawPoint(String srcImagePath, int x, int y, int width,
			int height, Color ovalColor, String imageFormat, String toPath)
			throws IOException {
		FileOutputStream fos = null;
		try {
			// 获取源图片
			BufferedImage image = ImageIO.read(new File(srcImagePath));
			// 根据xy点坐标绘制连接线
			Graphics2D g2d = image.createGraphics();
			g2d.setColor(ovalColor);
			// 填充一个椭圆形
			g2d.fillOval(x, y, width, height);
			fos = new FileOutputStream(toPath);
			ImageIO.write(image, imageFormat, fos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	public static void testDrawCircle() {
		String srcImagePath = "D:/chenyb/pic/blank.jpg";
		int x = 40;
		int y = 10;
		int width = 300;
		int height = 350;
		Color ovalColor = Color.RED;
		String imageFormat = "jpg";
		String toPath = "D:/chenyb/pic/new/circle.jpg";
		// 画一个圆点
		try {
			drawPoint(srcImagePath, x, y, width, height, ovalColor,
					imageFormat, toPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 画一组（多个）点---- 实际上是画一组（多个）填充颜色的圆 ---- 以指定点坐标为中心画一个小半径的圆形，并填充其颜色来充当点
	 * 
	 * @param srcImagePath
	 *            原图片路径
	 * @param pointList
	 *            点列表
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param ovalColor
	 *            填充颜色
	 * @param imageFormat
	 *            写入图片颜色
	 * @param toPath
	 *            写入路径
	 * @throws IOException
	 */
	public static void drawPoints(String srcImagePath, List pointList,
			int width, int height, Color ovalColor, String imageFormat,
			String toPath) throws IOException {
		FileOutputStream fos = null;
		try {
			// 获取源图片
			BufferedImage image = ImageIO.read(new File(srcImagePath));
			// 根据xy点坐标绘制连接线
			Graphics2D g2d = image.createGraphics();
			g2d.setColor(ovalColor);
			// 填充一个椭圆形
			if (pointList != null) {
				for (int i = 0; i < pointList.size(); i++) {
					Point point = (Point) pointList.get(i);
					int x = (int) point.getX();
					int y = (int) point.getY();
					g2d.fillOval(x, y, width, height);
				}
			}
			fos = new FileOutputStream(toPath);
			ImageIO.write(image, imageFormat, fos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	public static void testMutiPoints() {
		List pointList = new ArrayList();
		Point p1 = new Point(60, 80);
		pointList.add(p1);
		Point p2 = new Point(160, 80);
		pointList.add(p2);
		Point p3 = new Point(60, 180);
		pointList.add(p3);
		Point p4 = new Point(260, 180);
		pointList.add(p4);
		Point p5 = new Point(460, 380);
		pointList.add(p5);
		String srcImagePath = "D:/chenyb/pic/blank.jpg";
		int width = 10;
		int height = 10;
		Color ovalColor = Color.RED;
		String imageFormat = "jpg";
		String toPath = "D:/chenyb/pic/new/multiPoints.jpg";
		try {
			drawPoints(srcImagePath, pointList, width, height, ovalColor,
					imageFormat, toPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 画出一组（多个）点

	}

	/**
	 * 绘制折线，并突出显示转折点
	 * 
	 * @param srcImagePath
	 *            源图片路径
	 * @param xPoints
	 *            x坐标数组
	 * @param yPoints
	 *            y坐标数组
	 * @param nPoints
	 *            点的数量
	 * @param lineColor
	 *            连线颜色
	 * @param width
	 *            点的宽度
	 * @param height
	 *            点的高度
	 * @param ovalColor
	 *            点的填充颜色
	 * @param toPath
	 *            图像写入路径
	 * @param imageFormat
	 *            图像写入格式
	 * @throws IOException
	 */
	public static void drawPolylineShowPoints(String srcImagePath,
			int[] xPoints, int[] yPoints, int nPoints, Color lineColor,
			int width, int height, Color ovalColor, String toPath,
			String imageFormat) throws IOException {
		FileOutputStream fos = null;
		try {
			// 获取源图片
			BufferedImage image = ImageIO.read(new File(srcImagePath));
			// 根据xy点坐标绘制连接线
			Graphics2D g2d = image.createGraphics();
			// 设置线条颜色
			g2d.setColor(lineColor);
			// 画线条
			g2d.drawPolyline(xPoints, yPoints, nPoints);
			// 设置圆点颜色
			g2d.setColor(ovalColor);
			// 画圆点
			if (xPoints != null) {
				for (int i = 0; i < xPoints.length; i++) {
					int x = xPoints[i];
					int y = yPoints[i];
					g2d.fillOval(x, y, width, height);
				}
			}
			// 图像写出路径
			fos = new FileOutputStream(toPath);
			ImageIO.write(image, imageFormat, fos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	public static void testdrawPolylineShowPoints() {
		int width = 10;
		int height = 10;
		int[] xPoints = { 50, 100, 180, 400, 600 };
		int[] yPoints = { 200, 100, 160, 300, 640 };
		int nPoints = 5;
		Color lineColor = Color.PINK;
		String srcImagePath = "D:/chenyb/pic/blank.jpg";
		String toPath = "D:/chenyb/pic/new/mutipoints-blod.jpg";
		Color ovalColor = Color.RED;
		String imageFormat = "jpg";
		try {
			drawPolylineShowPoints(srcImagePath, xPoints, yPoints, nPoints,
					lineColor, width, height, ovalColor, toPath, imageFormat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 画折线并突出显示点
	}

	/**
	 * 绘制并填充多边形
	 * 
	 * @param srcImagePath
	 *            源图像路径
	 * @param xPoints
	 *            x坐标数组
	 * @param yPoints
	 *            y坐标数组
	 * @param nPoints
	 *            坐标点个数
	 * @param polygonColor
	 *            多边形填充颜色
	 * @param alpha
	 *            多边形部分透明度
	 * @param imageFormat
	 *            写入图形格式
	 * @param toPath
	 *            写入图形路径
	 * @throws IOException
	 */
	public static void drawAndAlphaPolygon(String srcImagePath, int[] xPoints,
			int[] yPoints, int nPoints, Color polygonColor, float alpha,
			String imageFormat, String toPath) throws IOException {
		FileOutputStream fos = null;
		try {
			// 获取图片
			BufferedImage image = ImageIO.read(new File(srcImagePath));
			// 根据xy点坐标绘制闭合多边形
			Graphics2D g2d = image.createGraphics();
			g2d.setColor(polygonColor);
			// 设置透明度
			AlphaComposite ac = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, alpha);
			g2d.setComposite(ac);
			g2d.fillPolygon(xPoints, yPoints, nPoints);
			fos = new FileOutputStream(toPath);
			ImageIO.write(image, imageFormat, fos);
			g2d.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	public static void testdrawAndAlphaPolygon() {
		String srcImagePath = "D:/chenyb/pic/blank.jpg";
		int[] xPoints = { 50, 90, 180, 320, 640 };
		int[] yPoints = { 200, 300, 120, 240, 360 };
		int nPoints = 5;
		Color polygonColor = Color.green;
		float alpha = (float) 1.0;
		String imageFormat = "jpg";
		String toPath = "D:/chenyb/pic/new/drawAndAlphaPolygon.jpg";
		try {
			drawAndAlphaPolygon(srcImagePath, xPoints, yPoints, nPoints,
					polygonColor, alpha, imageFormat, toPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 合并图片(按指定初始x、y坐标将附加图片贴到底图之上)
	 * 
	 * @param negativeImagePath
	 *            背景图片路径
	 * @param additionImagePath
	 *            附加图片路径
	 * @param x
	 *            附加图片的起始点x坐标
	 * @param y
	 *            附加图片的起始点y坐标
	 * @param toPath
	 *            图片写入路径
	 * @throws IOException
	 */
	public static void mergeBothImage(String negativeImagePath,
			String additionImagePath, int x, int y, String toPath)
			throws IOException {
		InputStream is = null;
		InputStream is2 = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(negativeImagePath);
			is2 = new FileInputStream(additionImagePath);
			BufferedImage image = ImageIO.read(is);
			BufferedImage image2 = ImageIO.read(is2);
			Graphics g = image.getGraphics();
			g.drawImage(image2, x, y, null);
			os = new FileOutputStream(toPath);
			JPEGImageEncoder enc = JPEGCodec.createJPEGEncoder(os);
			enc.encode(image);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
			if (is2 != null) {
				is2.close();
			}
			if (is != null) {
				is.close();
			}
		}
	}

	/**
	 * 将一组图片一次性附加合并到底图上
	 * 
	 * @param negativeImagePath
	 *            源图像（底图）路径
	 * @param additionImageList
	 *            附加图像信息列表
	 * @param imageFormat
	 *            图像写入格式
	 * @param toPath
	 *            图像写入路径
	 * @throws IOException
	 */
	public static void mergeImageList(String negativeImagePath,
			List additionImageList, String imageFormat, String toPath)
			throws IOException {
		InputStream is = null;
		InputStream is2 = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(negativeImagePath);
			BufferedImage image = ImageIO.read(is);
			// Graphics g=image.getGraphics();
			Graphics2D g = image.createGraphics();
			;
			BufferedImage image2 = null;
			if (additionImageList != null) {
				for (int i = 0; i < additionImageList.size(); i++) {
					// 解析附加图片信息：x坐标、 y坐标、 additionImagePath附加图片路径
					// 图片信息存储在一个数组中
					String[] additionImageInfo = (String[]) additionImageList
							.get(i);
					int x = Integer.parseInt(additionImageInfo[0]);
					int y = Integer.parseInt(additionImageInfo[1]);
					String additionImagePath = additionImageInfo[2];
					// 读取文件输入流，并合并图片
					is2 = new FileInputStream(additionImagePath);
					// System.out.println(x+"  :  "+y+"  :  "+additionImagePath);
					image2 = ImageIO.read(is2);
					g.drawImage(image2, x, y, null);
				}
			}
			os = new FileOutputStream(toPath);
			ImageIO.write(image, imageFormat, os);// 写图片
			// JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
			// enc.encode(image);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
			if (is2 != null) {
				is2.close();
			}
			if (is != null) {
				is.close();
			}
		}
	}

	public static void testmergeBothImage() {
		String negativeImagePath = "D:/chenyb/pic/t1.jpg";
		String additionImagePath = "D:/chenyb/pic/t2.jpg";
		String toPath = "D:/chenyb/pic/new/mergeBothImage.jpg";
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			Random rand = new Random();
			int x = rand.nextInt(1024);
			int y = rand.nextInt(768);
			try {
				mergeBothImage(additionImagePath, negativeImagePath, x, y,
						toPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 每次附加合并一张图片(循环若干次)
			System.out.println(i);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	public static void testmergeImageList() {
		// 叠加组合图像
		String negativeImagePath = "D:/chenyb/pic/t2.jpg";
		String toPath = "D:/chenyb/pic/new/mergeImageList.jpg";
		String additionImagePath = "D:/chenyb/pic/t1.jpg";
		List additionImageList = new ArrayList();
		int count = 0;
		for (int i = 0; i < 3; i++) {// 为什么总是连续生成一样的随机数？？？
			Random rand = new Random();
			int x = rand.nextInt(1020);
			String xStr = x + "";
			int y = rand.nextInt(760);
			String yStr = y + "";
			String[] str = { xStr, yStr, additionImagePath };
			additionImageList.add(str);
			count++;
		}
		System.out.println(count);
		long start = System.currentTimeMillis();
		try {
			mergeImageList(negativeImagePath, additionImageList, "jpg", toPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	/**
	 * 横向拼接一组（多张）图像
	 * 
	 * @param pics
	 *            将要拼接的图像
	 * @param type
	 *            图像写入格式
	 * @param dst_pic
	 *            图像写入路径
	 * @return
	 */
	public static boolean joinImageListHorizontal(String[] pics, String type,
			String dst_pic) {
		try {
			int len = pics.length;
			if (len < 1) {
				System.out.println("pics len < 1");
				return false;
			}
			File[] src = new File[len];
			BufferedImage[] images = new BufferedImage[len];
			int[][] imageArrays = new int[len][];
			for (int i = 0; i < len; i++) {
				src[i] = new File(pics[i]);
				images[i] = ImageIO.read(src[i]);
				int width = images[i].getWidth();
				int height = images[i].getHeight();
				imageArrays[i] = new int[width * height];// 从图片中读取RGB
				imageArrays[i] = images[i].getRGB(0, 0, width, height,
						imageArrays[i], 0, width);
			}

			int dst_width = 0;
			int dst_height = images[0].getHeight();
			for (int i = 0; i < images.length; i++) {
				dst_height = dst_height > images[i].getHeight() ? dst_height
						: images[i].getHeight();
				dst_width += images[i].getWidth();
			}
			// System.out.println(dst_width);
			// System.out.println(dst_height);
			if (dst_height < 1) {
				System.out.println("dst_height < 1");
				return false;
			}
			/*
			 * 生成新图片
			 */
			BufferedImage ImageNew = new BufferedImage(dst_width, dst_height,
					BufferedImage.TYPE_INT_RGB);
			int width_i = 0;
			for (int i = 0; i < images.length; i++) {
				ImageNew.setRGB(width_i, 0, images[i].getWidth(), dst_height,
						imageArrays[i], 0, images[i].getWidth());
				width_i += images[i].getWidth();
			}
			File outFile = new File(dst_pic);
			ImageIO.write(ImageNew, type, outFile);// 写图片
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void testjoinImageListHorizontal() {
		List iamgePathList = new ArrayList(); // D:/test/16a/
		iamgePathList.add("D:/chenyb/pic/t1.jpg");
		iamgePathList.add("D:/chenyb/pic/t1.jpg");
		iamgePathList.add("D:/chenyb/pic/t1.jpg");
		iamgePathList.add("D:/chenyb/pic/t1.jpg");
		String imageFormat = "jpg";
		String toPath = "D:/chenyb/pic/new/joinImageListHorizontal.jpg";
		String[] str = (String[]) iamgePathList
				.toArray(new String[iamgePathList.size()]);
		joinImageListHorizontal(str, imageFormat, toPath);
	}

	public static void testjoinImageListVertical() {
		String imageFormat = "jpg";
		String[] pics1 = { "D:/chenyb/pic/t1.jpg", "D:/chenyb/pic/t1.jpg",
				"D:/chenyb/pic/t1.jpg", "D:/chenyb/pic/t1.jpg" };
		String[] pics2 = { "D:/chenyb/pic/t1.jpg", "D:/chenyb/pic/t1.jpg",
				"D:/chenyb/pic/t1.jpg", "D:/chenyb/pic/t1.jpg" };
		String[] pics3 = { "D:/chenyb/pic/t1.jpg", "D:/chenyb/pic/t1.jpg",
				"D:/chenyb/pic/t1.jpg", "D:/chenyb/pic/t1.jpg" };
		String[] pics4 = { "D:/chenyb/pic/t1.jpg", "D:/chenyb/pic/t1.jpg",
				"D:/chenyb/pic/t1.jpg", "D:/chenyb/pic/t1.jpg" };
		String[] pics5 = { "D:/chenyb/pic/t1.jpg", "D:/chenyb/pic/t1.jpg",
				"D:/chenyb/pic/t1.jpg", "D:/chenyb/pic/t1.jpg" };
		String[] pics6 = { "D:/chenyb/pic/t1.jpg", "D:/chenyb/pic/t1.jpg",
				"D:/chenyb/pic/t1.jpg", "D:/chenyb/pic/t1.jpg" };

		String toPath1 = "D:/chenyb/pic/new/16a_v1.jpg";
		String toPath2 = "D:/chenyb/pic/new/16a_v2.jpg";
		String toPath3 = "D:/chenyb/pic/new/16a_v3.jpg";
		String toPath4 = "D:/chenyb/pic/new/16a_v4.jpg";
		String toPath5 = "D:/chenyb/pic/new/16a_v5.jpg";
		String toPath6 = "D:/chenyb/pic/new/16a_v6.jpg";

		String[] pics7 = { toPath1, toPath2, toPath3, toPath4, toPath5, toPath6 };
		String toPath7 = "D:/chenyb/pic/new/16a_h1.jpg";

		long start = System.currentTimeMillis();
		joinImageListVertical(pics1, imageFormat, toPath1);
		joinImageListVertical(pics2, imageFormat, toPath2);
		joinImageListVertical(pics3, imageFormat, toPath3);
		joinImageListVertical(pics4, imageFormat, toPath4);
		joinImageListVertical(pics5, imageFormat, toPath5);
		joinImageListVertical(pics6, imageFormat, toPath6);
		joinImageListHorizontal(pics7, imageFormat, toPath7);
		long end = System.currentTimeMillis();
		System.out.println(end - start);

	}

	/**
	 * 纵向拼接一组（多张）图像
	 * 
	 * @param pics
	 *            将要拼接的图像数组
	 * @param type
	 *            写入图像类型
	 * @param dst_pic
	 *            写入图像路径
	 * @return
	 */
	public static boolean joinImageListVertical(String[] pics, String type,
			String dst_pic) {
		try {
			int len = pics.length;
			if (len < 1) {
				System.out.println("pics len < 1");
				return false;
			}
			File[] src = new File[len];
			BufferedImage[] images = new BufferedImage[len];
			int[][] imageArrays = new int[len][];
			for (int i = 0; i < len; i++) {
				// System.out.println(i);
				src[i] = new File(pics[i]);
				images[i] = ImageIO.read(src[i]);
				int width = images[i].getWidth();
				int height = images[i].getHeight();
				imageArrays[i] = new int[width * height];// 从图片中读取RGB
				imageArrays[i] = images[i].getRGB(0, 0, width, height,
						imageArrays[i], 0, width);
			}

			int dst_height = 0;
			int dst_width = images[0].getWidth();
			for (int i = 0; i < images.length; i++) {
				dst_width = dst_width > images[i].getWidth() ? dst_width
						: images[i].getWidth();
				dst_height += images[i].getHeight();
			}
			// System.out.println(dst_width);
			// System.out.println(dst_height);
			if (dst_height < 1) {
				System.out.println("dst_height < 1");
				return false;
			}
			/*
			 * 生成新图片
			 */
			BufferedImage ImageNew = new BufferedImage(dst_width, dst_height,
					BufferedImage.TYPE_INT_RGB);
			int height_i = 0;
			for (int i = 0; i < images.length; i++) {
				ImageNew.setRGB(0, height_i, dst_width, images[i].getHeight(),
						imageArrays[i], 0, dst_width);
				height_i += images[i].getHeight();
			}
			File outFile = new File(dst_pic);
			ImageIO.write(ImageNew, type, outFile);// 写图片
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// 根据坐标数组绘制多边形
		testDrawPolygon();
		// 图片上设置水印文字
		testshuiyin();
		// 画折线
		testDrawPolyline();
		// 画线段
		testDrawLine();
		testDrawCircle();
		// 画出一组（多个）点
		testMutiPoints();
		// 画折线并突出显示点
		testdrawPolylineShowPoints();
		// 绘制并填充多边形
		testdrawAndAlphaPolygon();
		// 每次附加合并一张图片(循环若干次)
		testmergeBothImage();
		// 叠加组合图像
		testmergeImageList();
		testjoinImageListHorizontal();
		testjoinImageListVertical();
	}
}
