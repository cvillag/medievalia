package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.ObjetoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;

public class ObjetoDOMMapper implements RowMapper<ObjetoDOM> {

	public ObjetoDOM mapRow(ResultSet rs, int rowNum) throws SQLException {
		ObjetoDOM o = new ObjetoDOM();
		o.setIdInstancia(rs.getInt("idInstancia"));
		TipoObjetoDOM t = new TipoObjetoDOM();
		t.setTipoDOM(rs.getInt("idObjeto"));
		o.setTipo(t);
		o.setNombre(rs.getString("nombreInstancia"));
		o.setValidado(rs.getInt("validado"));
		o.setCreador(rs.getInt("creador"));
		o.setTextoValidacion(rs.getString("textoValidacion"));
		o.setGrupo(rs.getInt("idGrupo"));
		return o;
	}
}
