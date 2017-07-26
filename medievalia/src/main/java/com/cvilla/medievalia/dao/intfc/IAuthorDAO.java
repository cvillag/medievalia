package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.Author;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;

public interface IAuthorDAO {
	public List<Author> getAuthorList();
	public String createAuthorNoVal(String nombre, Group groupA, User user);
	public String createAuthorVal(String nombre, Group groupA, User user);
	public Author getAuthorByName(String nombre);
	public Author getAuthor(int id);
	public String renameAuthor(int idCargo, String nombre);
	public String deleteAuthor(int idCargo);
	public List<Author> getStudentAuthorList(User user);
	public List<Author> getTeacherAuthorList(Group groupA);
	public String validateAuthor(int idAuthor);
	public List<User> getUsersToValidateByGroup(int idGroup);
	public int getAuthorsToValidateByGroup(int idGroup);
	public int getAuthorsToValidateByGroupAndCreator(User user, int idGroup);
}
