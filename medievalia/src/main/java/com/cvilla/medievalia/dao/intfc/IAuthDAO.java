package com.cvilla.medievalia.dao.intfc;

public interface IAuthDAO {
	
	public boolean isAuthorized(int user, int action);

}
