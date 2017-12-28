package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.TipoObjeto;

public class TipoObjetoDOMMapper implements RowMapper<TipoObjeto> {

	public TipoObjeto mapRow(ResultSet rs, int rowNum) throws SQLException {
		TipoObjeto t = new TipoObjeto();
		t.setTipoDOM(rs.getInt("idObjeto"));
		t.setNombreDOM(rs.getString("nombreObjeto"));
		return t;
	}
}
