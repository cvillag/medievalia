package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IPlaceDAO;
import com.cvilla.medievalia.domain.Place;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IPlaceManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.utils.Constants;

public class PlaceManager implements IPlaceManager{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private IPlaceDAO placedao;
	
	@Autowired
	public IPlaceDAO  getAuthdao() {
		return placedao;
	}
	
	@Autowired
	public void setAuthdao(IPlaceDAO authdao) {
		this.placedao = authdao;
	}
	
	public List<Place> getPlaceList() {
		return placedao.getPlaceList();
	}

	public String addPlace(String nombre, Group groupA, User user) {
		Place c  = placedao.getPlaceByName(nombre);
		if( c != null){
			return "nameRepeated";
		}
		else{
			if(groupA == null || user == null){
				return "noCreado";
			}
			else{
				if(user.getUser_role() == Constants.ROLE_PROFESOR ){
					return placedao.createPlaceVal(nombre,groupA,user);
				}
				else{
					return placedao.createPlaceNoVal(nombre,groupA,user);
				}
			}
		}
	}
	
	private String checkRename(User user, Group groupA, Place lugar, String repeat){
		if( lugar == null){
			return "noExist";
		}
		else{
			if(groupA == null || user == null){
				return "noCambiado";
			}
			else{
				Place c2 = placedao.getPlaceByName(repeat);
				if(c2 != null){
					return "repeated";
				}
				else{
					return "next";
				}
			}
		}
	}

	public String renamePlaceOwn(String nombre, int idLugar, User user, Group groupA) {
		Place c  = placedao.getPlace(idLugar);
		
		String ret = checkRename(user,groupA,c,nombre);
		if(ret.equals("next")){
			if(c.getIdCreator() != user.getId()){
				return "noCreator";
			}
			else
				return placedao.renamePlace(idLugar,nombre);
		}
		else{
			return ret;
		}
		
	}
	
	public String renamePlace(String nombre, int idLugar, User user, Group groupA) {
		Place c  = placedao.getPlace(idLugar);
		String ret = checkRename(user,groupA,c,nombre);
		if(ret.equals("next")){
			return placedao.renamePlace(idLugar,nombre);
		}
		else{
			return ret;
		}
	}

	public Place getPlace(int idPlace) {
		return placedao.getPlace(idPlace);
	}

	public String deletePlace(int idLugar) {
		return placedao.deletePlace(idLugar);
	}

	public List<Place> getStudentPlaceList(User user) {
		return placedao.getStudentPlaceList(user);
	}

	public String deleteOwnPlace(int idLugar, User user) {
		Place c = placedao.getPlace(idLugar);
		if(c.getIdCreator() == user.getId() && c.getValidado() != Constants.OBJETO_VALIDADO){
			return placedao.deletePlace(idLugar);
		}
		else{
			return "noCreator";
		}
	}

	public List<Place> getTeacherPlaceList(User user, Group groupA) {
		if(!groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
			return null;
		}
		else{
			return placedao.getTeacherPlaceList(groupA);
		}
	}

	public String validatePlace(Place c, User user, Group groupA) {
		if(!groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
			return "noTeacher";
		}
		else{
			if(c == null){
				return "noPlace";
			}
			else{
				if(groupA.getIdGrupo() != c.getIdGroup()){
					return "noGroup";
				}
				else{
					return placedao.validatePlace(c.getIdPlace());
				}
			}
		}
	}
	
	public List<User> getUsersToValidatePlaceByGroup(User teacher, Group group){
		if(!groupManager.isTeacherOrDirector(teacher, group.getIdGrupo())){
			return null;
		}
		else{
			return placedao.getUsersToValidateByGroup(group.getIdGrupo());
		}
	}
	
	public int getNumUsersToValidateByGroup(User teacher, Group group){
		if(!groupManager.isTeacherOrDirector(teacher, group.getIdGrupo())){
			return -1;
		}
		else{
			return placedao.getPlacesToValidateByGroup(group.getIdGrupo());
		}
	}
	
	public int getNumPlacesToValidateByUser(Group g, User u){
		return placedao.getPlacesToValidateByGroupAndCreator(u, g.getIdGrupo());
	}
}
