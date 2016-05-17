package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.IUserDAO;
import com.cvilla.medievalia.domain.User;

public class LoginManager implements ILoginManager {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IUserDAO userDAO;
	
	private User currentUser;
	
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
	public User getUser(String name) {
		// TODO Auto-generated method stub
		User u = userDAO.getUserByName(name);
		return u;
	}

	public boolean login(String name, String pass) {
		User user = userDAO.login(name, pass);
		if(user == null){
			return false;
		}
		else{
			setCurrentUser(user);
			return true;
		}
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}


}
