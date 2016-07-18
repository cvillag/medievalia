package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IRoleDAO;
import com.cvilla.medievalia.dao.mappers.RoleMapper;
import com.cvilla.medievalia.domain.Role;

public class RoleDAO implements IRoleDAO {

	private static final String GET_LIST = "select * from role";
	
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
	
	public List<Role> getList() {
		List<Role> roles = (List<Role>)jdbcTemplate.query(GET_LIST, new RoleMapper());
		return roles;
	}

}
