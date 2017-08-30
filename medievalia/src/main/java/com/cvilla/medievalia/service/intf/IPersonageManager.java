package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Personage;
import com.cvilla.medievalia.domain.User;

@Component
public interface IPersonageManager extends Serializable{

	public List<Personage> getPersonageList();
	public Personage getPersonage(int idPers);
	public String addPersonage(Personage p, Group groupA, User user);
	public String deleteCharacter(Personage c, User user, Group groupA);
	public String deleteOwnCharacter(Personage c, User user, Group groupA);
	public String renameCharacter(String nombre, int idPers, User user, Group groupA);
	public String renameCharacterOwn(String nombre, int idPers, User user, Group groupA);
	public Personage getPersonageByName(String name);
	public String modifyCharacter(Personage p, int idPersonaje, User user, Group groupA);
	public String modifyCharacterOwn(Personage p, int idPersonaje, User user, Group groupA);
}
