package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.Personage;

public interface IPersonageDAO {
	
	public List<Personage> getPersonageList();
	public Personage getPersonage(int idPersonage);
	public void addPersonage(Personage p);

}
