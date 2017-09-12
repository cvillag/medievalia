package com.cvilla.medievalia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvilla.medievalia.dao.intfc.IObjetoDAO;
import com.cvilla.medievalia.domain.AtributoSencilloDOM;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.ObjetoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IObjectManager;

public class ObjectManager implements IObjectManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IObjetoDAO objetoDAO;
	
	@Autowired
	public IObjetoDAO getObjetoDAO() {
		return objetoDAO;
	}

	@Autowired
	public void setObjetoDAO(IObjetoDAO objetoDAO) {
		this.objetoDAO = objetoDAO;
	}

	public List<TipoObjetoDOM> getTiposObjetosDOM() {
		return objetoDAO.getObjectTypeList();
	}
	
	public TipoObjetoDOM getTipoObjetoDOM(int idType) {
		return objetoDAO.getObjectType(idType);
	}
	
	public List<TipoObjetoDOM> getAtributosCObjetoDOM(TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AtributoSencilloDOM> getAtributosSObjetoDOM(TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjetoDOM getObjetoDOM(TipoObjetoDOM tipo, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ObjetoDOM> getObjetoDOMListByType(TipoObjetoDOM tipo) {
		// TODO Tras conseguir los nombres con ObjetoDOM hay que conseguir los atributos
		return objetoDAO.getObjectListByTipe(tipo);
	}

	public String addObjetoDOM(TipoObjetoDOM tipo, ObjetoDOM o, Group groupA,
			User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public String renameObjetoDOM(TipoObjetoDOM tipo, String nombre, int id,
			User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String renameObjetoDOMOwn(TipoObjetoDOM tipo, String nombre, int id,
			User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteObjetoDOM(TipoObjetoDOM tipo, int id, User user,
			Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ObjetoDOM> getStudentObjetoDOMList(TipoObjetoDOM tipo, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteObjetoDOMOwn(TipoObjetoDOM tipo, int id, User user,
			Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ObjetoDOM> getTeachersObjetoDOMList(TipoObjetoDOM tipo,
			User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String validateObjetoDOM(TipoObjetoDOM tipo, int id, User user,
			Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsersToValidateObjectDOMByGroup(User teacher,
			Group group, TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumUsersToValidateByGroup(User teacher, Group group,
			TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumObjetoDOMToValidateByUser(User teacher, Group group,
			TipoObjetoDOM tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<ObjetoDOM> getObjetoDOMAtributeByType(TipoObjetoDOM tipoPadre,
			TipoObjetoDOM tipoHijo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addObjetoDOMAttributeByType(ObjetoDOM padre, ObjetoDOM hijo,
			User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteObjetoDOMAttributeByType(ObjetoDOM padre,
			ObjetoDOM hijo, User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ObjetoDOM> getStudentObjetoDOMAtributeByType(
			TipoObjetoDOM tipoPadre, TipoObjetoDOM tipoHijo, User user,
			Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addStudentObjetoDOMAttributeByType(ObjetoDOM padre,
			ObjetoDOM hijo, User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteStudentObjetoDOMAttributeByType(ObjetoDOM padre,
			ObjetoDOM hijo, User user, Group groupA) {
		// TODO Auto-generated method stub
		return null;
	}
}
