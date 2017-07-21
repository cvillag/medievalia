package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IChargeDAO;
import com.cvilla.medievalia.dao.mappers.ChargeMapper;
import com.cvilla.medievalia.dao.mappers.UserMapper;
import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.utils.Constants;

public class ChargeDAO implements IChargeDAO {
	
	private static final String GET_CHARGE_LIST = "SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado`, `nameCreator` FROM (SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado` FROM `cargo` where validado = ? ) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String ADD_CHARGE = "INSERT INTO `cargo`( `nombre`, `idGroup`, `creador`, `validado`) VALUES (?,?,?,?)";
	private static final String GET_CHARGE_BY_NAME = "SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado`, `nameCreator` FROM ( select `idCargo`, `idGroup`, `nombre`, `creador`, `validado` FROM `cargo` where nombre = ?) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String GET_CHARGE = "SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado`, `nameCreator` FROM (select `idCargo`, `idGroup`, `nombre`, `creador`, `validado` FROM `cargo` where idCargo = ? ) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String UPDATE_CHARGE_NAME = "UPDATE `cargo` SET `nombre`= ? WHERE `idCargo` = ?";
	private static final String DELETE_CHARGE = "DELETE FROM `cargo` WHERE `idCargo` = ?";
	private static final String GET_STUDENT_CHARGE_LIST = "SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado`, `nameCreator` FROM (SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado` FROM `cargo` where creador = ? ) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String GET_TEACHER_CHARGE_LIST = "SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado`, `nameCreator` FROM (SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado` FROM `cargo` where idGroup = ? and validado = ?) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id order by creador";
	private static final String VALIDATE_CHARGE_NAME = "UPDATE `cargo` SET `validado`= ? WHERE `idCargo` = ?";
	private static final String GET_STUDENTS_TO_VALIDATE = "select user_id, user_long_name, user_name, user_pass, user_role from users where user_id in (SELECT  distinct(`creador`) FROM `cargo` WHERE idGroup = ? and validado = ?)";
	private static final String GET_NUM_CHARGES_TO_VALIDATE = "SELECT  count(`creador`) FROM `cargo` WHERE idGroup = ? and validado = ?";
	private static final String GET_NUM_CHARGES_TO_VALIDATE_BY_CREATOR = "SELECT  count(`creador`) FROM `cargo` WHERE idGroup = ? and validado = ? and `creador` = ?";
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
			List<Charge> g = getJdbcTemplate().query(GET_CHARGE_LIST, new Object[]{Constants.OBJETO_VALIDADO}, new ChargeMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public String createChargeNoVal(String nombre, Group groupA, User user) {
		try{
			int num = jdbcTemplate.update(ADD_CHARGE,new Object[]{nombre,groupA.getIdGrupo(),user.getId(),Constants.OBJETO_NO_VALIDADO});
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
			int num = jdbcTemplate.update(ADD_CHARGE,new Object[]{nombre,groupA.getIdGrupo(),user.getId(),Constants.OBJETO_VALIDADO});
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

	public String deleteCharge(int idCargo) {
		try{
			int num = jdbcTemplate.update(DELETE_CHARGE,new Object[]{idCargo});
			if(num == 1){
				return "borrado";
			}
			else{
				return "noBorrado";
			}
		}
		catch(Exception e){
			return "noBorrado";
		}
	}

	public List<Charge> getStudentChargeList(User user) {
		try{
			List<Charge> g = getJdbcTemplate().query(GET_STUDENT_CHARGE_LIST, new Object[]{user.getId()}, new ChargeMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public List<Charge> getTeacherChargeList(Group groupA) {
		try{
			List<Charge> g = getJdbcTemplate().query(GET_TEACHER_CHARGE_LIST, new Object[]{groupA.getIdGrupo(),Constants.OBJETO_NO_VALIDADO}, new ChargeMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public String validateCharge(int idCharge) {
		try{
			int num = jdbcTemplate.update(VALIDATE_CHARGE_NAME,new Object[]{Constants.OBJETO_VALIDADO,idCharge});
			if(num == 1){
				return "validado";
			}
			else{
				return "noValidado";
			}
		}
		catch(Exception e){
			return "noValidado";
		}
	}
	
	public List<User> getUsersToValidateByGroup(int idGroup){
		try{
			List<User> l = getJdbcTemplate().query(GET_STUDENTS_TO_VALIDATE, new Object[]{idGroup,Constants.OBJETO_NO_VALIDADO},new UserMapper());
			return l;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public int getChargesToValidateByGroup(int idGroup){
		try{
			return getJdbcTemplate().queryForInt(GET_NUM_CHARGES_TO_VALIDATE, new Object[]{idGroup,Constants.OBJETO_NO_VALIDADO});
		}
		catch(Exception e){
			return -1;
		}
	}
	
	public int getChargesToValidateByGroupAndCreator(User user, int idGroup){
		try{
			return getJdbcTemplate().queryForInt(GET_NUM_CHARGES_TO_VALIDATE_BY_CREATOR, new Object[]{idGroup,Constants.OBJETO_NO_VALIDADO,user.getId()});
		}
		catch(Exception e){
			return -1;
		}
	}

}
