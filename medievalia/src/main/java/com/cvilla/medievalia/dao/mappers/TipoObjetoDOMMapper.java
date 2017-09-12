package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.TipoObjetoDOM;

public class TipoObjetoDOMMapper implements RowMapper<TipoObjetoDOM> {

	public TipoObjetoDOM mapRow(ResultSet rs, int rowNum) throws SQLException {
		TipoObjetoDOM t = new TipoObjetoDOM();
		t.setTipoDOM(rs.getInt("idObjeto"));
		t.setNombreDOM(rs.getString("nombreObjeto"));
		return t;
	}
}
