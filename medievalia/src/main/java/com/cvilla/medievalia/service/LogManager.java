package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.ILogDAO;
import com.cvilla.medievalia.domain.Log;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.PaginaException;

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

	public List<Log> getActivity(int idUser, int pag, int tamPag, boolean order) throws PaginaException {
		int num = logDao.getNumLogByUser(idUser);
		if(pag-1 <= num/tamPag)
			return logDao.getLogByUser(idUser, pag, tamPag, order);
		else
			throw new PaginaException("La página solicitada es superior a las disponibles");
	}

	public int getNumPag(int idUser, int tamPag) {
		int pags = logDao.getNumLogByUser(idUser)/tamPag + 1;
		return pags;
	}
	
	public int getNumPag(int tamPag) {
		int pags = logDao.getNumLog()/tamPag + 1;
		return pags;
	}
	
	public List<Log> getActivity(int pag, int tamPag, boolean order) throws PaginaException {
		int num = logDao.getNumLog();
		if(pag-1 <= num/tamPag)
			return logDao.getLog(pag, tamPag, order);
		else
			throw new PaginaException("La página solicitada es superior a las disponibles");
	}
	
}
