package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;

public class TipoAtributoComplejoDOMMapper implements RowMapper<TipoAtributoComplejoDOM> {

	public TipoAtributoComplejoDOM mapRow(ResultSet rs, int rowNum) throws SQLException {
		TipoAtributoComplejoDOM t = new TipoAtributoComplejoDOM();
		t.setIdTipoPadre(rs.getInt("idObjetoPadre"));
		t.setIdTipoHijo(rs.getInt("idObjetoHijo"));
		t.setNombreAtributo(rs.getString("NombreAtributo"));
		return t;
	}
}
