package com.cvilla.medievalia.dao;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.domain.Log;

public class LogDAO implements ILogDAO {

	private static final String INSERT_LOG = "INSERT INTO `log`(`idUser`, `idAction`, `time`, `description`, `success`) VALUES (?,?,NOW(),?,?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public boolean log(int idUser, int idAction, String desc, int succ) {
		try{
			return jdbcTemplate.update(INSERT_LOG,new Object[]{idUser,idAction,desc,succ}) == 1;
		}
		catch(Exception e){
			return false;
		}
	}

	public Log getLogById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Log getLogByUser(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	public Log getLogByDate(Date d) {
		// TODO Auto-generated method stub
		return null;
	}

	public Log getLogByDateRange(Date d1, Date d2) {
		// TODO Auto
		return null;
	}

}
