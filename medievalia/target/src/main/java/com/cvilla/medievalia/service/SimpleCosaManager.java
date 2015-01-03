package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.CosaDAO;
import com.cvilla.medievalia.dao.ICosaDAO;
import com.cvilla.medievalia.domain.Cosa;

public class SimpleCosaManager implements CosaManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private List<Cosa> cosas;
	@Autowired
	private ICosaDAO cosaDAO;
	
	public void setCosas(List<Cosa> c){
		this.cosas = c;
	}
	
	@Autowired
	public ICosaDAO getCosaDAO() {
		return cosaDAO;
	}
	
	@Autowired
	public void setCosaDAO(ICosaDAO cosaDAO) {
		this.cosaDAO = cosaDAO;
	}

	public void addCosa(String cn) {
		// TODO Auto-generated method stub
		cosas.add(new Cosa(cn));
	}

	public List<Cosa> listar() {
		// TODO Auto-generated method stub
		return cosas;
	}


	public String getCosaName(int id) {
		String nombre = cosaDAO.getNombre(id);
		return nombre;
	}

}
