package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;

public interface IChargeDAO {
	List<Charge> getChargeList();

	String createChargeNoVal(String nombre, Group groupA, User user);

	String createChargeVal(String nombre, Group groupA, User user);
}
