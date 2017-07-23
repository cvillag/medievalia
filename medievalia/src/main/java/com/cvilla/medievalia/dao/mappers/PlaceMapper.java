package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.Place;

public class PlaceMapper implements RowMapper<Place> {

	public Place mapRow(ResultSet rs, int rowNum) throws SQLException {
		Place g = new Place();
		g.setIdPlace(rs.getInt("idLugar"));
		g.setIdGroup(rs.getInt("idGrupo"));
		g.setNombre(rs.getString("nombre"));
		g.setIdCreator(rs.getInt("creador"));
		g.setValidado(rs.getInt("validado"));
		g.setNameCreator(rs.getString("nameCreator"));
		return g;
	}

}
