package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.Author;

public class AuthorMapper implements RowMapper<Author> {

	public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
		Author g = new Author();
		g.setIdAuthor(rs.getInt("idAutor"));
		g.setIdGroup(rs.getInt("idGroup"));
		g.setNombre(rs.getString("nombre"));
		g.setIdCreator(rs.getInt("creador"));
		g.setValidado(rs.getInt("validado"));
		g.setNameCreator(rs.getString("nameCreator"));
		return g;
	}
}
