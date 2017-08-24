package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.Personage;

public class CharacterMapper implements RowMapper<Personage> {

	public Personage mapRow(ResultSet rs, int rowNum) throws SQLException {
		Personage c =  new Personage();
		c.setIdPersonaje(rs.getInt("idPersonaje"));
		c.setIdGrupo(rs.getInt("idGrupo"));
		c.setCreador(rs.getInt("creador"));
		c.setNombreCreador(rs.getString("nombreCreador"));
		c.setNombre(rs.getString("nombre"));
		c.setOtros(rs.getString("otros"));
		c.setValidado(rs.getInt("validado"));
		c.setLugarFallecimiento(rs.getInt("lugarNacimiento"));
		c.setLugarNacimiento(rs.getInt("lugarFallecimiento"));
		c.setAnacimiento(rs.getInt("anacimiento"));
		c.setMnacimiento(rs.getInt("mnacimiento"));
		c.setDnacimiento(rs.getInt("dnacimiento"));
		c.setAfallecimiento(rs.getInt("afallecimiento"));
		c.setMfallecimiento(rs.getInt("mfallecimiento"));
		c.setDfallecimiento(rs.getInt("dfallecimiento"));
		return c;
	}

}
