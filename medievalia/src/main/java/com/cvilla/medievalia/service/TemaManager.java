package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.ITemaDAO;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.SubTema;
import com.cvilla.medievalia.domain.Teachers;
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ITemaManager;
import com.cvilla.medievalia.utils.Constants;

public class TemaManager implements ITemaManager {

	@Autowired
	IGroupManager groupManager;
	
	@Autowired
	ITemaDAO temaDAO;
	
	@Autowired
	private ILogManager logManager;
	
	private static final long serialVersionUID = 1L;

	public String addTema(String name,Group g) {
		if(name.length() < Constants.MIN_TEMA_NAME){
			return "noLength";
		}
		else{
			List<Tema> listaPrevia = temaDAO.getTemaListByGroup(g);
			if(!existeTemaEnGrupo(g, listaPrevia, name)){
				return temaDAO.createTopic(name,g);
			}
			return "repetido";
		}
	}

	public Tema getTema(int id) {
		return temaDAO.getTemaById(id);
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

	public List<SubTema> getSubTemaGrupoByTema(Group groupA, int idTema) {
		return temaDAO.getSubtemaList(idTema);
	}

	public String renameTema(String nombre, int idTema, User user, Group g) {
		if(nombre.length() < Constants.MIN_TEMA_NAME){
			return "noLength";
		}
		else{
			Tema t = temaDAO.getTemaById(idTema);
			if(groupManager.isTeacherOrDirector(user, t.getIdGroup())){
				Tema t2 = temaDAO.getTemaByName(nombre, g);
				if(t2 == null || t2.getIdTema() < 1 || t2.getNombre() == null){
					return temaDAO.renameTopic(idTema,nombre);
				}
				else{
					return "nameRepeat";
				}
			}
			else{
				return "noPrivileges";
			}
		}
	}

	public String addSubTema(String name, Group g, int idTema) {
		if(name.length() < Constants.MIN_TEMA_NAME){
			return "noLength";
		}
		else{
			Tema t = temaDAO.getTemaById(idTema);
			if(t == null || t.getIdTema() < 1 || t.getNombre() == null){
				return "noTema";
			}
			else{
				if(t.getIdGroup() != g.getIdGrupo()){
					return "noGroup";
				}
				else{
					List<SubTema> lista = temaDAO.getSubtemaList(t.getIdTema());
					boolean enc = false;
					int i = 0;
					while (!enc && i < lista.size()){
						enc = name.equals(lista.get(i++).getNombreSubtema());
					}
					if(enc){
						return "nameRepeat";
					}
					else{
						return temaDAO.createSubTopic(name, t.getIdTema());
					}
				}
			}
		}
	}
}
