package com.cyb.qutoes.listener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.ContextLoader;

import com.cyb.computer.CpuThread;
import com.cyb.push.PushServer;
import com.cyb.push.PushThread;
import com.cyb.qutoes.constant.QutoesContants;
import com.cyb.qutoes.constant.SpringUtil;
import com.cyb.utils.FileUtils;
import com.cyb.utils.PropertyUtil;

public class QutoesListener implements ServletContextListener {
	public static Logger log = Logger.getLogger(QutoesListener.class);
    public QutoesListener() {
    	log.info("QutoesListener init ...!");
    }

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		SpringUtil.wac = ContextLoader.getCurrentWebApplicationContext();
		/*try {
			PooledConnectionFactory fac = (PooledConnectionFactory) SpringUtil.getBean("connectionFactory");
			fac.getConnectionFactory();
			log.info("activemq 连接成功！");
			new Thread(new MessageSender()).start();
			new Thread(new MessageReceiver()).start();
			new Thread(new MessageReceiver()).start();
			new Thread(new MessageReceiver()).start();
		} catch (Exception e1) {
			log.info(e1.toString());
		}*/
		
		try {
			//RepositoryService repositoryService =  (RepositoryService) SpringUtil.getBean("repositoryService");
	    	//repositoryService.createDeployment().addResourceFromClasspath("process.jpdl.xml").deploy();
			PropertyUtil.init("App");
			//绝对路径 指定系统的任意目录
			String savePath = PropertyUtil.getValueByKey("App", "defaultUploadPath");
			File saveDir = new File(savePath);
			if(saveDir.isDirectory()&&!saveDir.exists()){
			   saveDir.mkdir();//创建目录
			}
			String savePathTemp = PropertyUtil.getValueByKey("App", "defaultTampUploadPath");
			File saveDirTemp = new File(savePathTemp);
			if(saveDirTemp.isDirectory()&&!saveDirTemp.exists()){
				saveDirTemp.mkdir();//创建目录
			}
			initCodeMap();
			initIndustry();
			
			new PushServer().startPushServer();
    		PushThread thread = new PushThread();
    		Thread push = new Thread(thread);
    		push.start();
    		
    		try {
				CpuThread cpu = new CpuThread();
				new Thread(cpu).start();
			} catch (Exception e1) {
				log.info(e1.toString());
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	  public static void initIndustry() {
		    String filePath = QutoesContants.WEBPATH + File.separator + "stock" + File.separator + "industry.txt";
		    StringBuffer content = FileUtils.readFileByLines(filePath);
		    QutoesContants.INDUSTRYSORT = new ArrayList();
		    QutoesContants.INDUSTRYSORTMAP = new HashMap<String, String>();
		    String[] industrys = content.toString().split(",");
		    Map species = null;
		    for (int i = 0; i < industrys.length; i++) {
		      species = new HashMap();
		      species.put("type", industrys[i].split("#")[0]);
		      species.put("name", industrys[i].split("#")[1]);
		      QutoesContants.INDUSTRYSORT.add(species);
		      QutoesContants.INDUSTRYSORTMAP.put(industrys[i].split("#")[0], industrys[i].split("#")[1]);
		    }
//		    log.info("行业分类：" + QutoesContants.INDUSTRYSORT);
		    log.info("行业分类初始化成功！");
		  }
    public void initCodeMap(){
    	QutoesContants.STOCKMAP = new HashMap<String, Object>();
    	QutoesContants.CODEINDUSTRY = new HashMap<String, String>();
//    	[{CODE=zs000001, NAME=}, {CODE=sh166105, NAME=信达增利}, {CODE=sh201000, NAME=R003}
    	JdbcTemplate dao = (JdbcTemplate) SpringUtil.getBean("jdbcTemplate");
    	QutoesContants.STOCKLIST = dao.queryForList("SELECT exCHANGE_ ||codE_  code,name_ name,industry  FROM STOCK");
    	if(QutoesContants.STOCKLIST!=null){
    		for(Map<String, Object> map:QutoesContants.STOCKLIST){
    			QutoesContants.STOCKMAP.put(map.get("CODE").toString(),map.get("NAME"));
    			QutoesContants.CODEINDUSTRY.put(map.get("CODE").toString(), map.get("INDUSTRY").toString());
    		}
    	}
//    	log.info("股票代码映射："+QutoesContants.STOCKMAP);
    	log.info("股票代码映射初始化成功！");
    }
    
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
}
