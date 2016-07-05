package com.cvilla.medievalia.dao;
import java.util.List;

import com.cvilla.medievalia.domain.User;

public interface IUserDAO {
	
	public String nuevo(String name, String longname, String pass, String rol);
	
	public List<User> list();
	
	public User getUserByName(String name);
	
	public User login(String name, String pass);
	
	public boolean deleteUser(int id);

}
