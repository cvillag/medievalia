package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Group;

public class ChargeMapper implements RowMapper<Charge> {

	public Charge mapRow(ResultSet rs, int rowNum) throws SQLException {
		Charge g = new Charge();
		g.setIdCharge(rs.getInt("idCargo"));
		g.setIdGroup(rs.getInt("idGroup"));
		g.setNombre(rs.getString("nombre"));
		g.setIdCreator(rs.getInt("creador"));
		g.setValidado(rs.getInt("validado"));
		return g;
	}

}
