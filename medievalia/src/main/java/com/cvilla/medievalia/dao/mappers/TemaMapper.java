package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.Tema;

public class TemaMapper implements RowMapper<Tema> {

	public Tema mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tema t = new Tema();
		t.setIdTema(rs.getInt("idTema"));
		t.setNombre(rs.getString("nombre"));
		t.setIdGroup(rs.getInt("idGrupo"));
		t.setNombreGrupo(rs.getString("nombreGrupo"));
		t.setNumSubtemas(rs.getInt("numSubtemas"));
		return t;
	}

}
