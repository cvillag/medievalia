package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class UserIDMapper implements RowMapper<Integer> {

	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Integer i = rs.getInt("user_id");
		return i;
	}

}
