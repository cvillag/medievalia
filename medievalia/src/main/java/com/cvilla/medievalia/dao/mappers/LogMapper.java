package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.Log;

public class LogMapper implements RowMapper<Log> {
	public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
		Log l = new Log();
		l.setDescription(rs.getString("description"));
		l.setIdAction(rs.getInt("idAction"));
		l.setActionName(rs.getString("actionName"));
		l.setIdLog(rs.getInt("idLog"));
		l.setIdUser(rs.getInt("idUser"));
		l.setUserName(rs.getString("userName"));
		l.setUserLongName(rs.getString("userLongName"));
		l.setSuccess(rs.getInt("success"));
		l.setTime(rs.getTimestamp("time"));
		return l;
	}
}
