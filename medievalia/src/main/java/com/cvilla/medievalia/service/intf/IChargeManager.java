package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;

@Component
public interface IChargeManager extends Serializable{
	
	public List<Charge> getChargeList();
	public String addCharge(String nombre, Group groupA, User user);
	public String renameCharge(String nombre, int idCargo, User user,Group groupA);
	public String renameChargeOwn(String nombre, int idCargo, User user,Group groupA);
	public Charge getCharge(int idCharge);
	public String deleteCharge(int idCargo);
	public List<Charge> getStudentChargeList(User user);
	public String deleteOwnCharge(int idCargo, User user);
	public List<Charge> getTeacherChargeList(User user, Group groupA);
	public String validateCharge(Charge c, User user, Group groupA);
	public List<User> getUsersToValidateChargeByGroup(User teacher, Group group);
	public int getNumUsersToValidateByGroup(User teacher, Group group);
	public int getNumChargesToValidateByUser(Group g, User u);
	
}
