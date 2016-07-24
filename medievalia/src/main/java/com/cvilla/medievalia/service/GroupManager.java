package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IGroupDAO;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Students;
import com.cvilla.medievalia.domain.Teachers;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;

public class GroupManager implements IGroupManager {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IGroupDAO groupDAO;
	
	@Autowired
	public IGroupDAO getGroupDAO() {
		return groupDAO;
	}
	
	@Autowired
	public void setGroupDAO(IGroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	
	@Autowired
	public ILogManager logManager;
	
	@Autowired
	public ILogManager getLogManager() {
		return logManager;
	}

	@Autowired
	public void setLogManager(ILogManager logManager) {
		this.logManager = logManager;
	}
	
	public List<Group> getList(User u) {
		List<Group> l;
		if(u.getUser_role() == Constants.ROLE_ADMIN){
			l = groupDAO.getGroupList();
			if(l != null){
				logManager.log(u.getId(), Constants.P_GROUP_LIST, "Listado de todos los grupos", Constants.P_OK);
			}
			else{
				logManager.log(u.getId(), Constants.P_GROUP_LIST, "Listado de todos los grupos, sin éxito", Constants.P_NOK);
			}
		}
		else{
			l = groupDAO.getOwnGroups(u);
			if(l != null){
				logManager.log(u.getId(), Constants.P_GROUP_OWN, "Listado de los grupos propios del usuario", Constants.P_OK);
			}
			else{
				logManager.log(u.getId(), Constants.P_GROUP_OWN, "Listado de los grupos propios del usuario", Constants.P_NOK);
			}
		}
		return l;
	}

	public String addGroup(int director, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeGroup(int idGroup) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Group> getListByDirector(User user, User dir) {
		List<Group> l = null;
		if(user.getUser_role() == Constants.ROLE_ADMIN){
			l = groupDAO.getGroupListByDir(dir);
			logManager.log(user.getId(), Constants.P_DETAIL_DIRECTOR_GROUPS_OTHER,"Visualización de grupos del usuario con id " + dir.getId() + " como director", Constants.P_OK);
		}
		else if(user.getUser_role() == Constants.ROLE_PROFESOR){
			l = groupDAO.getGroupListByDir(user);
			logManager.log(user.getId(), Constants.P_DETAIL_DIRECTOR_GROUPS_OWN,"Visualización de grupos del usuario propio id " + dir.getId() + " como director", Constants.P_OK);
		}
		return l;
	}

	public List<Teachers> getListByTeacher(User user, User teacher) {
		List<Teachers> l = null;
		if(user.getUser_role() == Constants.ROLE_ADMIN){
			l = groupDAO.getGroupListByTeacher(teacher);
			logManager.log(user.getId(), Constants.P_DETAIL_TEACHER_GROUPS_OTHER,"Visualización de grupos del usuario con id " + teacher.getId() + " como profesor", Constants.P_OK);
		}
		else if(user.getUser_role() == Constants.ROLE_PROFESOR){
			l = groupDAO.getGroupListByTeacher(teacher);
			logManager.log(user.getId(), Constants.P_DETAIL_TEACHER_GROUPS_OWN,"Visualización de grupos del usuario propio id " + teacher.getId() + " como profesor", Constants.P_OK);
		}
		return l;
	}

	public List<Students> getListByStudent(User user, User user2) {
		List<Students> l = null;
		if(user.getUser_role() == Constants.ROLE_ADMIN || user.getUser_role() == Constants.ROLE_PROFESOR){
			l = groupDAO.getGroupListByStudent(user2);
			logManager.log(user.getId(), Constants.P_DETAIL_STUDENT_GROUPS_OTHER,"Visualización de grupos del usuario con id " + user2.getId() + " como estudiante", Constants.P_OK);
		}
		else if(user.getUser_role() == Constants.ROLE_ALUMNO){
			l = groupDAO.getGroupListByStudent(user);
			logManager.log(user.getId(), Constants.P_DETAIL_STUDENT_GROUPS_OWN,"Visualización de grupos del usuario propio id " + user.getId() + " como estudiante", Constants.P_OK);
		}
		return l;
	}

}
