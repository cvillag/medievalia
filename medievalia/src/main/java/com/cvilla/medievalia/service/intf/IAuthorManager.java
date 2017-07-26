package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Author;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;

@Component
public interface IAuthorManager extends Serializable{
	
	public List<Author> getAuthorList();
	public String addAuthor(String nombre, Group groupA, User user);
	public String renameAuthor(String nombre, int idAutor, User user,Group groupA);
	public String renameAuthorOwn(String nombre, int idAutor, User user,Group groupA);
	public Author getAuthor(int idAuthor);
	public String deleteAuthor(int idAutor);
	public List<Author> getStudentAuthorList(User user);
	public String deleteOwnAuthor(int idAutor, User user);
	public List<Author> getTeacherAuthorList(User user, Group groupA);
	public String validateAuthor(Author c, User user, Group groupA);
	public List<User> getUsersToValidateAuthorByGroup(User teacher, Group group);
	public int getNumUsersToValidateByGroup(User teacher, Group group);
	public int getNumAuthorsToValidateByUser(Group g, User u);
}
