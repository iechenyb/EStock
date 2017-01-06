package com.cyb.xtqx.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.dao.impl.BaseDaoImpl;
import com.cyb.xtqx.bean.Book;
import com.cyb.xtqx.bean.Clazz;
import com.cyb.xtqx.bean.Reader;
import com.cyb.xtqx.bean.RoleQX;
import com.cyb.xtqx.bean.Student;
import com.cyb.xtqx.bean.UserQX;

@Repository("xtqxDao")
@Transactional
public class XtqxDaoImpl extends BaseDaoImpl{
	
  public void addRole(RoleQX role){
	  System.out.println(role.getName());
	  Session session = this.getSession();
	  session.save(role);
  }
  public RoleQX loadRole(RoleQX role){
	  System.out.println(role.getName());
	  Session session = this.getSession();
	  role = (RoleQX) session.load(RoleQX.class, role.getId());
	  return role;
  }
  public void saveReaderBook(Reader obj){
	  System.out.println("begin!");
	  Session session = this.getSession();
	  Reader r = new Reader();
	  	r.setName("Reader zhang");
	  	Book b1 = new Book();
	  	b1.setTitle("title1");
	  	Book b2 = new Book();
	  	b2.setTitle("title2");
	  	b1.setReader(r);
	  	b2.setReader(r);
	  	r.getBooks().add(b1);
	  	r.getBooks().add(b2);
	  	session.save(r);
	  System.out.println("over!");
  }
  public void saveStuClazz(Reader obj){
	  Session session = this.getSession();
	  Student s1 = new Student();  
      Student s2 = new Student();  
      Clazz c1 = new Clazz();  
      c1.setName("1班");  
      s1.setName("z1");  
      s1.setClazz(c1);  
      s2.setName("z2");  
      s2.setClazz(c1);  
      Set<Student> set = new HashSet<Student>();  
      set.add(s1);
      set.add(s2);  
      c1.setStudents(set);  
      System.out.println("123123!");
      session.save(c1);
      System.out.println("over!");
  }
  public void getClass(String id){  
      Session session = this.getSession();
      Clazz c2 = (Clazz) session.get(Clazz.class, id);  
      Set<Student> sss =  c2.getStudents();  
      for(Student s: sss){  
          System.out.println(s.getName());  
      }  
  }  
  public void getReader(int id){  
      Session session = this.getSession();
      Reader c2 = (Reader) session.get(Reader.class, id);  
      Set<Book> sss =  c2.getBooks();  
      for(Book s: sss){  
          System.out.println(s.getTitle());  
      }  
  }
  /**
   * 保存多对多关系，不需要建立多对多关系bean，只需要创建两个“多”表，
   * 按照如下逻辑保存可以同时向多对多设计的三张表中插入数据，请具体查看数据库！
   */
  public void insertUserRoles(){
	  /*create table user_role (
		        user_id integer not null,
		        role_id integer not null,
		        primary key (user_id, role_id)
		    )*/
	  Session session = this.getSession();
	  UserQX user=new UserQX();
	  //user.setId(2);//不设置id默认新增
      user.setName("lisi");
      
      RoleQX role1=new RoleQX();
      //role1.setId(1);////不设置id默认新增
      role1.setName("管理员1");
      RoleQX role2=new RoleQX();
      //role2.setId(2);
      role2.setName("管理员2");
      
      Set<RoleQX> roles=new HashSet<RoleQX>();
      roles.add(role1);
      roles.add(role2);
      user.setRoles(roles);
      session.save(user);
  }
  public void getUsers(int id){
	  Session session = this.getSession();
      UserQX c2 = (UserQX) session.get(UserQX.class, id);  
      Set<RoleQX> sss =  c2.getRoles();  
      for(RoleQX s: sss){  
          System.out.println(s.getName());  
      }  
  }
  public void getRoles(int id){
	  Session session = this.getSession();
	  RoleQX c2 = (RoleQX) session.get(RoleQX.class, id);  
      Set<UserQX> sss =  c2.getUsers();  
      for(UserQX s: sss){  
          System.out.println(s.getName());  
      }  
  }
  public void delRole(int id){
	  Session session = this.getSession();
	  RoleQX role = new RoleQX();
	  role.setId(id);
	  session.delete(role);
  }
}
