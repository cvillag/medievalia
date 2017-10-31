package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.InstanciaObjetoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;

public class ObjetoDOMMapper implements RowMapper<InstanciaObjetoDOM> {

	public InstanciaObjetoDOM mapRow(ResultSet rs, int rowNum) throws SQLException {
		InstanciaObjetoDOM o = new InstanciaObjetoDOM();
		o.setIdInstancia(rs.getInt("idInstancia"));
		TipoObjetoDOM t = new TipoObjetoDOM();
		t.setTipoDOM(rs.getInt("idObjeto"));
		o.setTipo(t);
		o.setNombre(rs.getString("nombreInstancia"));
		o.setValidado(rs.getInt("validado"));
		User u = new User();
		u.setId(rs.getInt("creador"));
		o.setCreador(u);
		o.setTextoValidacion(rs.getString("textoValidacion"));
		o.setGrupo(rs.getInt("idGrupo"));
		o.setTextoLeido(rs.getInt("textoLeido"));
		return o;
	}
}
