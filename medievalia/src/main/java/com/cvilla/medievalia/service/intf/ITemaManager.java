package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.SubTema;
import com.cvilla.medievalia.domain.User;

@Component
public interface ITemaManager extends Serializable{
	
	public String addTema(String name,Group g);
	public String addSubTema(String name,Group g,int idTema);
	public Tema getTema(int id);
	public List<Tema> getTemaGrupoByGroup(Group g);
	public List<SubTema> getSubTemaGrupoByTema(Group groupA, int idTema);
	public String renameTema(String nombre, int idTema, User user, Group g);
	public String deleteTema(int idTema, User user, Group g);
	public String renameSubTema(String nombre, int idTema, int idSubTema, User user,Group groupA);
	public String deleteSubTema(int idTema, int idSubtema);

}
