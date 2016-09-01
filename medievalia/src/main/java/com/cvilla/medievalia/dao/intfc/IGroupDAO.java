package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Students;
import com.cvilla.medievalia.domain.Teachers;
import com.cvilla.medievalia.domain.User;

public interface IGroupDAO {
	
	public List<Group> getGroupList();
	public List<Group> getOwnGroups(User u);
	public String addGroup(Group g);
	public boolean removeGroup(Group g);
	public List<Group> getGroupListByDir(User dir);
	public List<Teachers> getGroupListByTeacher(User teacher);
	public List<Students> getGroupListByStudent(User student);
	public Group getGroup(int idGroup);
	public String addStudent(int idGroup, User user);
	public String addTeacher(int idGroup, User user);
	public boolean isTeacher(int idGroup, User user);
	public Teachers getTeacher(int idGroup, User user);
	public boolean isStudent(int idGroup, User user);
	public Students getStudent(int idGroup, User user);
	public List<User> getPossibleUsersListToGroup(Group group, String filter);
	public List<Students> getEnrolledStudents(Group group);
	public List<Teachers> getEnrolledTeachers(Group group);
}
