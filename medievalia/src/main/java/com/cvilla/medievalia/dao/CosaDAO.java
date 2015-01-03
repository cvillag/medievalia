package com.cvilla.medievalia.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CosaDAO implements ICosaDAO{

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

	public String getNombre(int id){
		String nombre = getJdbcTemplate().queryForObject("select nombre from cosas where id = 5", String.class);
		return nombre;
	}
}
