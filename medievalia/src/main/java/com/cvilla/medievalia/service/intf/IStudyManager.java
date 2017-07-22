package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Study;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;

@Component
public interface IStudyManager extends Serializable{
	
	public List<Study> getStudyList();
	public String addStudy(String nombre, Group groupA, User user);
	public String renameStudy(String nombre, int idCargo, User user,Group groupA);
	public String renameStudyOwn(String nombre, int idCargo, User user,Group groupA);
	public Study getStudy(int idStudy);
	public String deleteStudy(int idCargo);
	public List<Study> getStudentStudyList(User user);
	public String deleteOwnStudy(int idCargo, User user);
	public List<Study> getTeacherStudyList(User user, Group groupA);
	public String validateStudy(Study c, User user, Group groupA);
	public List<User> getUsersToValidateStudyByGroup(User teacher, Group group);
	public int getNumUsersToValidateByGroup(User teacher, Group group);
	public int getNumStudysToValidateByUser(Group g, User u);
}
