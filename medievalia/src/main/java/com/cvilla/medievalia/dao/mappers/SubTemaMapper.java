package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.SubTema;

public class SubTemaMapper implements RowMapper<SubTema>{

	public SubTema mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubTema s = new SubTema();
		s.setIdSubtema(rs.getInt("idSubtema"));
		s.setIdTema(rs.getInt("idTema"));
		s.setNombreSubtema(rs.getString("nombreSubtema"));
		s.setNombreTema(rs.getString("nombreTema"));
		return s;
	}

}
