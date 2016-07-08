package com.cvilla.medievalia.dao;

import java.sql.Date;

import com.cvilla.medievalia.domain.Log;

public interface ILogDAO {

	public boolean log(int idUser, int idAction, String desc, int succ);
	public Log getLogById(int id);
	public Log getLogByUser(int idUser);
	public Log getLogByDate(Date d);
	public Log getLogByDateRange(Date d1, Date d2);
	
}
