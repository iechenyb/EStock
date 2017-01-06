package com.cyb.test.xtqx;

import org.junit.Before;
import org.junit.Test;

import com.cyb.base.JunitBase;
import com.cyb.xtqx.bean.RoleQX;
import com.cyb.xtqx.dao.XtqxDaoImpl;
/**
 * hibernate many to many annotation test  
 * @author DHUser
 *
 */
public class QXGLTest extends JunitBase{
	XtqxDaoImpl dao  ;
    @Before
    public void loadXml() {
    	dao = (XtqxDaoImpl) ac.getBean("xtqxDao");
    }
    @Test
    public void addRole() {
    	for(int i=0;i<10;i++){
    		RoleQX role  = new RoleQX();
    		role.setName("jb"+i);
    		dao.addRole(role);
    	}
    }
    /**
     * 保存一对多关系
     */
	@Test
    public void readerBooks(){
    	dao.saveReaderBook(null);
    	System.out.println("*******");
    }
	
	@Test
    public void getClasses(){
		dao.getClass("4028905357085a440157085a4a8d0000");
		//查询班级，一对多关系，一个班级多个学生
	}
	
	@Test
	public void studentClasses(){
		dao.saveStuClazz(null);
    	System.out.println("*******");
	}
	@Test
	public void getReader(){
		dao.getReader(1);//查询一对多关系，一个读者多本书
	}
	@Test
	public void insertUserRoles(){
		dao.insertUserRoles();//插入复杂的多对多关系
	}
	@Test
	public void getUsers(){
		dao.getUsers(1);//查询id为1的用户，并附带查询所有的角色
	}
	@Test
	public void getRoles(){
		dao.getRoles(1);//查询id为1的角色，并附带查询所有的用户
	}
	@Test
	public void delRole(){
		dao.delRole(1);//查询id为1的角色，并附带查询所有的用户
	}
}
