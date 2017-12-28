package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.InstanciaAtributoSencillo;
import com.cvilla.medievalia.utils.Constants;

public class AtributoSencilloDOMMapper implements RowMapper<InstanciaAtributoSencillo>{
	
	public InstanciaAtributoSencillo mapRow(ResultSet rs, int rowNum) throws SQLException {
		InstanciaAtributoSencillo a = new InstanciaAtributoSencillo();
		a.setIdAtributo(rs.getInt("idAtributo"));
		a.setNombreTipoAtributo(rs.getString("nombreAtributo"));
		a.setTipoAtributo(rs.getInt("tipo"));
		a.setSubtipo(rs.getInt("subtipo"));
		a.setValor(null);
		
		return a;
	}
}
