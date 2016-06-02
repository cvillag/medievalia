package com.cvilla.medievalia.service;
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.User;

@Component
public interface ILoginManager extends Serializable{
	
	public List<User> listar();
	public User getUser(String name);
	public boolean login(String name, String pass);
	public User getCurrentUser();
	public void setCurrentUser(User user);
	public boolean createUser(String name, String longname, String pass, String role);
	
}
