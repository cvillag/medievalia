package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.TipoAtributoComplejo;
import com.cvilla.medievalia.domain.TipoObjeto;

public class TipoAtributoComplejoDOMMapper implements RowMapper<TipoAtributoComplejo> {

	public TipoAtributoComplejo mapRow(ResultSet rs, int rowNum) throws SQLException {
		TipoAtributoComplejo t = new TipoAtributoComplejo();
		t.setIdTipoPadre(rs.getInt("idObjetoPadre"));
		t.setIdTipoHijo(rs.getInt("idObjetoHijo"));
		t.setNombreAtributo(rs.getString("NombreAtributo"));
		t.setIdTipoRelacion(rs.getInt("idObjetoRelacion"));
		t.setConFecha(rs.getInt("conFecha"));
		t.setConPaginaDoc(rs.getInt("conPaginaDoc"));
		return t;
	}
}
