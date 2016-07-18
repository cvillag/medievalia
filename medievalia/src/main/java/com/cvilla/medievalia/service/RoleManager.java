package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IRoleDAO;
import com.cvilla.medievalia.domain.Role;
import com.cvilla.medievalia.service.intf.IRoleManager;

public class RoleManager implements IRoleManager {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IRoleDAO roleDAO;

	public List<Role> getRoleList() {
		return roleDAO.getList();
	}

	
}
