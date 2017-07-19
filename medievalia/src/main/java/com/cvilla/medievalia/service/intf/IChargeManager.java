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
}
