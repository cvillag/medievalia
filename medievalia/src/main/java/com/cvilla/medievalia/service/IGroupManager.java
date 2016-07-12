package com.cvilla.medievalia.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;

@Component
public interface IGroupManager extends Serializable {
	public List<Group> getList(User u);
	public String addGroup(int director, String name);
	public boolean removeGroup(int idGroup);
}
