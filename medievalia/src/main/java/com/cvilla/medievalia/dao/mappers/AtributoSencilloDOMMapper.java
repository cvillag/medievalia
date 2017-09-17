package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.AtributoSencilloDOM;
import com.cvilla.medievalia.utils.Constants;

public class AtributoSencilloDOMMapper implements RowMapper<AtributoSencilloDOM>{
	
	public AtributoSencilloDOM mapRow(ResultSet rs, int rowNum) throws SQLException {
		AtributoSencilloDOM a = new AtributoSencilloDOM();
		a.setIdAtributo(rs.getInt("idAtributo"));
		a.setNombreTipoAtributo(rs.getString("nombreAtributo"));
		a.setTipoAtributo(rs.getInt("tipo"));
		a.setValor(null);
		
		return a;
	}
}
