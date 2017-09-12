package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Personage;

public interface IPersonageDAO {
	
	public List<Personage> getPersonageList();
	public Personage getPersonage(int idPersonage);
	public String addPersonage(Personage p);
	public boolean nameRepeat(String name);
	public String deleteCharacter(Personage c);
	public Personage getPersonage(String name);
	public String renamePersonage(int idPers, String nombre);
	public String updatePersonage(Personage p, int idPersonaje);
	public List<Charge> getChargeListNotInPersonage(int idPersonaje);
	public List<Charge> getChargeListInPersonage(int idPersonaje);

}
