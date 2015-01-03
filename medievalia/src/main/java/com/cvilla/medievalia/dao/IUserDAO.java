package com.cvilla.medievalia.dao;
import java.util.List;

import com.cvilla.medievalia.domain.User;

public interface IUserDAO {
	
	public void nuevo(User u);
	
	public List<User> list();

}
