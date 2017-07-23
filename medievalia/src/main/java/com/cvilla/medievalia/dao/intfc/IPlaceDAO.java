package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.Place;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;

public interface IPlaceDAO {
	public List<Place> getPlaceList();
	public String createPlaceNoVal(String nombre, Group groupA, User user);
	public String createPlaceVal(String nombre, Group groupA, User user);
	public Place getPlaceByName(String nombre);
	public Place getPlace(int id);
	public String renamePlace(int idCargo, String nombre);
	public String deletePlace(int idCargo);
	public List<Place> getStudentPlaceList(User user);
	public List<Place> getTeacherPlaceList(Group groupA);
	public String validatePlace(int idPlace);
	public List<User> getUsersToValidateByGroup(int idGroup);
	public int getPlacesToValidateByGroup(int idGroup);
	public int getPlacesToValidateByGroupAndCreator(User user, int idGroup);
}
