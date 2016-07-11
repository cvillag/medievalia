package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.IUserDAO;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.utils.Constants;

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

	public String createUser(String name, String longname, String pass,
			String role) {
		// FIXME: Comprobar que coinciden las contraseñas
		if(name.length()>0){
			if(longname.length() > 0){
				if( pass.length() > 0 ){
					//TODO Añadir estos mensajes a messages_es.properties
					if(role.length() > 0 ){
						return userDAO.nuevo(name, longname, pass, role);
					}else{
						return Constants.M_NO_ROLE;
					}
				}else{
					return Constants.M_NO_PASS;
				}
			}else{
				return Constants.M_NO_LNAME;
			}
		}else{
			return Constants.M_NO_NAME;
		}
	}

	public String deleteUser(int id, User current) {
		if(id != current.getId()){
			if(userDAO.deleteUser(id)){
				return "borrado";
			}
			else{
				return "noBorrado";
			}
		}
		else{
			return "noAuto";
		}
	}

	public User getUser(int id) {
		User u = userDAO.getUserById(id);
		return u;
	}

	public String modifyUser(String name, String lname, 
			String pass, String pass2, String role, User user) {
		if(pass.equals(pass2)){
			if(pass.length()>1){
				return userDAO.modifyUser(name, lname, pass, pass2, role, user.getId());
			}
			else{
				return userDAO.modifyUser(name, lname, role, user.getId());
			}
		}
		else{
			return "p1-3.1.error.mismatchPass";
		}
	}


}
