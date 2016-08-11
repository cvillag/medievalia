package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Tema;

@Component
public interface ITemaManager extends Serializable{
	
	public String addTema(String name,Group g);
	public Tema getTema(int id);
	public List<Tema> getTemaGrupoByGroup(Group g);

}
