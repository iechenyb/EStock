package com.cyb.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.cyb.qutoes.vo.annotaion.bean.Ticket;
import com.cyb.xtqx.bean.UserQX;
/**
 * test hibernate annotation bean
 * @author DHUser
 *
 */
@Repository("ticketDao")
public class TicketDaoImpl extends BaseDaoImpl{
  Log log = LogFactory.getLog(TicketDaoImpl.class);	
  public Ticket loadTicket(String id){
	  Ticket t = (Ticket) this.getSession().load(Ticket.class, id);
	  log.info("--------------------------");
	  log.info(t);  
	  return t;
  }
  public UserQX loadUser(String id){
	  UserQX t = (UserQX) this.getSession().load(UserQX.class, id);
	  log.info("--------------------------");
	  log.info(t.getName());  
	  return t;
  }
}
