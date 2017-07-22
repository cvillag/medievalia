package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IStudyDAO;
import com.cvilla.medievalia.dao.mappers.StudyMapper;
import com.cvilla.medievalia.dao.mappers.UserMapper;
import com.cvilla.medievalia.domain.Study;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.utils.Constants;

public class StudyDAO implements IStudyDAO {
	
	private static final String GET_STUDY_LIST = "SELECT `idEstudio`, `idGrupo`, `nombre`, `creador`, `validado`, `nameCreator` FROM (SELECT `idEstudio`, `idGrupo`, `nombre`, `creador`, `validado` FROM `estudio` where validado = ? ) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String ADD_STUDY = "INSERT INTO `estudio`( `nombre`, `idGrupo`, `creador`, `validado`) VALUES (?,?,?,?)";
	private static final String GET_STUDY_BY_NAME = "SELECT `idEstudio`, `idGrupo`, `nombre`, `creador`, `validado`, `nameCreator` FROM ( select `idEstudio`, `idGrupo`, `nombre`, `creador`, `validado` FROM `estudio` where nombre = ?) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String GET_STUDY = "SELECT `idEstudio`, `idGrupo`, `nombre`, `creador`, `validado`, `nameCreator` FROM (select `idEstudio`, `idGrupo`, `nombre`, `creador`, `validado` FROM `estudio` where idEstudio = ? ) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String UPDATE_STUDY_NAME = "UPDATE `estudio` SET `nombre`= ? WHERE `idEstudio` = ?";
	private static final String DELETE_STUDY = "DELETE FROM `estudio` WHERE `idEstudio` = ?";
	private static final String GET_STUDENT_STUDY_LIST = "SELECT `idEstudio`, `idGrupo`, `nombre`, `creador`, `validado`, `nameCreator` FROM (SELECT `idEstudio`, `idGrupo`, `nombre`, `creador`, `validado` FROM `estudio` where creador = ? ) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String GET_TEACHER_STUDY_LIST = "SELECT `idEstudio`, `idGrupo`, `nombre`, `creador`, `validado`, `nameCreator` FROM (SELECT `idEstudio`, `idGrupo`, `nombre`, `creador`, `validado` FROM `estudio` where idGrupo = ? and validado = ?) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id order by creador";
	private static final String VALIDATE_STUDY_NAME = "UPDATE `estudio` SET `validado`= ? WHERE `idEstudio` = ?";
	private static final String GET_STUDENTS_TO_VALIDATE = "select user_id, user_long_name, user_name, user_pass, user_role from users where user_id in (SELECT  distinct(`creador`) FROM `estudio` WHERE idGrupo = ? and validado = ?)";
	private static final String GET_NUM_STUDIES_TO_VALIDATE = "SELECT  count(`creador`) FROM `estudio` WHERE idGrupo = ? and validado = ?";
	private static final String GET_NUM_STUDIES_TO_VALIDATE_BY_CREATOR = "SELECT  count(`creador`) FROM `estudio` WHERE idGrupo = ? and validado = ? and `creador` = ?";
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

	public List<Study> getStudyList() {
		try{
			List<Study> g = getJdbcTemplate().query(GET_STUDY_LIST, new Object[]{Constants.OBJETO_VALIDADO}, new StudyMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public String createStudyNoVal(String nombre, Group groupA, User user) {
		try{
			int num = jdbcTemplate.update(ADD_STUDY,new Object[]{nombre,groupA.getIdGrupo(),user.getId(),Constants.OBJETO_NO_VALIDADO});
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
	
	public String createStudyVal(String nombre, Group groupA, User user) {
		try{
			int num = jdbcTemplate.update(ADD_STUDY,new Object[]{nombre,groupA.getIdGrupo(),user.getId(),Constants.OBJETO_VALIDADO});
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

	public Study getStudyByName(String nombre) {
		try{
			return getJdbcTemplate().queryForObject(GET_STUDY_BY_NAME,new Object[]{nombre}, new StudyMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public Study getStudy(int id) {
		try{
			return getJdbcTemplate().queryForObject(GET_STUDY,new Object[]{id}, new StudyMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String renameStudy(int idEstudio, String nombre) {
		try{
			int num = jdbcTemplate.update(UPDATE_STUDY_NAME,new Object[]{nombre,idEstudio});
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

	public String deleteStudy(int idEstudio) {
		try{
			int num = jdbcTemplate.update(DELETE_STUDY,new Object[]{idEstudio});
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

	public List<Study> getStudentStudyList(User user) {
		try{
			List<Study> g = getJdbcTemplate().query(GET_STUDENT_STUDY_LIST, new Object[]{user.getId()}, new StudyMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public List<Study> getTeacherStudyList(Group groupA) {
		try{
			List<Study> g = getJdbcTemplate().query(GET_TEACHER_STUDY_LIST, new Object[]{groupA.getIdGrupo(),Constants.OBJETO_NO_VALIDADO}, new StudyMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public String validateStudy(int idStudy) {
		try{
			int num = jdbcTemplate.update(VALIDATE_STUDY_NAME,new Object[]{Constants.OBJETO_VALIDADO,idStudy});
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
	
	public int getStudyToValidateByGroup(int idGrupo){
		try{
			return getJdbcTemplate().queryForInt(GET_NUM_STUDIES_TO_VALIDATE, new Object[]{idGrupo,Constants.OBJETO_NO_VALIDADO});
		}
		catch(Exception e){
			return -1;
		}
	}
	
	public int getStudyToValidateByGroupAndCreator(User user, int idGrupo){
		try{
			return getJdbcTemplate().queryForInt(GET_NUM_STUDIES_TO_VALIDATE_BY_CREATOR, new Object[]{idGrupo,Constants.OBJETO_NO_VALIDADO,user.getId()});
		}
		catch(Exception e){
			return -1;
		}
	}
}
