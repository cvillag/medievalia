package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.SubTema;
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.User;

public interface ITemaDAO {

	public Tema getTemaById(int id);
	public List<Tema> getTemaListByGroup(Group g);
	public String createTopic(String name,Group group);
	public Tema getTemaByName(String name, Group g);
	public SubTema getSubTemaByName(String name, Group g);
	public List<SubTema> getSubtemaList(int idTema);
	public String renameTopic(int idTema, String nombre);
	public String createSubTopic(String name, int idTema);
	public String deleteTema(int idTema);
	public String renameSubTopic(int idSubTema, String nombre);
	public SubTema getSubTema(int idSubTema);
}