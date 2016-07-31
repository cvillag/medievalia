package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.TemaGrupo;
import com.cvilla.medievalia.domain.User;

public interface ITemaDAO {

	public Tema getTemaById(int id);
	public List<TemaGrupo> getTemaListByGroup(Group g);
}