package com.cyb.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.cyb.qutoes.constant.SpringUtil;

public class MailUtils {
	public static String from = "383065059@qq.com";
	public static String to = "zzuchenyb@sina.com";
	public static void sendTextEmail(SimpleMailMessage mail){
		JavaMailSender mailSender = (JavaMailSender) SpringUtil.getBean("qqMailSender");  
//		String text = "您的密码已经重置，新密码为111111,请及时修改密码！";  
		mailSender.send(mail);
		System.out.println("邮件发送成功！");
	}
	public static void sendTextEmail(){
	    try {
			JavaMailSender mailSender = (JavaMailSender) SpringUtil.getBean("qqMailSender");  
			SimpleMailMessage mail = new SimpleMailMessage();  
			mail.setFrom(from);  
			mail.setTo(to);  
			mail.setSubject("密码找回");   
			String text = "您的密码已经重置，新密码为111111,请及时修改密码！";  
			mail.setText(text);  
			mailSender.send(mail);
			System.out.println("邮件发送成功！");
		} catch (MailException e) {
			e.printStackTrace();
		}
	}
	public static void sendHtmlEmail(){
	    try {
			JavaMailSender senderImpl = (JavaMailSender) SpringUtil.getBean("qqMailSender");  
			//建立邮件消息,发送简单邮件和html邮件的区别      
		     MimeMessage mailMessage = senderImpl.createMimeMessage();      
		     MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");      
		    //设置收件人，寄件人      
		     messageHelper.setFrom(from);      
		     messageHelper.setTo(to);      
		     messageHelper.setSubject("测试HTML邮件！");      
		    //true 表示启动HTML格式的邮件      
		     String html= "<html><head></head><body>"
		     		+ "<font color='red'>hello!!zhangjian</font>"
		    		+"<img src='http://localhost:8085/EStock/img/tigger.gif' ></img>"
		     		+ "123123</body></html>";
		     System.out.println(html);
		     messageHelper.setText(html,true);  
		     messageHelper.addInline("aaaa", new File("D:/upload/Chrysanthemum.jpg"));  
		     messageHelper.addInline("bbbb", new File("D:/upload/Desert.jpg"));   
		     File file=new File("D:/file/code.rar");    
		     // 这里的方法调用和插入图片是不同的，使用MimeUtility.encodeWord()来解决附件名称的中文问题  
		     messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()), file);   
		    //发送邮件      
		     senderImpl.send(mailMessage);  
			 System.out.println("邮件发送成功！");
		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
