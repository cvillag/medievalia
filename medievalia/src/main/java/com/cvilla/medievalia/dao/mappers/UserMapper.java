package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.User;

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("user_id"));
		u.setUser_name(rs.getString("user_name"));
		u.setUser_long_name(rs.getString("user_long_name"));
		u.setUser_pass(rs.getBytes("user_pass"));
		u.setUser_role(rs.getInt("user_role"));
		return u;
	}

}
