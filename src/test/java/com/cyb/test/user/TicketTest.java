package com.cyb.test.user;

import org.junit.Before;
import org.junit.Test;

import com.cyb.base.JunitBase;
import com.cyb.dao.impl.TicketDaoImpl;

public class TicketTest extends JunitBase{
	TicketDaoImpl dao;
	@Before
    public void loadXml() {
        dao = (TicketDaoImpl) ac.getBean("ticketDao");
    }

    @Test
    public void loadTicket() {
        dao.loadTicket("1");
    }
    @Test
    public void loadUser() {
        dao.loadUser("402890535707b19f015707b20d9d0001");
    }

}
