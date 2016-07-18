package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.Log;
import com.cvilla.medievalia.utils.PaginaException;

@Component
public interface ILogManager extends Serializable{
	
	public void log(int idUser, int idAction, String desc, int succ);
	public List<Log> getActivity(int idUser, int pag, int tamPag, boolean order) throws PaginaException;
	public int getNumPag(int idUser, int tamPag);

}
