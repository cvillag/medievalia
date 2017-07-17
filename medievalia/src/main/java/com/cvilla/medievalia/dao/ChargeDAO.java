package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IChargeDAO;
import com.cvilla.medievalia.dao.mappers.ChargeMapper;
import com.cvilla.medievalia.dao.mappers.GroupMapper;
import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.utils.Constants;

public class ChargeDAO implements IChargeDAO {
	
	private static final String GET_CHARGE_LIST = "SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado` FROM `cargo`";
	private static final String ADD_CHARGE = "INSERT INTO `cargo`( `nombre`, `idGroup`, `creador`, `validado`) VALUES (?,?,?,?)";
	
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

	public String createChargeNoVal(String nombre, Group groupA, User user) {
		try{
			int num = jdbcTemplate.update(ADD_CHARGE,new Object[]{nombre,groupA.getIdGrupo(),user.getId(),Constants.CARGO_NO_VALIDADO});
			if(num == 1)
				return "creado";
			else{
				return "noCreado";
			}
		}
		catch(Exception e){
			return "noCreado";
		}
	}
	
	public String createChargeVal(String nombre, Group groupA, User user) {
		try{
			int num = jdbcTemplate.update(ADD_CHARGE,new Object[]{nombre,groupA.getIdGrupo(),user.getId(),Constants.CARGO_VALIDADO});
			if(num == 1)
				return "creado";
			else{
				return "noCreado";
			}
		}
		catch(Exception e){
			return "noCreado";
		}
	}

}
