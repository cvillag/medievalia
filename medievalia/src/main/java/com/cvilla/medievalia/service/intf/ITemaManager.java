package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.TemaGrupo;

@Component
public interface ITemaManager extends Serializable{
	
	public Tema addTema(String name);
	public Tema getTema(int id);
	public List<TemaGrupo> getTemaGrupoByGroup(Group g);

}
