package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IChargeDAO;
import com.cvilla.medievalia.dao.mappers.ChargeMapper;
import com.cvilla.medievalia.dao.mappers.GroupMapper;
import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Group;

public class ChargeDAO implements IChargeDAO {
	
	private static final String GET_CHARGE_LIST = "SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado` FROM `cargo`";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Charge> getChargeList() {
		try{
			List<Charge> g = getJdbcTemplate().query(GET_CHARGE_LIST, new ChargeMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

}
