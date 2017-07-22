package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.Study;

public class StudyMapper implements RowMapper<Study> {

	public Study mapRow(ResultSet rs, int rowNum) throws SQLException {
		Study g = new Study();
		g.setIdStudy(rs.getInt("idEstudio"));
		g.setIdGroup(rs.getInt("idGrupo"));
		g.setNombre(rs.getString("nombre"));
		g.setIdCreator(rs.getInt("creador"));
		g.setValidado(rs.getInt("validado"));
		g.setNameCreator(rs.getString("nameCreator"));
		return g;
	}

}
