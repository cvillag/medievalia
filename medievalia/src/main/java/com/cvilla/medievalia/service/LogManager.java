package com.cvilla.medievalia.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.ILogDAO;

public class LogManager implements ILogManager{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ILogDAO logDao;

	@Autowired
	public ILogDAO getLogDao() {
		return logDao;
	}

	@Autowired
	public void setLogDao(ILogDAO logDao) {
		this.logDao = logDao;
	}

	public void log(int idUser, int idAction, String desc, int succ) {
		logDao.log(idUser, idAction, desc, succ);
	}
	
	
	
}
