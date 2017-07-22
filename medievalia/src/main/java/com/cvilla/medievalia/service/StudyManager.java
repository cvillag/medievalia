package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IStudyDAO;
import com.cvilla.medievalia.domain.Study;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IStudyManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.utils.Constants;

public class StudyManager implements IStudyManager{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private IStudyDAO studydao;
	
	@Autowired
	public IStudyDAO  getAuthdao() {
		return studydao;
	}
	
	@Autowired
	public void setAuthdao(IStudyDAO authdao) {
		this.studydao = authdao;
	}
	
	public List<Study> getStudyList() {
		return studydao.getStudyList();
	}

	public String addStudy(String nombre, Group groupA, User user) {
		Study c  = studydao.getStudyByName(nombre);
		if( c != null){
			return "nameRepeated";
		}
		else{
			if(groupA == null || user == null){
				return "noCreado";
			}
			else{
				if(user.getUser_role() == Constants.ROLE_PROFESOR ){
					return studydao.createStudyVal(nombre,groupA,user);
				}
				else{
					return studydao.createStudyNoVal(nombre,groupA,user);
				}
			}
		}
	}
	
	private String checkRename(User user, Group groupA, Study cargo, String repeat){
		if( cargo == null){
			return "noExist";
		}
		else{
			if(groupA == null || user == null){
				return "noCambiado";
			}
			else{
				Study c2 = studydao.getStudyByName(repeat);
				if(c2 != null){
					return "repeated";
				}
				else{
					return "next";
				}
			}
		}
	}

	public String renameStudyOwn(String nombre, int idCargo, User user, Group groupA) {
		Study c  = studydao.getStudy(idCargo);
		
		String ret = checkRename(user,groupA,c,nombre);
		if(ret.equals("next")){
			if(c.getIdCreator() != user.getId()){
				return "noCreator";
			}
			else
				return studydao.renameStudy(idCargo,nombre);
		}
		else{
			return ret;
		}
		
	}
	
	public String renameStudy(String nombre, int idCargo, User user, Group groupA) {
		Study c  = studydao.getStudy(idCargo);
		String ret = checkRename(user,groupA,c,nombre);
		if(ret.equals("next")){
			return studydao.renameStudy(idCargo,nombre);
		}
		else{
			return ret;
		}
	}

	public Study getStudy(int idStudy) {
		return studydao.getStudy(idStudy);
	}

	public String deleteStudy(int idCargo) {
		return studydao.deleteStudy(idCargo);
	}

	public List<Study> getStudentStudyList(User user) {
		return studydao.getStudentStudyList(user);
	}

	public String deleteOwnStudy(int idCargo, User user) {
		Study c = studydao.getStudy(idCargo);
		if(c.getIdCreator() == user.getId() && c.getValidado() != Constants.OBJETO_VALIDADO){
			return studydao.deleteStudy(idCargo);
		}
		else{
			return "noCreator";
		}
	}

	public List<Study> getTeacherStudyList(User user, Group groupA) {
		if(!groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
			return null;
		}
		else{
			return studydao.getTeacherStudyList(groupA);
		}
	}

	public String validateStudy(Study c, User user, Group groupA) {
		if(!groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
			return "noTeacher";
		}
		else{
			if(c == null){
				return "noStudy";
			}
			else{
				if(groupA.getIdGrupo() != c.getIdGroup()){
					return "noGroup";
				}
				else{
					return studydao.validateStudy(c.getIdStudy());
				}
			}
		}
	}
	
	public List<User> getUsersToValidateStudyByGroup(User teacher, Group group){
		if(!groupManager.isTeacherOrDirector(teacher, group.getIdGrupo())){
			return null;
		}
		else{
			return studydao.getUsersToValidateByGroup(group.getIdGrupo());
		}
	}
	
	public int getNumUsersToValidateByGroup(User teacher, Group group){
		if(!groupManager.isTeacherOrDirector(teacher, group.getIdGrupo())){
			return -1;
		}
		else{
			return studydao.getStudyToValidateByGroup(group.getIdGrupo());
		}
	}
	
	public int getNumStudysToValidateByUser(Group g, User u){
		return studydao.getStudyToValidateByGroupAndCreator(u, g.getIdGrupo());
	}
}
