package com.cvilla.medievalia.dao.intfc;

import java.sql.Date;
import java.util.List;

import com.cvilla.medievalia.domain.Log;

public interface ILogDAO {

	public boolean log(int idUser, int idAction, String desc, int succ);
	public Log getLogById(int id);
	public List<Log> getLogByUser(int idUser, int pag, int tamPag, boolean order);
	public Log getLogByDate(Date d);
	public Log getLogByDateRange(Date d1, Date d2);
	public int getNumLogByUser(int idUser);
	public int getNumLog();
	public List<Log> getLog(int pag, int tamPag, boolean order);
	
}
