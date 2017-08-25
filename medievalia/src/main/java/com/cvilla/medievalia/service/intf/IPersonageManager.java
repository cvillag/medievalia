package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Personage;
import com.cvilla.medievalia.domain.User;

@Component
public interface IPersonageManager extends Serializable{

	public List<Personage> getPersonageList();
	public Personage getPersonage(int idPers);
	public String addPersonage(Personage p, Group groupA, User user);
}
