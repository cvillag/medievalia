package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IAuthorDAO;
import com.cvilla.medievalia.dao.mappers.AuthorMapper;
import com.cvilla.medievalia.dao.mappers.UserMapper;
import com.cvilla.medievalia.domain.Author;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.utils.Constants;

public class AuthorDAO implements IAuthorDAO {
	
	private static final String GET_AUTHOR_LIST = "SELECT `idAutor`, `idGroup`, `nombre`, `creador`, `validado`, `nameCreator` FROM (SELECT `idAutor`, `idGroup`, `nombre`, `creador`, `validado` FROM `autor` where validado = ? ) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String ADD_AUTHOR = "INSERT INTO `autor`( `nombre`, `idGroup`, `creador`, `validado`) VALUES (?,?,?,?)";
	private static final String GET_AUTHOR_BY_NAME = "SELECT `idAutor`, `idGroup`, `nombre`, `creador`, `validado`, `nameCreator` FROM ( select `idAutor`, `idGroup`, `nombre`, `creador`, `validado` FROM `autor` where nombre = ?) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String GET_AUTHOR = "SELECT `idAutor`, `idGroup`, `nombre`, `creador`, `validado`, `nameCreator` FROM (select `idAutor`, `idGroup`, `nombre`, `creador`, `validado` FROM `autor` where idAutor = ? ) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String UPDATE_AUTHOR_NAME = "UPDATE `autor` SET `nombre`= ? WHERE `idAutor` = ?";
	private static final String DELETE_AUTHOR = "DELETE FROM `autor` WHERE `idAutor` = ?";
	private static final String GET_STUDENT_AUTHOR_LIST = "SELECT `idAutor`, `idGroup`, `nombre`, `creador`, `validado`, `nameCreator` FROM (SELECT `idAutor`, `idGroup`, `nombre`, `creador`, `validado` FROM `autor` where creador = ? ) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id";
	private static final String GET_TEACHER_AUTHOR_LIST = "SELECT `idAutor`, `idGroup`, `nombre`, `creador`, `validado`, `nameCreator` FROM (SELECT `idAutor`, `idGroup`, `nombre`, `creador`, `validado` FROM `autor` where idGroup = ? and validado = ?) as sel1 left join (select user_name as nameCreator,  user_id from users) as sel2 on sel1.creador = sel2.user_id order by creador";
	private static final String VALIDATE_AUTHOR_NAME = "UPDATE `autor` SET `validado`= ? WHERE `idAutor` = ?";
	private static final String GET_STUDENTS_TO_VALIDATE = "select user_id, user_long_name, user_name, user_pass, user_role from users where user_id in (SELECT  distinct(`creador`) FROM `autor` WHERE idGroup = ? and validado = ?)";
	private static final String GET_NUM_AUTHORS_TO_VALIDATE = "SELECT  count(`creador`) FROM `autor` WHERE idGroup = ? and validado = ?";
	private static final String GET_NUM_AUTHORS_TO_VALIDATE_BY_CREATOR = "SELECT  count(`creador`) FROM `autor` WHERE idGroup = ? and validado = ? and `creador` = ?";
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

	public List<Author> getAuthorList() {
		try{
			List<Author> g = getJdbcTemplate().query(GET_AUTHOR_LIST, new Object[]{Constants.OBJETO_VALIDADO}, new AuthorMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public String createAuthorNoVal(String nombre, Group groupA, User user) {
		try{
			int num = jdbcTemplate.update(ADD_AUTHOR,new Object[]{nombre,groupA.getIdGrupo(),user.getId(),Constants.OBJETO_NO_VALIDADO});
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
	
	public String createAuthorVal(String nombre, Group groupA, User user) {
		try{
			int num = jdbcTemplate.update(ADD_AUTHOR,new Object[]{nombre,groupA.getIdGrupo(),user.getId(),Constants.OBJETO_VALIDADO});
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

	public Author getAuthorByName(String nombre) {
		try{
			return getJdbcTemplate().queryForObject(GET_AUTHOR_BY_NAME,new Object[]{nombre}, new AuthorMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public Author getAuthor(int id) {
		try{
			return getJdbcTemplate().queryForObject(GET_AUTHOR,new Object[]{id}, new AuthorMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String renameAuthor(int idAutor, String nombre) {
		try{
			int num = jdbcTemplate.update(UPDATE_AUTHOR_NAME,new Object[]{nombre,idAutor});
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

	public String deleteAuthor(int idAutor) {
		try{
			int num = jdbcTemplate.update(DELETE_AUTHOR,new Object[]{idAutor});
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

	public List<Author> getStudentAuthorList(User user) {
		try{
			List<Author> g = getJdbcTemplate().query(GET_STUDENT_AUTHOR_LIST, new Object[]{user.getId()}, new AuthorMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public List<Author> getTeacherAuthorList(Group groupA) {
		try{
			List<Author> g = getJdbcTemplate().query(GET_TEACHER_AUTHOR_LIST, new Object[]{groupA.getIdGrupo(),Constants.OBJETO_NO_VALIDADO}, new AuthorMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public String validateAuthor(int idAuthor) {
		try{
			int num = jdbcTemplate.update(VALIDATE_AUTHOR_NAME,new Object[]{Constants.OBJETO_VALIDADO,idAuthor});
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
	
	public int getAuthorsToValidateByGroup(int idGroup){
		try{
			return getJdbcTemplate().queryForInt(GET_NUM_AUTHORS_TO_VALIDATE, new Object[]{idGroup,Constants.OBJETO_NO_VALIDADO});
		}
		catch(Exception e){
			return -1;
		}
	}
	
	public int getAuthorsToValidateByGroupAndCreator(User user, int idGroup){
		try{
			return getJdbcTemplate().queryForInt(GET_NUM_AUTHORS_TO_VALIDATE_BY_CREATOR, new Object[]{idGroup,Constants.OBJETO_NO_VALIDADO,user.getId()});
		}
		catch(Exception e){
			return -1;
		}
	}

}
