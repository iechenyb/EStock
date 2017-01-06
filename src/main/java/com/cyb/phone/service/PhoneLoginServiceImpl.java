package com.cyb.phone.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cyb.phone.dao.PhoneLoginDaoImpl;
import com.cyb.phone.vo.User;
@Repository("phoneLoginService")
public class PhoneLoginServiceImpl {
	@Resource(name="phoneLoginDao")
	PhoneLoginDaoImpl phoneLoginDao ; 	
	public boolean checkLogin(User user){
    return this.phoneLoginDao.checkLogin(user);
	}
}
