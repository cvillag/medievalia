package com.cvilla.medievalia.service;
import com.cvilla.medievalia.domain.Cosa;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface CosaManager extends Serializable{
	
	public void addCosa(String cn);
	
	public List<Cosa> listar();
	
	public String getCosaName(int id);

}
