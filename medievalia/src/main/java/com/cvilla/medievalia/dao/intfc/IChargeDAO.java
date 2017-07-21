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
	public String deleteCharge(int idCargo);
	public List<Charge> getStudentChargeList(User user);
	public List<Charge> getTeacherChargeList(Group groupA);
	public String validateCharge(int idCharge);
	public List<User> getUsersToValidateByGroup(int idGroup);
	public int getChargesToValidateByGroup(int idGroup);
	public int getChargesToValidateByGroupAndCreator(User user, int idGroup);
}
