package com.cyb.activemq;

/*
 * http://activemq.apache.org/activemq-5133-release.html
 * http://blog.csdn.net/xh16319/article/details/12142249
   http://www.tuicool.com/articles/jABfEff
   http://127.0.0.1:8161/admin/
   Exception in thread "main" javax.jms.IllegalStateException: Cannot synchronously receive a message when a MessageListener is set
 */

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.cyb.qutoes.listener.QutoesListener;

public class MessageReceiver implements Runnable{
	public static Logger log = Logger.getLogger(MessageReceiver.class);
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        connection.start();
        final Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("my-queue");
        MessageConsumer consumer = session.createConsumer(destination);
        //consumer.setMessageListener(new JMSMSGListener());//set msg listen 监听和直接接收消息二者只能选择一个
         long i = 0;
        while (true) {
        	if(i>Long.MAX_VALUE-1){i=0;}
            i++;
            MapMessage message = (MapMessage) consumer.receive();
            session.commit();
            //log.info("*get message is " + new Date(message.getLong("count")));
        }
        /*session.close();
        connection.close();*/
    }
    public static void start(){
    	
    }
	@Override
	public void run() {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
			Connection connection = connectionFactory.createConnection();
			connection.start();
			final Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("my-queue");
			MessageConsumer consumer = session.createConsumer(destination);
			//consumer.setMessageListener(new JMSMSGListener(consumer));//set msg listen 监听和直接接收消息二者只能选择一个
			log.info("消费者已启动！");
			while (true) {
			    MapMessage message = (MapMessage) consumer.receive();
			    session.commit();
			   // log.info("get message is " + new Date(message.getLong("count")));
			}
			//session.close();
			//connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}