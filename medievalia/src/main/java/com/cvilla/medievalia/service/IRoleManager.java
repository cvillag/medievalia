package com.cvilla.medievalia.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Role;

@Component
public interface IRoleManager extends Serializable{
	
	public List<Role> getRoleList();
}
