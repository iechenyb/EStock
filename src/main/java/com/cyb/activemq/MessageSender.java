package com.cyb.activemq;

/*
 * xcch@cnic.cn
 * ��Ϣ������
 */
import java.util.Date;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.cyb.qutoes.listener.QutoesListener;
public class MessageSender  implements Runnable{
	public static Logger log = Logger.getLogger(MessageSender.class);
     public static void main(String[] args) throws Exception {
         ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover:(tcp://192.168.16.211:61616)?timeout=3000&startupMaxReconnectAttempts=1&maxReconnectAttempts=0");//failover:ʧЧת��
         //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");//61616
         javax.jms.Connection connection = connectionFactory.createConnection();
         //connection.setExceptionListener(new MyJmsException());
         connection.start();
         Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
         Destination destination = session.createQueue("my-queue");
         /*session = connection.createSession( true, Session. AUTO_ACKNOWLEDGE);
         destination = session.createTopic("FirstTopic");*/
         MessageProducer producer = session.createProducer(destination);
         for(int i=0; i<3; i++) {
             MapMessage message = session.createMapMessage();
             message.setLong("count", new Date().getTime());
             Thread.sleep(1000);
             producer.send(message);
         }
         session.commit();
         session.close();
         connection.close();
    }

	@Override
	public void run() {
		 try {
			 //Messages Enqueued  = Number Of Pending Messages + Messages Dequeued ;
			//ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover:(tcp://192.168.16.211:61616)?timeout=3000&startupMaxReconnectAttempts=1&maxReconnectAttempts=0");//failover:ʧЧת��
			 ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");//wireFormat.maxInactivityDurationInitalDelay=30000
			 javax.jms.Connection connection = connectionFactory.createConnection();
			 //connection.setExceptionListener(new MyJmsException());
			 connection.start();
			 Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			 Destination destination = session.createQueue("my-queue");
			 MessageProducer producer = session.createProducer(destination);
			 log.info("生产者开始产生消息！");
			 while(true){
			     MapMessage message = session.createMapMessage();
			     long time = new Date().getTime();
			     message.setLong("count", time);
			     //log.info("activemq create a msg  is "+time);
			     //Thread.sleep(5);
			     producer.send(message);
			     session.commit();
			 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			 /*session.commit();
			 session.close();
			 connection.close();*/
		}
	} 
     
}
