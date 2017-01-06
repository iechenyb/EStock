package com.cyb.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.cyb.dao.UserDao;
import com.cyb.dao.impl.HibernateDaoImpl;
import com.cyb.qutoes.vo.User;

@Service("hibernateService")
public class HibernateServiceImpl {
	@Resource(name = "hibernateDao")
	public HibernateDaoImpl hibernateDao;
	public void testSaveNoSetId(){
		this.hibernateDao.testSaveNoSetId();
	}
	public void testSaveNoSetId(User user){
		this.hibernateDao.testSaveNoSetId(user);
	}
	public void testUpdate1(String id){
		this.hibernateDao.testUpdateLoadObject(id);
	}
	public void testUpdate2(String id){
		this.hibernateDao.testUpdateNewObject(id);
	}
	public void testDeleteLoadObject(String id){
		this.hibernateDao.testDeleteLoadObject( id);
	}
	public void testDeleteNewObject(String id){
		this.hibernateDao.testDeleteNewObject( id);
	}
	public Iterator<User> getSomeUser(String name){
		return this.hibernateDao.getSomeUser(name);
	}
	public List<User> getUserList(String name){
		return this.hibernateDao.getUserList(name);
	}
	public Map getUserMap(String name){
		return this.hibernateDao.getUserMap(name);
	}
	public void getLoadUser(String id){
		this.hibernateDao.getLoadUser(id);
	}
}
