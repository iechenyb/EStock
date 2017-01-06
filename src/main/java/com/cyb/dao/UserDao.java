package com.cyb.dao;

import java.util.List;
import java.util.Map;

import com.cyb.page.Pagination;
import com.cyb.qutoes.vo.Idea;
import com.cyb.qutoes.vo.User;
public interface UserDao {
	public int  getNums();
    public List queryStu(Pagination page);
    public void addStu();
    public int delStu(Long number);
	public void log(Long thread,Long tradeNum,String result,int updCount);
	public void delLog();
	public int  add100(Integer num);
	public void addUser(User user);
	public void addIdea(Idea idea);
	public User queryUserByUserName(User user);
	public User queryUserByUserNamePassword(User user);
	public int updatePassword(String username);
	public List<Map<String, Object>> getUserConcernCode(String username);
	public int getTotal();
	public int getRealTradeNum();
}
