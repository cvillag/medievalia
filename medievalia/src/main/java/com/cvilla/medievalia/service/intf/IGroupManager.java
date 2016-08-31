package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Students;
import com.cvilla.medievalia.domain.Teachers;
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.User;

@Component
public interface IGroupManager extends Serializable {
	public List<Group> getList(User u);
	public String addGroup(int director, String name, String description);
	public boolean removeGroup(int idGroup);
	public List<Group> getListByDirector(User user, User dir);
	public List<Teachers> getListByTeacher(User user, User teacher);
	public List<Students> getListByStudent(User user, User user2);
	public Group getGroupById(int idGroup);
	public boolean setActiveGroup (User user, Group group, ILogManager logManager);
	public boolean isTeacherOrDirector(User user, int idGrupo);
	public String addStudent(Group group, int idstudent, User user);
	public String addTeacher(Group group, int idteacher, User user);
	public List<User> getUsersToGroup(Group group, String filter);
	public List<Students> getStudentParticipantList(Group group);
	public List<Teachers> getTeacherParticipantList(Group group);
}
