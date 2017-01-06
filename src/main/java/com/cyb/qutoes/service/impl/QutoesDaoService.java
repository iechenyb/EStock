package com.cyb.qutoes.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyb.qutoes.dao.impl.QutoesDaoImpl;
@Service("qutoesDaoService")
public class QutoesDaoService {
	@Resource
	QutoesDaoImpl qutoesDaoImpl; 
}
