package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.TemaGrupo;

public class TemaGrupoMapper implements RowMapper<TemaGrupo> {

	public TemaGrupo mapRow(ResultSet rs, int rowNum) throws SQLException {
		TemaGrupo t = new TemaGrupo();
		t.setIdGroup(rs.getInt("idGroup"));
		t.setIdTema(rs.getInt("idTema"));
		t.setNombreTema(rs.getString("nombreTema"));
		t.setNombreGrupo(rs.getString("nombreGrupo"));
		return t;
	}

}
