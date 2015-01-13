/*package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cvilla.medievalia.dao.IUserDAO;
import com.cvilla.medievalia.domain.User;

@Component
public class LoginManager implements ILoginManager {

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	@Autowired
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public List<User> listar() {
		List<User> userList = this.userDAO.list();
		return userList;
	}

}
*/