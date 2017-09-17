package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StringDOMMapper implements RowMapper<String>{
	
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		String s = rs.getString("valor");
		return s;
	}
}
