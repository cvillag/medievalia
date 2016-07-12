package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.IGroupDAO;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
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

}
