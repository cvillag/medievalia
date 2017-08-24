package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Personage;

@Component
public interface IPersonageManager extends Serializable{

	public List<Personage> getPersonageList();
	public Personage getPersonage(int idPers);
	public void addPersonage(Personage p);
}
