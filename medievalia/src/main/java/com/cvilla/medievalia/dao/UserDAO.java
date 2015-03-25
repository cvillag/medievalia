package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.mappers.UserMapper;
import com.cvilla.medievalia.domain.User;

public class UserDAO implements IUserDAO {
	
	private static final String GET_LISTADO = "select * from users";
	private static final String GET_USER = "select * from users where user_name = ?";
	private static final String GET_USER_LOGIN = "select * from users where user_name = ? and user_pass=sha1(?)";
	
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

	public void nuevo(User u) {

	}

	public List<User> list() {
		List<User> users = getJdbcTemplate().query(GET_LISTADO, new UserMapper());
		return users;
	}

	public User getUserByName(String name) {
		User user;
		try{
			user = (User)jdbcTemplate.queryForObject(GET_USER, new Object[] {name}, new UserMapper());
		}
		catch(EmptyResultDataAccessException e){
			user = null;
		}
		return user;
	}

	public User login(String name, String pass) {
		User user;
		try{
			user = (User)jdbcTemplate.queryForObject(GET_USER_LOGIN, new Object[]{name,pass},new UserMapper());
		}
		catch(EmptyResultDataAccessException e){
			user = null;
		}
		return user;
	}
}
