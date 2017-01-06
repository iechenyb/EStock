package com.cyb.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyb.dao.UserDao;
import com.cyb.page.Pagination;
import com.cyb.qutoes.vo.Idea;
import com.cyb.qutoes.vo.User;
public interface UserService {
 public int  getNums();
 public void delLog();
 public List getList(Pagination page);
 public void addStu();
 public int delStu(Long number);
 public void log(Long thread, Long tradeNum,String result, String queryStr,int updCount );
 public int  add100(Integer num);//火车票模拟接口，放票100
 public void addUser(User user);
 public void addIdea(Idea idea);
 public User queryUserByUserName(User user);
 public User queryUserByUserNamePassword(User user);
 public int updatePassword(String username);
 public List<Map<String, Object>> getUserConcernCode(String username);
 public int getTotal();
 public int getRealTradeNum();
}
