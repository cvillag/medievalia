package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IChargeDAO;
import com.cvilla.medievalia.dao.mappers.ChargeMapper;
import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.utils.Constants;

public class ChargeDAO implements IChargeDAO {
	
	private static final String GET_CHARGE_LIST = "SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado` FROM `cargo`";
	private static final String ADD_CHARGE = "INSERT INTO `cargo`( `nombre`, `idGroup`, `creador`, `validado`) VALUES (?,?,?,?)";
	private static final String GET_CHARGE_BY_NAME = "select `idCargo`, `idGroup`, `nombre`, `creador`, `validado` FROM `cargo` where nombre = ?";
	private static final String GET_CHARGE = "select `idCargo`, `idGroup`, `nombre`, `creador`, `validado` FROM `cargo` where idCargo = ?";
	private static final String UPDATE_CHARGE_NAME = "UPDATE `cargo` SET `nombre`= ? WHERE `idCargo` = ?";
	
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

	public Charge getChargeByName(String nombre) {
		try{
			return getJdbcTemplate().queryForObject(GET_CHARGE_BY_NAME,new Object[]{nombre}, new ChargeMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public Charge getCharge(int id) {
		try{
			return getJdbcTemplate().queryForObject(GET_CHARGE,new Object[]{id}, new ChargeMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String renameCharge(int idCargo, String nombre) {
		try{
			int num = jdbcTemplate.update(UPDATE_CHARGE_NAME,new Object[]{nombre,idCargo});
			if(num == 1){
				return "cambiado";
			}
			else{
				return "noCambiado";
			}
		}
		catch(Exception e){
			return "noCambiado";
		}
	}

}
