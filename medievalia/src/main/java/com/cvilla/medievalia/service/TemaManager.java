package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.ITemaDAO;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.service.intf.ITemaManager;

public class TemaManager implements ITemaManager {

	/**
	 * 
	 */
	
	@Autowired
	ITemaDAO temaDAO;
	
	private static final long serialVersionUID = 1L;

	public String addTema(String name,Group g) {
		// TODO: Comprobar antes manualmente en la lista de temas si hay un tema igual en el mismo grupo
		List<Tema> listaPrevia = temaDAO.getTemaListByGroup(g);
		if(!existeTemaEnGrupo(g, listaPrevia, name)){
			return temaDAO.createTopic(name,g);
		}
		return "repetido";
	}

	public Tema getTema(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Tema> getTemaGrupoByGroup(Group g) {
		return temaDAO.getTemaListByGroup(g);
	}

	private boolean existeTemaEnGrupo(Group g, List<Tema> l, String n){
		boolean enc = false;
		int i = 0;
		while(!enc && i < l.size()){
			enc = l.get(i++).getNombre().equals(n);
		}
		return enc;
	}
}
