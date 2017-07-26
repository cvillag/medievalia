package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IAuthorDAO;
import com.cvilla.medievalia.domain.Author;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IAuthorManager;
import com.cvilla.medievalia.service.intf.IGroupManager;
import com.cvilla.medievalia.utils.Constants;

public class AuthorManager implements IAuthorManager{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IGroupManager groupManager;
	
	@Autowired
	private IAuthorDAO authordao;
	
	@Autowired
	public IAuthorDAO  getAuthdao() {
		return authordao;
	}
	
	@Autowired
	public void setAuthdao(IAuthorDAO authdao) {
		this.authordao = authdao;
	}
	
	public List<Author> getAuthorList() {
		return authordao.getAuthorList();
	}

	public String addAuthor(String nombre, Group groupA, User user) {
		Author c  = authordao.getAuthorByName(nombre);
		if( c != null){
			return "nameRepeated";
		}
		else{
			if(groupA == null || user == null){
				return "noCreado";
			}
			else{
				if(user.getUser_role() == Constants.ROLE_PROFESOR ){
					return authordao.createAuthorVal(nombre,groupA,user);
				}
				else{
					return authordao.createAuthorNoVal(nombre,groupA,user);
				}
			}
		}
	}
	
	private String checkRename(User user, Group groupA, Author autor, String repeat){
		if( autor == null){
			return "noExist";
		}
		else{
			if(groupA == null || user == null){
				return "noCambiado";
			}
			else{
				Author c2 = authordao.getAuthorByName(repeat);
				if(c2 != null){
					return "repeated";
				}
				else{
					return "next";
				}
			}
		}
	}

	public String renameAuthorOwn(String nombre, int idAutor, User user, Group groupA) {
		Author c  = authordao.getAuthor(idAutor);
		
		String ret = checkRename(user,groupA,c,nombre);
		if(ret.equals("next")){
			if(c.getIdCreator() != user.getId()){
				return "noCreator";
			}
			else
				return authordao.renameAuthor(idAutor,nombre);
		}
		else{
			return ret;
		}
		
	}
	
	public String renameAuthor(String nombre, int idAutor, User user, Group groupA) {
		Author c  = authordao.getAuthor(idAutor);
		String ret = checkRename(user,groupA,c,nombre);
		if(ret.equals("next")){
			return authordao.renameAuthor(idAutor,nombre);
		}
		else{
			return ret;
		}
	}

	public Author getAuthor(int idAuthor) {
		return authordao.getAuthor(idAuthor);
	}

	public String deleteAuthor(int idAutor) {
		return authordao.deleteAuthor(idAutor);
	}

	public List<Author> getStudentAuthorList(User user) {
		return authordao.getStudentAuthorList(user);
	}

	public String deleteOwnAuthor(int idAutor, User user) {
		Author c = authordao.getAuthor(idAutor);
		if(c.getIdCreator() == user.getId() && c.getValidado() != Constants.OBJETO_VALIDADO){
			return authordao.deleteAuthor(idAutor);
		}
		else{
			return "noCreator";
		}
	}

	public List<Author> getTeacherAuthorList(User user, Group groupA) {
		if(!groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
			return null;
		}
		else{
			return authordao.getTeacherAuthorList(groupA);
		}
	}

	public String validateAuthor(Author c, User user, Group groupA) {
		if(!groupManager.isTeacherOrDirector(user, groupA.getIdGrupo())){
			return "noTeacher";
		}
		else{
			if(c == null){
				return "noAuthor";
			}
			else{
				if(groupA.getIdGrupo() != c.getIdGroup()){
					return "noGroup";
				}
				else{
					return authordao.validateAuthor(c.getIdAuthor());
				}
			}
		}
	}
	
	public List<User> getUsersToValidateAuthorByGroup(User teacher, Group group){
		if(!groupManager.isTeacherOrDirector(teacher, group.getIdGrupo())){
			return null;
		}
		else{
			return authordao.getUsersToValidateByGroup(group.getIdGrupo());
		}
	}
	
	public int getNumUsersToValidateByGroup(User teacher, Group group){
		if(!groupManager.isTeacherOrDirector(teacher, group.getIdGrupo())){
			return -1;
		}
		else{
			return authordao.getAuthorsToValidateByGroup(group.getIdGrupo());
		}
	}
	
	public int getNumAuthorsToValidateByUser(Group g, User u){
		return authordao.getAuthorsToValidateByGroupAndCreator(u, g.getIdGrupo());
	}
}
