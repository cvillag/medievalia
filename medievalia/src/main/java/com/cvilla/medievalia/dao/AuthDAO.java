package com.cvilla.medievalia.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.mappers.AutorizationMapper;

public class AuthDAO implements IAuthDAO {
	
	private final String GET_AUTH = "SELECT * FROM  authorization WHERE idAction =? AND idRol =?";
	
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

	public boolean isAuthorized(int role, int action) {
		try{
			jdbcTemplate.queryForObject(GET_AUTH,new Object[]{role,action}, new AutorizationMapper());
			return true;
		}
		catch(EmptyResultDataAccessException e){
			return false;
		}
		
	}

}
