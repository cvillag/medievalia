package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.ITemaDAO;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.TemaGrupo;
import com.cvilla.medievalia.service.intf.ITemaManager;

public class TemaManager implements ITemaManager {

	/**
	 * 
	 */
	
	@Autowired
	ITemaDAO temaDAO;
	
	private static final long serialVersionUID = 1L;

	public Tema addTema(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tema getTema(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TemaGrupo> getTemaGrupoByGroup(Group g) {
		return temaDAO.getTemaListByGroup(g);
	}

}
