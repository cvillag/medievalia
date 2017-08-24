package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IPersonageDAO;
import com.cvilla.medievalia.domain.Personage;
import com.cvilla.medievalia.service.intf.IPersonageManager;

public class PersonageManager implements IPersonageManager {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IPersonageDAO persdao;

	public List<Personage> getPersonageList() {
		return persdao.getPersonageList();
	}

	public Personage getPersonage(int idPers) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPersonage(Personage p) {
		// TODO Auto-generated method stub

	}

}
