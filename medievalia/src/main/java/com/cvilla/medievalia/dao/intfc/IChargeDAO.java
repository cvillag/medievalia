package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;

public interface IChargeDAO {
	public List<Charge> getChargeList();

	public String createChargeNoVal(String nombre, Group groupA, User user);

	public String createChargeVal(String nombre, Group groupA, User user);
	
	public Charge getChargeByName(String nombre);
	public Charge getCharge(int id);

	public String renameCharge(int idCargo, String nombre);
}
