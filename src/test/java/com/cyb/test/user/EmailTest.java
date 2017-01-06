package com.cyb.test.user;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.cyb.base.JunitBase;

public class EmailTest extends JunitBase{
	public static String from = "383065059@qq.com";
	public static String to = "zzuchenyb@sina.com";
	public JavaMailSender mailSender;
	@Before
	public void init(){
		mailSender = (JavaMailSender) ac.getBean("qqMailSender");
	}
	//发送文本
	@Test
	public void sendTextMail() {
		try {
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
	//发送网页
	@Test
	public void sendHtmlMail() {
		try {
			//建立邮件消息,发送简单邮件和html邮件的区别      
		     MimeMessage mailMessage = mailSender.createMimeMessage();      
		     MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");      
		    //设置收件人，寄件人      
		     messageHelper.setFrom(from);      
		     messageHelper.setTo(to);      
		     messageHelper.setSubject("带附件的html邮件发送测试");      
		    //true 表示启动HTML格式的邮件      
		     String html= "<html><head></head><body>"
		     		+ "<font color='red'>hello!!zhangjian</font>"
		    		+"<img src='http://121.42.144.78/file/1.jpg' ></img>"
		     		+ "123123</body></html>";
		     System.out.println(html);
		     messageHelper.setText(html,true);
		     String file1 ="C:\\Users\\WEIBO\\workspace\\EStockSvn\\src\\main\\webapp\\file\\1.jpg";
		     String file2 ="C:\\Users\\WEIBO\\workspace\\EStockSvn\\src\\main\\webapp\\file\\2.jpg";
		     String file3 ="C:\\Users\\WEIBO\\workspace\\EStockSvn\\src\\main\\webapp\\file\\test.rar";
		     messageHelper.addInline("aaaa", new File(file1));//"http://121.42.144.78/file/1.jpg")  
		     messageHelper.addInline("bbbb", new File(file2));   
		     File file=new File(file3);    
		     // 这里的方法调用和插入图片是不同的，使用MimeUtility.encodeWord()来解决附件名称的中文问题  
		     messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()), file);   
		    //发送邮件      
		     mailSender.send(mailMessage);  
			 System.out.println("邮件发送成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
