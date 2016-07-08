package com.cvilla.medievalia.service;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public interface ILogManager extends Serializable{
	
	public void log(int idUser, int idAction, String desc, int succ);

}
