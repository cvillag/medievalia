package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Place;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;

@Component
public interface IPlaceManager extends Serializable{
	
	public List<Place> getPlaceList();
	public String addPlace(String nombre, Group groupA, User user);
	public String renamePlace(String nombre, int idLugar, User user,Group groupA);
	public String renamePlaceOwn(String nombre, int idLugar, User user,Group groupA);
	public Place getPlace(int idPlace);
	public String deletePlace(int idLugar);
	public List<Place> getStudentPlaceList(User user);
	public String deleteOwnPlace(int idLugar, User user);
	public List<Place> getTeacherPlaceList(User user, Group groupA);
	public String validatePlace(Place c, User user, Group groupA);
	public List<User> getUsersToValidatePlaceByGroup(User teacher, Group group);
	public int getNumUsersToValidateByGroup(User teacher, Group group);
	public int getNumPlacesToValidateByUser(Group g, User u);
}
