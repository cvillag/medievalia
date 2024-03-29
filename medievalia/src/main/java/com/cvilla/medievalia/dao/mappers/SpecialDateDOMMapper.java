package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.utils.SpecialDate;

public class SpecialDateDOMMapper implements RowMapper<SpecialDate>{
	
	public SpecialDate mapRow(ResultSet rs, int rowNum) throws SQLException {
		SpecialDate s = new SpecialDate();
		s.setAnio(rs.getInt("anio"));
		s.setMes(rs.getInt("mes"));
		if(s.getMes() == 0){
			s.setMes(null);
		}
		s.setDia(rs.getInt("dia"));
		if(s.getDia() == 0){
			s.setDia(null);
		}
		return s;
	}
}
