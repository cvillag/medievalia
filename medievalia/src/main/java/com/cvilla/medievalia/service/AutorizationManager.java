package com.cvilla.medievalia.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IAuthDAO;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAutorizationManager;

public class AutorizationManager implements IAutorizationManager {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int rol;
	
	@Autowired
	private IAuthDAO authdao;
	
	@Autowired
	public IAuthDAO getAuthdao() {
		return authdao;
	}
	
	@Autowired
	public void setAuthdao(IAuthDAO authdao) {
		this.authdao = authdao;
	}

	public boolean isAutorized(int action, User user) {
		if(user != null)
			return authdao.isAuthorized(user.getUser_role(), action);
		else
			return false;
	}
}
