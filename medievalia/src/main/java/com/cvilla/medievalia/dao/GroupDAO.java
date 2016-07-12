package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.mappers.GroupMapper;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;

public class GroupDAO implements IGroupDAO {
	
	private static final String GET_GROUP_LIST = "SELECT * FROM `groups`";
	private static final String ADD_GROUP = "insert into groups (teacher,name) values (?,?)";
	private static final String REMOVE_GROUP = "delete from groups where idGroup = ?";
	private static final String GET_OWN_GROUP_LIST = "select * from groups where director = ? or idGroup in (select idGroup from teachers where idTeacher = ?) group by director";

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
			//FIXME: Error al recoger el resultset. En vez de dos objetos group, dos null.
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

}
