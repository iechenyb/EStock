package com.cyb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyb.dao.UserDao;
import com.cyb.page.Pagination;
import com.cyb.qutoes.vo.Idea;
import com.cyb.qutoes.vo.User;
import com.cyb.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name = "userDao")
	public UserDao userdao;

	public List getList(Pagination page) {
		return this.userdao.queryStu(page);
	}

	@Override
	public void addStu() {

	}

	@Override
	public int delStu(Long number) {
		return this.userdao.delStu(number);
	}

	@Override
	public void log(Long thread, Long tradeNum, String result, String queryStr,
			int updCount) {
		this.userdao.log(thread, tradeNum, result, updCount);

	}

	@Override
	public void delLog() {
		this.userdao.delLog();
	}

	@Override
	public int add100(Integer num) {
		return this.userdao.add100(num);
	}

	@Override
	public void addUser(User user) {
		this.userdao.addUser(user);
	}

	@Override
	public void addIdea(Idea idea) {
		this.userdao.addIdea(idea);
	}
	public User queryUserByUserName(User user){
		if(user.getUsername()==null){
			return null;
		}
		return this.userdao.queryUserByUserName(user);
	}

	@Override
	public User queryUserByUserNamePassword(User user) {
		return this.userdao.queryUserByUserNamePassword(user);
	}

	@Override
	public int updatePassword(String username) {
		return this.userdao.updatePassword(username);
	}

	@Override
	public List<Map<String, Object>> getUserConcernCode(String username) {
		return this.userdao.getUserConcernCode(username);
	}
	public int  getNums(){
		return this.userdao.getNums();
	}

	@Override
	public int getTotal() {
		
		return userdao.getTotal();
	}
	public int getRealTradeNum(){
		return userdao.getRealTradeNum();
	}
}
