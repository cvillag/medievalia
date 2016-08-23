package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IUserDAO;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.ILoginManager;
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

	public String createUser(String name, String longname, String pass, String pass2, String role) {
		if(name.length()>0){
			if(longname.length() > 0){
				if( pass.length() > 0 ){
					if(role.length() > 0 ){
						if(pass.equals(pass2))
							return userDAO.nuevo(name, longname, pass, role);
						else{
							return Constants.M_PASS_NO_MATCH;
						}
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
				return userDAO.modifyUser(name, lname, (new Integer(role)).intValue(), user.getId());
			}
		}
		else{
			return "p1-3.1.error.mismatchPass";
		}
	}
	
	public String modifyUser(String name, String lname,	User user) {
		if(name.length() < Constants.MIN_USER_NAME || lname.length() < Constants.MIN_USER_NAME){
			return "noLength";
		}
		else{
			User u = userDAO.getUserByName(name);
			if(u == null || u.getUser_name().equals(user.getUser_name())){
				return userDAO.modifyUser(name, lname, user.getUser_role(), user.getId());
			}
			else{
				return "nameRepeat";
			}
		}
	}

	public String modifyUserPass(String user_name, String user_long_name,
			String pass1, String pass2, String pass3, int user_role, User user) {
		if(!pass2.equals(pass3)){
			return "mismatchPass2";
		}
		else{
			if(pass2.length() < Constants.MIN_PASS){
				return "passLength";
			}
			else{
				User user2 = userDAO.login(user.getUser_name(), pass1);
				if(user2 == null){
					return "mismatchPass1";
				}
				else{
					return userDAO.modifyUser(user2.getUser_name(), user2.getUser_long_name(), pass2, pass2, (new Integer(user2.getUser_role())).toString(), user2.getId());
				}
			}
		}
	}
}
