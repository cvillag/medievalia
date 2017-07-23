package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IPlaceDAO;
import com.cvilla.medievalia.dao.mappers.PlaceMapper;
import com.cvilla.medievalia.dao.mappers.UserMapper;
import com.cvilla.medievalia.domain.Place;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.utils.Constants;

public class PlaceDAO implements IPlaceDAO {
	
	private static final String GET_PLACE_LIST = "SELECT `idLugar`, `idGrupo`, `nombre`, `creador`, `validado`, `nameCreator` FROM (SELECT `idLugar`, `idGrupo`, `nombre`, `creador`, `validado` FROM `lugar` where validado = ? ) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String ADD_PLACE = "INSERT INTO `lugar`( `nombre`, `idGrupo`, `creador`, `validado`) VALUES (?,?,?,?)";
	private static final String GET_PLACE_BY_NAME = "SELECT `idLugar`, `idGrupo`, `nombre`, `creador`, `validado`, `nameCreator` FROM ( select `idLugar`, `idGrupo`, `nombre`, `creador`, `validado` FROM `lugar` where nombre = ?) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String GET_PLACE = "SELECT `idLugar`, `idGrupo`, `nombre`, `creador`, `validado`, `nameCreator` FROM (select `idLugar`, `idGrupo`, `nombre`, `creador`, `validado` FROM `lugar` where idLugar = ? ) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String UPDATE_PLACE_NAME = "UPDATE `lugar` SET `nombre`= ? WHERE `idLugar` = ?";
	private static final String DELETE_PLACE = "DELETE FROM `lugar` WHERE `idLugar` = ?";
	private static final String GET_STUDENT_PLACE_LIST = "SELECT `idLugar`, `idGrupo`, `nombre`, `creador`, `validado`, `nameCreator` FROM (SELECT `idLugar`, `idGrupo`, `nombre`, `creador`, `validado` FROM `lugar` where creador = ? ) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String GET_TEACHER_PLACE_LIST = "SELECT `idLugar`, `idGrupo`, `nombre`, `creador`, `validado`, `nameCreator` FROM (SELECT `idLugar`, `idGrupo`, `nombre`, `creador`, `validado` FROM `lugar` where idGrupo = ? and validado = ?) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id order by creador";
	private static final String VALIDATE_PLACE_NAME = "UPDATE `lugar` SET `validado`= ? WHERE `idLugar` = ?";
	private static final String GET_STUDENTS_TO_VALIDATE = "select user_id, user_long_name, user_name, user_pass, user_role from users where user_id in (SELECT  distinct(`creador`) FROM `lugar` WHERE idGrupo = ? and validado = ?)";
	private static final String GET_NUM_PLACES_TO_VALIDATE = "SELECT  count(`creador`) FROM `lugar` WHERE idGrupo = ? and validado = ?";
	private static final String GET_NUM_PLACES_TO_VALIDATE_BY_CREATOR = "SELECT  count(`creador`) FROM `lugar` WHERE idGrupo = ? and validado = ? and `creador` = ?";
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

	public List<Place> getPlaceList() {
		try{
			List<Place> g = getJdbcTemplate().query(GET_PLACE_LIST, new Object[]{Constants.OBJETO_VALIDADO}, new PlaceMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public String createPlaceNoVal(String nombre, Group groupA, User user) {
		try{
			int num = jdbcTemplate.update(ADD_PLACE,new Object[]{nombre,groupA.getIdGrupo(),user.getId(),Constants.OBJETO_NO_VALIDADO});
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
	
	public String createPlaceVal(String nombre, Group groupA, User user) {
		try{
			int num = jdbcTemplate.update(ADD_PLACE,new Object[]{nombre,groupA.getIdGrupo(),user.getId(),Constants.OBJETO_VALIDADO});
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

	public Place getPlaceByName(String nombre) {
		try{
			return getJdbcTemplate().queryForObject(GET_PLACE_BY_NAME,new Object[]{nombre}, new PlaceMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public Place getPlace(int id) {
		try{
			return getJdbcTemplate().queryForObject(GET_PLACE,new Object[]{id}, new PlaceMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String renamePlace(int idLugar, String nombre) {
		try{
			int num = jdbcTemplate.update(UPDATE_PLACE_NAME,new Object[]{nombre,idLugar});
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

	public String deletePlace(int idLugar) {
		try{
			int num = jdbcTemplate.update(DELETE_PLACE,new Object[]{idLugar});
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

	public List<Place> getStudentPlaceList(User user) {
		try{
			List<Place> g = getJdbcTemplate().query(GET_STUDENT_PLACE_LIST, new Object[]{user.getId()}, new PlaceMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public List<Place> getTeacherPlaceList(Group groupA) {
		try{
			List<Place> g = getJdbcTemplate().query(GET_TEACHER_PLACE_LIST, new Object[]{groupA.getIdGrupo(),Constants.OBJETO_NO_VALIDADO}, new PlaceMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public String validatePlace(int idPlace) {
		try{
			int num = jdbcTemplate.update(VALIDATE_PLACE_NAME,new Object[]{Constants.OBJETO_VALIDADO,idPlace});
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
	
	public List<User> getUsersToValidateByGroup(int idGrupo){
		try{
			List<User> l = getJdbcTemplate().query(GET_STUDENTS_TO_VALIDATE, new Object[]{idGrupo,Constants.OBJETO_NO_VALIDADO},new UserMapper());
			return l;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public int getPlacesToValidateByGroup(int idGrupo){
		try{
			return getJdbcTemplate().queryForInt(GET_NUM_PLACES_TO_VALIDATE, new Object[]{idGrupo,Constants.OBJETO_NO_VALIDADO});
		}
		catch(Exception e){
			return -1;
		}
	}
	
	public int getPlacesToValidateByGroupAndCreator(User user, int idGrupo){
		try{
			return getJdbcTemplate().queryForInt(GET_NUM_PLACES_TO_VALIDATE_BY_CREATOR, new Object[]{idGrupo,Constants.OBJETO_NO_VALIDADO,user.getId()});
		}
		catch(Exception e){
			return -1;
		}
	}

}
