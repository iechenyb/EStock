package com.cyb.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.dao.UserDao;
import com.cyb.page.Pagination;
import com.cyb.qutoes.vo.Idea;
import com.cyb.qutoes.vo.User;
import com.cyb.utils.MD5Encoder;
@Repository("userDao")
public class UserDaoImpl implements UserDao{
	@Resource(name="jdbcTemplate")
	JdbcTemplate jdbcTemplate; 
	
	@Resource(name="sessionFactory")
	SessionFactory sessionFactory;
	
	protected static Logger log = Logger.getLogger(UserDaoImpl.class);
	
	public String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
    public void initDDL(){
    	String del = "DROP TABLE IF EXISTS Student";
		String create = "CREATE TABLE Student(id varchar(64),NAME VARCHAR(255),age integer,sex integer)";
		String sql1="INSERT INTO Student VALUES('"+getUUID()+"', 'Hello',20,1)	";	
		String sql2=" INSERT INTO Student VALUES('"+getUUID()+"', 'World',25,0)";
		this.jdbcTemplate.execute(del);
		this.jdbcTemplate.execute(create);
		this.jdbcTemplate.execute(sql1);
		this.jdbcTemplate.execute(sql2);
    }
    public int getTotal(){
		String query = "select count(*) from buy_log ";
		return this.jdbcTemplate.queryForInt(query);
	}
	public List queryStu(Pagination page){		
		String sql = "select * from buy_log order by time_  asc";
		String sql2 = " select xh,* from("+
				" select  rownum as xh,* from("+
				sql+
				" ) "+
				" ) where  xh>="+page.getOffset()+" and xh<="+page.getCurrentPage()*page.getPageSize();
		log.info(sql2);
    	List<Map<String, Object>> list  = this.jdbcTemplate.queryForList(sql2);
    	try {
			this.jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return list;
    }
	
	public JdbcTemplate getJdbcTemplate() {
		log.info("jdbc:"+this.jdbcTemplate);
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		log.info("jdbc:"+this.jdbcTemplate);
	}
	@Override
	public void addStu() {
		String sql=" INSERT INTO Student VALUES('"+getUUID()+"', 'World',25,0)";
		this.jdbcTemplate.execute(sql);
	}
	
	@Override
	public int delStu(Long number) {
		String sql_1 = "select nvl(name,0) from ticket where id = 1";
		int before = this.jdbcTemplate.queryForInt(sql_1); 
		String sql = "update TEST set num=num-"+number+" where id=1 and num>"+number;
		int updCount = this.jdbcTemplate.update(sql);
//		int after_ = this.jdbcTemplate.queryForInt(sql_1); 
		return updCount;
	}
	//synchronized
	public synchronized  void log(Long thread,Long reqNum,String window,int updCount){
		String sql_1 = "select nvl(num,0) from ticket where id = 1";
		String result = "";
		int before_ = this.jdbcTemplate.queryForInt(sql_1); 
		long trade = 0;
		String sql  = "";
		if(reqNum>0 && reqNum>before_){//部分提交  请求100 ，实际只有50，则购买50
			 trade = before_;
			 sql = "update ticket set num=0 where id=1 ";
		}else{
			 trade = reqNum;
			 sql = "update ticket set num=num-"+reqNum+" where id='1' and num>="+reqNum;
		}
		int updCount_ = this.jdbcTemplate.update(sql);
		
		int after_ = this.jdbcTemplate.queryForInt(sql_1); 
		
		if(before_!=after_){
			result="购买成功！";
		}else{
			if(before_==0){
				trade =0;
				result="已经售罄！";
			}else{
				result="部分购买！";
			}
		}
		if(trade!=0&&trade<reqNum){
			result="部分购买！";
		}
		String sql_log = "insert into buy_log (reqstr,tradeNum,handlerresult,time_ ,before_ ,after_) "
				+ "values('Window["+window+"]Thread["+thread+"]#"+reqNum+"',"+trade+",'"+result+"',sysdate,"+before_+","+after_+")";
		this.jdbcTemplate.execute(sql_log);
	}
	
	public int getRealTradeNum(){
		String sql = "select sum(tradeNum) from buy_log where tradeNum>0";
		return this.jdbcTemplate.queryForInt(sql);
	}
	@Override
	public void delLog() {
      String sql = " delete from buy_log";
      this.jdbcTemplate.execute(sql);
	}
	public int  add100(Integer num){
		this.jdbcTemplate.execute("update ticket set num=num+"+num+" where id=1");
		int curr = getNums(); 
		return curr;
	}
	public int  getNums(){
		String sql_1 = "select nvl(num,0) from ticket where id = 1";
		int curr = this.jdbcTemplate.queryForInt(sql_1); 
		return curr;
	}
	
	public Session getSession(){
		Session session = null ;
		try {
			session = sessionFactory.getCurrentSession();
			if(session==null){
				session = sessionFactory.openSession();
			}
		} catch (Exception e) {
			return sessionFactory.openSession();
		}
		return session;
	}
	@Transactional
	public void addUser(User user){
		this.getSession().save(user);
		log.info("用户注册成功！");
	}
	public void addIdea(Idea idea){
		this.getSession().save(idea);
	}
	public User queryUserByUserName(User user){
		String sql="select * from usr where username = ?";
		Query query = this.getSession().createSQLQuery(sql).addEntity(User.class).setString(0, user.getUsername());
		List<User> list = (List<User>)query.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	public User queryUserByUserNamePassword(User user){
		String sql="select * from usr where username = ? and password = ?";
		Query query = this.getSession().createSQLQuery(sql).addEntity(User.class)
				.setString(0, user.getUsername()).setString(1, MD5Encoder.encode(user.getPassword()));
		List<User> list = (List<User>)query.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public int updatePassword(String username){
		Query query = this.getSession().createQuery("update User t set t.password = '"+MD5Encoder.encode("111111")+"' where username = '"+username+"'");
		int numbers = query.executeUpdate();
		return numbers;
	}
	public List<Map<String, Object>> getUserConcernCode(String username){
		if(username==null||"".equals(username)){return null;}
		String sql ="SELECT * FROM USERCONCERN where userid='"+username+"' and rownum<=10";
		return this.jdbcTemplate.queryForList(sql);
	}
}
