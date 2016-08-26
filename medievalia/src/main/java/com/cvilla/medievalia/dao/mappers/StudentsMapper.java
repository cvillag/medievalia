package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.Students;

public class StudentsMapper implements RowMapper<Students> {

	public Students mapRow(ResultSet rs, int rowNum) throws SQLException {
		Students s = new Students();
		s.setDirectorName(rs.getString("directorName"));
		s.setGroupName(rs.getString("groupName"));
		s.setIdDirector(rs.getInt("idDirector"));
		s.setIdGroup(rs.getInt("idGroup"));
		s.setIdStudent(rs.getInt("idStudent"));
		s.setStudentName(rs.getString("studentName"));
		return s;
	}

}
