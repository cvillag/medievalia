package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class IntegerDOMMapper implements RowMapper<Double>{
	
	public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
		Double d = rs.getDouble("valor");
		return d;
	}
}
