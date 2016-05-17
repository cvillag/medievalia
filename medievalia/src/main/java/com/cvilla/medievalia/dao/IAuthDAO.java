package com.cvilla.medievalia.dao;

public interface IAuthDAO {
	
	public boolean isAuthorized(int user, int action);

}
