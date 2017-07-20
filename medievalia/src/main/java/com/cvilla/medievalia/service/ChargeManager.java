package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IChargeDAO;
import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IChargeManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.utils.Constants;

public class ChargeManager implements IChargeManager{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private IChargeDAO chargedao;
	
	@Autowired
	public IChargeDAO  getAuthdao() {
		return chargedao;
	}
	
	@Autowired
	public void setAuthdao(IChargeDAO authdao) {
		this.chargedao = authdao;
	}
	
	public List<Charge> getChargeList() {
		return chargedao.getChargeList();
	}

	public String addCharge(String nombre, Group groupA, User user) {
		Charge c  = chargedao.getChargeByName(nombre);
		if( c != null){
			return "nameRepeated";
		}
		else{
			if(groupA == null || user == null){
				return "noCreado";
			}
			else{
				if(user.getUser_role() == Constants.ROLE_PROFESOR ){
					return chargedao.createChargeVal(nombre,groupA,user);
				}
				else{
					return chargedao.createChargeNoVal(nombre,groupA,user);
				}
			}
		}
	}
	
	private String checkRename(User user, Group groupA, Charge cargo, String repeat){
		if( cargo == null){
			return "noExist";
		}
		else{
			if(groupA == null || user == null){
				return "noCambiado";
			}
			else{
				Charge c2 = chargedao.getChargeByName(repeat);
				if(c2 != null){
					return "repeated";
				}
				else{
					return "next";
				}
			}
		}
	}

	public String renameChargeOwn(String nombre, int idCargo, User user, Group groupA) {
		Charge c  = chargedao.getCharge(idCargo);
		
		String ret = checkRename(user,groupA,c,nombre);
		if(ret.equals("next")){
			if(c.getIdCreator() != user.getId()){
				return "noCreator";
			}
			else
				return chargedao.renameCharge(idCargo,nombre);
		}
		else{
			return ret;
		}
		
	}
	
	public String renameCharge(String nombre, int idCargo, User user, Group groupA) {
		Charge c  = chargedao.getCharge(idCargo);
		String ret = checkRename(user,groupA,c,nombre);
		if(ret.equals("next")){
			return chargedao.renameCharge(idCargo,nombre);
		}
		else{
			return ret;
		}
	}

	public Charge getCharge(int idCharge) {
		return chargedao.getCharge(idCharge);
	}

	public String deleteCharge(int idCargo) {
		return chargedao.deleteCharge(idCargo);
	}

	public List<Charge> getStudentChargeList(User user) {
		return chargedao.getStudentChargeList(user);
	}

	public String deleteOwnCharge(int idCargo, User user) {
		Charge c = chargedao.getCharge(idCargo);
		if(c.getIdCreator() == user.getId() && c.getValidado() != Constants.OBJETO_VALIDADO){
			return chargedao.deleteCharge(idCargo);
		}
		else{
			return "noCreator";
		}
	}

	public List<Charge> getTeacherChargeList(User user, Group groupA) {
		if(!groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
			return null;
		}
		else{
			return chargedao.getTeacherChargeList(groupA);
		}
	}

	public String validateCharge(Charge c, User user, Group groupA) {
		if(!groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
			return "noTeacher";
		}
		else{
			if(c == null){
				return "noCharge";
			}
			else{
				if(groupA.getIdGrupo() != c.getIdGroup()){
					return "noGroup";
				}
				else{
					return chargedao.validateCharge(c.getIdCharge());
				}
			}
		}
	}
}
