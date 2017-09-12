package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IGroupDAO;
import com.cvilla.medievalia.dao.mappers.UserMapper;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Students;
import com.cvilla.medievalia.domain.Teachers;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.service.intf.ILogManager;
import com.cvilla.medievalia.service.intf.ILoginManager;
import com.cvilla.medievalia.utils.Constants;

public class GroupManager implements IGroupManager {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IGroupDAO groupDAO;
	
	@Autowired
	public IGroupDAO getGroupDAO() {
		return groupDAO;
	}
	
	@Autowired
	public void setGroupDAO(IGroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	
	@Autowired
	public ILogManager logManager;
	
	@Autowired
	public ILogManager getLogManager() {
		return logManager;
	}

	@Autowired
	public void setLogManager(ILogManager logManager) {
		this.logManager = logManager;
	}
	
	@Autowired
	public ILoginManager userManager;
	
	public List<Group> getList(User u) {
		List<Group> l;
		if(u.getUser_role() == Constants.ROLE_ADMIN){
			l = groupDAO.getGroupList();
			if(l != null){
				logManager.log(u.getId(), Constants.P_GROUP_LIST, "Listado de todos los grupos", Constants.P_OK);
			}
			else{
				logManager.log(u.getId(), Constants.P_GROUP_LIST, "Listado de todos los grupos, sin éxito", Constants.P_NOK);
			}
		}
		else{
			l = groupDAO.getOwnGroups(u);
			if(l != null){
				logManager.log(u.getId(), Constants.P_GROUP_OWN, "Listado de los grupos propios del usuario", Constants.P_OK);
			}
			else{
				logManager.log(u.getId(), Constants.P_GROUP_OWN, "Listado de los grupos propios del usuario", Constants.P_NOK);
			}
		}
		return l;
	}

	public String addGroup(int director, String name, String description) {
		if(name.length() > 3){
			Group g = new Group(director, name, description);
			return groupDAO.addGroup(g);
		}
		return "noLength";
	}

	public boolean removeGroup(int idGroup) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Group> getListByDirector(User user, User dir) {
		List<Group> l = null;
		if(user.getUser_role() == Constants.ROLE_ADMIN){
			l = groupDAO.getGroupListByDir(dir);
			logManager.log(user.getId(), Constants.P_DETAIL_DIRECTOR_GROUPS_OTHER,"Visualización de grupos del usuario con id " + dir.getId() + " como director", Constants.P_OK);
		}
		else if(user.getUser_role() == Constants.ROLE_PROFESOR){
			l = groupDAO.getGroupListByDir(user);
			logManager.log(user.getId(), Constants.P_DETAIL_DIRECTOR_GROUPS_OWN,"Visualización de grupos del usuario propio id " + dir.getId() + " como director", Constants.P_OK);
		}
		return l;
	}

	public List<Teachers> getListByTeacher(User user, User teacher) {
		List<Teachers> l = null;
		if(user.getUser_role() == Constants.ROLE_ADMIN){
			l = groupDAO.getGroupListByTeacher(teacher);
			logManager.log(user.getId(), Constants.P_DETAIL_TEACHER_GROUPS_OTHER,"Visualización de grupos del usuario con id " + teacher.getId() + " como profesor", Constants.P_OK);
		}
		else if(user.getUser_role() == Constants.ROLE_PROFESOR){
			l = groupDAO.getGroupListByTeacher(teacher);
			logManager.log(user.getId(), Constants.P_DETAIL_TEACHER_GROUPS_OWN,"Visualización de grupos del usuario propio id " + teacher.getId() + " como profesor", Constants.P_OK);
		}
		return l;
	}

	public List<Students> getListByStudent(User user, User user2) {
		List<Students> l = null;
		if(user.getUser_role() == Constants.ROLE_ADMIN || user.getUser_role() == Constants.ROLE_PROFESOR){
			l = groupDAO.getGroupListByStudent(user2);
			logManager.log(user.getId(), Constants.P_DETAIL_STUDENT_GROUPS_OTHER,"Visualización de grupos del usuario con id " + user2.getId() + " como estudiante", Constants.P_OK);
		}
		else if(user.getUser_role() == Constants.ROLE_ALUMNO){
			l = groupDAO.getGroupListByStudent(user);
			logManager.log(user.getId(), Constants.P_DETAIL_STUDENT_GROUPS_OWN,"Visualización de grupos del usuario propio id " + user.getId() + " como estudiante", Constants.P_OK);
		}
		return l;
	}

	public Group getGroupById(int idGroup) {
		return groupDAO.getGroup(idGroup);
	}

	public boolean setActiveGroup(User user, Group group, ILogManager log) {
		boolean enc = false;
		int i = 0;
		if(user.getUser_role() == Constants.ROLE_ALUMNO){
			List<Students> lista = groupDAO.getGroupListByStudent(user);
			while(!enc || lista.size() > i){
				enc = lista.get(i++).getIdStudent() == user.getId();
			}
			if(enc){
				logManager.log(user.getId(), Constants.P_SELECT_ACTIVE_GROUP, "Selección de grupo " + group.getName() + " como alumno", Constants.P_OK);
			}
			else{
				logManager.log(user.getId(), Constants.P_SELECT_ACTIVE_GROUP, "Selección de grupo " + group.getName() + " como alumno fallida por no estar en lista", Constants.P_NOK);
			}
		}
		else if(user.getUser_role() == Constants.ROLE_PROFESOR){
			if(user.getId() == group.getDirector()){
				logManager.log(user.getId(), Constants.P_SELECT_ACTIVE_GROUP, "Selección de grupo " + group.getName() + " como director", Constants.P_OK);
				enc = true;
			}
			else{
				List<Teachers> lista = groupDAO.getGroupListByTeacher(user);
				while(!enc || lista.size() > i){
					enc = lista.get(i++).getIdTeacher() == user.getId();
				}
				if(enc){
					logManager.log(user.getId(), Constants.P_SELECT_ACTIVE_GROUP, "Selección de grupo " + group.getName() + " como profesor", Constants.P_OK);
				}
				else{
					logManager.log(user.getId(), Constants.P_SELECT_ACTIVE_GROUP, "Selección de grupo " + group.getName() + " como profesor fallida por no estar en lista", Constants.P_NOK);
				}
			}
		}
		return enc;
	}
	
	public String addStudent(Group group, int idstudent, User user){
		if(group.getDirector() == idstudent){
			return "alreadyDirector";
		}
		else{
			User student = userManager.getUser(idstudent);
			if(student == null){
				return "noUser";
			}
			else{
				if(isTeacherOrDirector(user, group.getIdGrupo())){
					try {
						if(groupDAO.isTeacher(group.getIdGrupo(), student)){
							return "alreadyTeacher";
						}
						else{
							return groupDAO.addStudent(group.getIdGrupo(), student);
						}
					} catch (Exception e) {
						return "error";
					}
				}
				else{
					return "noDirector";
				}
			}
		}
	}
	
	public String addTeacher(Group group, int idteacher, User user){
		if(group.getDirector() == idteacher){
			return "alreadyDirector";
		}
		else{
			User teacher = userManager.getUser(idteacher);
			if(teacher == null){
				return "noUser";
			}
			else{
				if(teacher.getUser_role() != Constants.ROLE_PROFESOR){
					return "noTeacher";
				}
				else{
					try {
						if(group.getDirector() == user.getId() || groupDAO.isStudent(group.getIdGrupo(), teacher)){
							if(groupDAO.isStudent(group.getIdGrupo(), teacher)){
								return "alreadyStudent";
							}
							else{
								return groupDAO.addTeacher(group.getIdGrupo(), teacher);
							}
						}
						else{
							return "noTeacherOrDirector";
						}
					} catch (Exception e) {
						return "error";
					}
				}
			}
		}
	}
	
	public List<User> getUsersToGroup(Group group, String filter){
		return groupDAO.getPossibleUsersListToGroup(group, filter);
	}
	
	public boolean isTeacherOrDirector(User user, int idGrupo){
		List<Group> listaDir = getListByDirector(user, user);
		List<Teachers> listaTeach = getListByTeacher(user, user);
		boolean enc = false;
		int i = 0;
		
		while(!enc && i < listaDir.size()){
			enc = listaDir.get(i++).getIdGrupo() == idGrupo;
		}
		
		i = 0;
		while(!enc && i < listaTeach.size()){
			enc = listaTeach.get(i++).getIdGroup() == idGrupo;
		}
		return enc;
	}

	public List<Students> getStudentParticipantList(Group group) {
		return groupDAO.getEnrolledStudents(group);
	}

	public List<Teachers> getTeacherParticipantList(Group group) {
		return groupDAO.getEnrolledTeachers(group);
	}

	public String removeStudent(Group group, int idstudent, User user) {
		if(group.getDirector() == idstudent){
			return "alreadyDirector";
		}
		else{
			User student = userManager.getUser(idstudent);
			if(student== null){
				return "noUser";
			}
			else{
				if(isTeacherOrDirector(user, group.getIdGrupo())){
					try {
						if(groupDAO.isTeacher(group.getIdGrupo(), student)){
							return "alreadyTeacher";
						}
						else{
							return groupDAO.removeStudent(group.getIdGrupo(), student);
						}
					} catch (Exception e) {
						return "error";
					}
				}
				else{
					return "noDirector";
				}
			}
		}
	}

	public String removeTeacher(Group group, int idteacher, User user) {
		if(group.getDirector() == idteacher){
			return "alreadyDirector";
		}
		else{
			User teacher = userManager.getUser(idteacher);
			if(teacher == null){
				return "noUser";
			}
			else{
				if(teacher.getUser_role() != Constants.ROLE_PROFESOR){
					return "noTeacher";
				}
				else{
					try {
						if(group.getDirector() == user.getId() || groupDAO.isStudent(group.getIdGrupo(), teacher)){
							if(groupDAO.isStudent(group.getIdGrupo(), teacher)){
								return "alreadyStudent";
							}
							else{
								return groupDAO.removeTeacher(group.getIdGrupo(), teacher);
							}
						}
						else{
							return "noTeacherOrDirector";
						}
					} catch (Exception e) {
						return "error";
					}
				}
			}
		}
	}
}
