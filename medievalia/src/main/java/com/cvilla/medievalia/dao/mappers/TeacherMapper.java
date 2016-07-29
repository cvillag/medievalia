package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.cvilla.medievalia.domain.Teachers;
import org.springframework.jdbc.core.RowMapper;

public class TeacherMapper implements RowMapper<Teachers> {

	public Teachers mapRow(ResultSet rs, int rowNum) throws SQLException {
		Teachers t = new Teachers();
		t.setIdGroup(rs.getInt("idGroup"));
		t.setIdTeacher(rs.getInt("idTeacher"));
		t.setName(rs.getString("name"));
		t.setDirectorName(rs.getString("directorName"));
		t.setIdDirector(rs.getInt("idDirector"));
		return t;
	}

}
