package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.Group;

public class GroupMapper implements RowMapper<Group> {

	public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
		Group g = new Group();
		g.setIdGrupo(rs.getInt("idGroup"));
		g.setDirector(rs.getInt("director"));
		g.setName(rs.getString("name"));
		g.setDescription(rs.getString("description"));
		return g;
	}

}
