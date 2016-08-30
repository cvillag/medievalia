package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IGroupDAO;
import com.cvilla.medievalia.dao.mappers.GroupMapper;
import com.cvilla.medievalia.dao.mappers.StudentsMapper;
import com.cvilla.medievalia.dao.mappers.TeacherMapper;
import com.cvilla.medievalia.dao.mappers.UserMapper;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Students;
import com.cvilla.medievalia.domain.Teachers;
import com.cvilla.medievalia.domain.User;

public class GroupDAO implements IGroupDAO {
	
	private static final String GET_GROUP_LIST = "SELECT * FROM `groups`";
	private static final String ADD_GROUP = "insert into groups (director,name,description) values (?,?,?)";
	private static final String REMOVE_GROUP = "delete from groups where idGroup = ?";
	private static final String GET_OWN_GROUP_LIST = "select * from groups where director = ? or idGroup in (select idGroup from teachers where idTeacher = ?) group by director";
	private static final String GET_GROUP_LIST_BY_DIR = "SELECT `idGroup`, `name`, `director`, `description` FROM `groups` WHERE director = ?";
	private static final String GET_GROUP_LIST_BY_TEACHER = "select sel1.name, sel1.idGroup, sel1.idTeacher, sel1.idDirector, sel1.description, users.user_long_name  as directorName from (select groups.name as name, teachers.idGroup as idGroup, teachers.idTeacher as idTeacher, groups.director as idDirector, groups.description from groups left join teachers on teachers.idGroup = groups.idGroup where idTeacher = ?) as sel1 left join users on sel1.idDirector = users.user_id";
	private static final String GET_GROUP_LIST_BY_STUDENT = "select idGroup, idDirector, groupName, idStudent, directorName, description, users.user_name as studentName from (select idGroup, director as idDirector, name as groupName, idStudent, user_name as directorName, description from (SELECT groups.idGroup as idGroup, director, description, name, idStudent from groups left join students on groups.idGroup = students.idGroup where idStudent = ?) as s1 left join users on s1.director = users.user_id) as sel2 left join users on users.user_id = sel2.idStudent";
	private static final String GET_GROUP_BY_ID = "select idGroup, director, name, description from groups where idGroup = ? ";
	private static final String ADD_STUDENT = "insert into students(idGroup,idStudent) values (?,?)";
	private static final String ADD_TEACHER = "insert into teachers (idGroup, idTeacher) values (?,?)";
	private static final String GET_TEACHER = "select users.user_long_name as name, sel3.idGroup, sel3.idTeacher, sel3.idDirector, sel3.directorName from (select sel2.idGroup, sel2.idTeacher, sel2.director as idDirector, users.user_long_name as directorName from (select sel1.idGroup, sel1.idTeacher, groups.director from (select idGroup, idTeacher from teachers where idGroup = ? and idTeacher = ?) as sel1 left join groups on sel1.idGroup = groups.idGroup) as sel2 left join users on users.user_id = sel2.director) as sel3 left join users on users.user_id = sel3.idTeacher";
	private static final String GET_STUDENT= "select sel3.idDirector, sel3.idGroup, sel3.groupName, sel3.idStudent, sel3.studentName, users.user_long_name as directorName from (select sel2.idDirector, sel2.idGroup, sel2.groupName, sel2.idStudent, users.user_long_name as studentName from (select groups.director as idDirector, groups.idGroup, groups.name as groupName, sel1.idStudent from (select idGroup, idStudent from students where idStudent = ? and idGroup = ?) as sel1 left join groups on groups.idGroup = sel1.idGroup) as sel2 left join users on users.user_id = sel2.idStudent) as sel3 left join users on sel3.idDirector = users.user_id";
	private static final String GET_USER_NOT_IN_GROUP = "select user_id, user_name, user_long_name, user_role, ' ' as user_pass from users where user_id not in (select idStudent from students where idGroup = ?) and user_id not in (select idTeacher from teachers where idGroup = ?) and user_id != 0  and (user_role = 2 or user_role = 3) and user_id != ?";
	private static final String GET_USER_NOT_IN_GROUP_FILTER = "select user_id, user_name, user_long_name, user_role, ' ' as user_pass from users where user_id not in (select idStudent from students where idGroup = ?) and user_id not in (select idTeacher from teachers where idGroup = ?) and user name like '%?%'  and user_id != 0  and (user_role = 2 or user_role = 3) and user_id != ?";
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
	
	public List<Group> getGroupList() {
		try{
			List<Group> g = getJdbcTemplate().query(GET_GROUP_LIST, new GroupMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public String addGroup(Group g) {
		try{
			int r = jdbcTemplate.update(ADD_GROUP, new Object[]{g.getDirector(),g.getName(),g.getDescription()});
			if(r == 1){
				return "creado";
			}
			else{
				return "nok";
			}
		}
		catch(DataIntegrityViolationException e){
			return "nameRepeat";
		}
		catch(Exception e){
			return "nok";
		}
	}

	public boolean removeGroup(Group g) {
		try{
			return jdbcTemplate.update(REMOVE_GROUP,new Object[]{g.getIdGrupo()}) == 1;
		}
		catch(Exception e){
			return false;
		}
	}

	public List<Group> getOwnGroups(User u) {
		try{
			List<Group> g = getJdbcTemplate().query(GET_OWN_GROUP_LIST, new Object[]{u.getId(),u.getId()}, new GroupMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public List<Group> getGroupListByDir(User dir) {
		try{
			List<Group> g = getJdbcTemplate().query(GET_GROUP_LIST_BY_DIR, new Object[]{dir.getId()}, new GroupMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public List<Teachers> getGroupListByTeacher(User teacher) {
		try{
			List<Teachers> g = getJdbcTemplate().query(GET_GROUP_LIST_BY_TEACHER, new Object[]{teacher.getId()}, new TeacherMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public List<Students> getGroupListByStudent(User student) {
		try{
			List<Students> g = getJdbcTemplate().query(GET_GROUP_LIST_BY_STUDENT, new Object[]{student.getId()}, new StudentsMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}

	public Group getGroup(int idGroup) {
		try{
			Group g = getJdbcTemplate().queryForObject(GET_GROUP_BY_ID, new Object[]{idGroup}, new GroupMapper());
			return g;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public String addStudent(int idGroup, User user){
		try{
			int r = jdbcTemplate.update(ADD_STUDENT, new Object[]{idGroup,user.getId()});
			if(r == 1){
				return "añadido";
			}
			else{
				return "error";
			}
		}
		catch(DataIntegrityViolationException e){
			return "duplicado";
		}
		catch (Exception e){
			return "error";
		}
	}
	
	public String addTeacher(int idGroup, User user){
		try{
			int r = jdbcTemplate.update(ADD_TEACHER, new Object[]{idGroup,user.getId()});
			if(r == 1){
				return "añadido";
			}
			else{
				return "error";
			}
		}
		catch(DataIntegrityViolationException e){
			return "duplicado";
		}
		catch (Exception e){
			return "error";
		}
	}
	
	public boolean isTeacher(int idGroup, User user){
		try{
			Teachers s = jdbcTemplate.queryForObject(GET_TEACHER, new Object[]{idGroup,user.getId()},new TeacherMapper());
			if(s == null){
				return false;
			}
			else{
				return true;
			}
		}
		catch(EmptyResultDataAccessException e){
			return false;
		}
	}
	
	public Students getStudent(int idGroup, User user){
		try{
			Students s = jdbcTemplate.queryForObject(GET_STUDENT, new Object[]{user.getId(),idGroup},new StudentsMapper());
			return s;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public Teachers getTeacher(int idGroup, User user){
		try{
			Teachers s = jdbcTemplate.queryForObject(GET_TEACHER, new Object[]{idGroup,user.getId()},new TeacherMapper());
			return s;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public boolean isStudent(int idGroup, User user){
		try{
			Students s = jdbcTemplate.queryForObject(GET_STUDENT, new Object[]{user.getId(),idGroup},new StudentsMapper());
			if(s == null){
				return false;
			}
			else{
				return true;
			}
		}
		catch(EmptyResultDataAccessException e){
			return false;
		}
	}
	
	public List<User> getPossibleUsersListToGroup(Group group, String filter){
		List<User> list;
		try{
			if(filter == null || filter.length() < 1){
				list = jdbcTemplate.query(GET_USER_NOT_IN_GROUP, new Object[]{group.getIdGrupo(),group.getIdGrupo(),group.getDirector()},new UserMapper());
			}
			else{
				list = jdbcTemplate.query(GET_USER_NOT_IN_GROUP_FILTER, new Object[]{group.getIdGrupo(),group.getIdGrupo(),filter,group.getDirector()},new UserMapper());
			}
			return list;
		}
		catch(Exception e){
			return null;
		}
	}
}
