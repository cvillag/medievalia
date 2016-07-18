package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.User;

public interface IGroupDAO {
	
	public List<Group> getGroupList();
	public List<Group> getOwnGroups(User u);
	public String addGroup(Group g);
	public boolean removeGroup(Group g);
}
