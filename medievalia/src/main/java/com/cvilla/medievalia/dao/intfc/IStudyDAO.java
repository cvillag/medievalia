package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.Study;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;

public interface IStudyDAO {
	public List<Study> getStudyList();
	public String createStudyNoVal(String nombre, Group groupA, User user);
	public String createStudyVal(String nombre, Group groupA, User user);
	public Study getStudyByName(String nombre);
	public Study getStudy(int id);
	public String renameStudy(int idStudy, String nombre);
	public String deleteStudy(int idStudy);
	public List<Study> getStudentStudyList(User user);
	public List<Study> getTeacherStudyList(Group groupA);
	public String validateStudy(int idStudy);
	public List<User> getUsersToValidateByGroup(int idGroup);
	public int getStudyToValidateByGroup(int idGroup);
	public int getStudyToValidateByGroupAndCreator(User user, int idGroup);
}
