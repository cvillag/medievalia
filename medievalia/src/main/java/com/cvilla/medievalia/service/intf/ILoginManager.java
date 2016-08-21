package com.cvilla.medievalia.service.intf;
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.User;

@Component
public interface ILoginManager extends Serializable{
	
	public List<User> listar();
	public User getUser(String name);
	public User getUser(int id);
	public boolean login(String name, String pass);
	public User getCurrentUser();
	public void setCurrentUser(User user);
	public String createUser(String name, String longname, String pass,String pass2, String role);
	public String deleteUser(int id, User current);
	public String modifyUser(String name, String lname, String pass, String pass2, String role, User user);
	public String modifyUser(String name, String lname,User user);
	public String modifyUserPass(String user_name, String user_long_name,
			String pass1, String pass2, String pass3, int user_role, User user);
}
