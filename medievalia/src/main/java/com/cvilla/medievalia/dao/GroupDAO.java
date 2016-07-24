package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IGroupDAO;
import com.cvilla.medievalia.dao.mappers.GroupMapper;
import com.cvilla.medievalia.dao.mappers.StudentsMapper;
import com.cvilla.medievalia.dao.mappers.TeacherMapper;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Students;
import com.cvilla.medievalia.domain.Teachers;
import com.cvilla.medievalia.domain.User;

public class GroupDAO implements IGroupDAO {
	
	private static final String GET_GROUP_LIST = "SELECT * FROM `groups`";
	private static final String ADD_GROUP = "insert into groups (teacher,name) values (?,?)";
	private static final String REMOVE_GROUP = "delete from groups where idGroup = ?";
	private static final String GET_OWN_GROUP_LIST = "select * from groups where director = ? or idGroup in (select idGroup from teachers where idTeacher = ?) group by director";
	private static final String GET_GROUP_LIST_BY_DIR = "SELECT `idGroup`, `name`, `director` FROM `groups` WHERE director = ?";
	private static final String GET_GROUP_LIST_BY_TEACHER = "select groups.name as name, teachers.idGroup as idGroup, teachers.idTeacher as idTeacher from groups left join teachers on teachers.idGroup = groups.idGroup where idTeacher = ?";
	private static final String GET_GROUP_LIST_BY_STUDENT = "select idGroup, director as idDirector, name as groupName, idStudent, user_name as directorName from (SELECT groups.idGroup as idGroup, director, name, idStudent from groups left join students on groups.idGroup = students.idGroup where idStudent = ?   ) as s1 left join users on s1.director = users.user_id";
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
			int r = jdbcTemplate.update(ADD_GROUP, new Object[]{g.getDirector(),g.getName()});
			if(r == 1){
				return "ok";
			}
			else{
				return "nok";
			}
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
}
