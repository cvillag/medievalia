package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.ObjetoDOM;

public class ObjetoDOMMapper implements RowMapper<ObjetoDOM> {

	public ObjetoDOM mapRow(ResultSet rs, int rowNum) throws SQLException {
		ObjetoDOM t = new ObjetoDOM();
		t.setIdInstancia(rs.getInt("idInstancia"));
		t.setIdObjetoDOM(rs.getInt("idObjeto"));
		t.setNombre(rs.getString("nombreInstancia"));
		t.setValidado(rs.getInt("validado"));
		t.setCreador(rs.getInt("creador"));
		t.setTextoValidacion(rs.getString("textoValidacion"));
		t.setGrupo(rs.getInt("idGrupo"));
		return t;
	}
}
