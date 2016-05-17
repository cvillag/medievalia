package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.Autorization;

public class AutorizationMapper implements RowMapper<Autorization> {

	public Autorization mapRow(ResultSet rs, int arg1) throws SQLException {
		Autorization a = new Autorization();
		a.setAction(rs.getInt("idAction"));
		a.setRol(rs.getInt("idRol"));
		
		return a;
	}

}
