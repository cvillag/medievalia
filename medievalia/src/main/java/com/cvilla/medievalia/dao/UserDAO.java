package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.mappers.UserMapper;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.utils.Constants;
public class UserDAO implements IUserDAO {
	
	private static final String GET_LISTADO = "select * from users";
	private static final String GET_USER = "select * from users where user_name = ?";
	private static final String GET_USER_LOGIN = "SELECT * FROM `users` WHERE user_name=? and user_pass=AES_ENCRYPT(?,UNHEX('"+ Constants.getKey() + "'))";
	private static final String CREATE_USER = "INSERT INTO `users`( `user_name`, `user_long_name`, `user_pass`, `user_role`) VALUES (?,?,AES_ENCRYPT(?,UNHEX(?)),?)";
	
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

	public String nuevo(String name, String longname, String pass, String rol) {
		try{
			int row = jdbcTemplate.update(CREATE_USER, new Object[]{name,longname,pass,Constants.getKey(),rol});
			if(row < 1){
				return "";
			}
			else{
				return  Constants.M_CREATE_USER_OK;
			}
		}
		catch (DuplicateKeyException e){
			e.printStackTrace();
			return Constants.M_DUPLICATED_USER;
		}
	}
}
