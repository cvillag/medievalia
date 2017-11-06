package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.InstanciaAtributoSencilloDOM;
import com.cvilla.medievalia.utils.Constants;

public class AtributoSencilloDOMMapper implements RowMapper<InstanciaAtributoSencilloDOM>{
	
	public InstanciaAtributoSencilloDOM mapRow(ResultSet rs, int rowNum) throws SQLException {
		InstanciaAtributoSencilloDOM a = new InstanciaAtributoSencilloDOM();
		a.setIdAtributo(rs.getInt("idAtributo"));
		a.setNombreTipoAtributo(rs.getString("nombreAtributo"));
		a.setTipoAtributo(rs.getInt("tipo"));
		a.setSubtipo(rs.getInt("subtipo"));
		a.setValor(null);
		
		return a;
	}
}
