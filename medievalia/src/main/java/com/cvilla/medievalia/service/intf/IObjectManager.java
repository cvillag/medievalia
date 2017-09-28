package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.AtributoComplejoDOM;
import com.cvilla.medievalia.domain.AtributoSencilloDOM;
import com.cvilla.medievalia.domain.ObjetoDOM;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;

@Component
public interface IObjectManager extends Serializable{
	
	//Lista de objetosDOM diferentes y sus atributos, ACCION 1
	public List<TipoObjetoDOM> getTiposObjetosDOM();
	public TipoObjetoDOM getTipoObjetoDOM(int idType);
	/**/public List<TipoObjetoDOM> getAtributosCObjetoDOM(TipoObjetoDOM tipo);
	/**/public List<AtributoSencilloDOM> getAtributosSObjetoDOM(TipoObjetoDOM tipo);
	
	public List<TipoAtributoComplejoDOM> getTiposAtributosCompleos(TipoObjetoDOM tipo);
	
	//Lista de objetos completa, ACCION 1
	public ObjetoDOM getObjetoDOM(TipoObjetoDOM tipo, int id);
	//Lista de objetos completa, ACCION 1
	public List<ObjetoDOM> getObjetoDOMListByType(TipoObjetoDOM tipo);
	//Crear objeto, ACCION 4
	public String addObjetoDOM(TipoObjetoDOM tipo, ObjetoDOM o, Group groupA, User user);
	//Modificar objeto validado, ACCION 8
	/**/public String renameObjetoDOM(TipoObjetoDOM tipo, String nombre, int id, User user, Group groupA);
	//Modificar objeto no validado propio, ACCION 10
	/**/public String renameObjetoDOMOwn(TipoObjetoDOM tipo, String nombre, int id, User user, Group groupA);
	//Eliminar objeto validado, ACCION 5
	/**/public String deleteObjetoDOM(TipoObjetoDOM tipo, int id, User user, Group groupA);
	
	//Lista de objetos sin validar propios, ACCION 3
	/**/public List<ObjetoDOM> getStudentObjetoDOMList(TipoObjetoDOM tipo, User user);
	//Eliminar objeto no validado propio, ACCION 7
	/**/public String deleteObjetoDOMOwn(TipoObjetoDOM tipo, int id, User user, Group groupA);
	
	//Lista de objetos sin validar, ACCION 2
	/**/public List<ObjetoDOM> getTeachersObjetoDOMList(TipoObjetoDOM tipo, User user, Group groupA);
	//Validar objeto, ACCION 11
	/**/public String validateObjetoDOM(TipoObjetoDOM tipo, int id, User user, Group group);
	
	//Ver lista de usuarios con objetos por validar, ACCION 2
	/**/public List<User> getUsersToValidateObjectDOMByGroup(User teacher, Group group, TipoObjetoDOM tipo);
	//Ver número de usuarios con objetos por validar, ACCION 2
	/**/public int getNumUsersToValidateByGroup(User teacher, Group group, TipoObjetoDOM tipo);
	//Ver número de objetos por validar por usuario, ACCION 2
	/**/public int getNumObjetoDOMToValidateByUser(User teacher, Group group, TipoObjetoDOM tipo);
	
	//Ver lista de objetosDOM-atributo de un objetoDOM concreto, ACCION 1
	/**/public List<ObjetoDOM> getObjetoDOMAtributeByType(TipoObjetoDOM tipoPadre, TipoObjetoDOM tipoHijo);
	//Añadir objetoDOM-atributo de un objetoDOM concreto, ACCION 8
	/**/public String addObjetoDOMAttributeByType(int padre, int hijo, TipoObjetoDOM tipoP, int tipoH, int val, User user, Group groupA);
	//Eliminar objetoDOM-atributo de un objetoDOM concreto, ACCION 8
	/**/public String deleteObjetoDOMAttributeByType(int padre, int hijo, TipoObjetoDOM tipoP, int tipoH, int val, User user, Group groupA);
	
	//Ver lista de objetosDOM-atributo de un objetoDOM concreto sin validar, ACCION 3
	/**/public List<ObjetoDOM> getStudentObjetoDOMAtributeByType(TipoObjetoDOM tipoPadre, TipoObjetoDOM tipoHijo, User user, Group groupA);
	//Añadir objetoDOM-atributo de un objetoDOM concreto, ACCION 10
	/**/public String addStudentObjetoDOMAttributeByType(ObjetoDOM padre, ObjetoDOM hijo, User user, Group groupA);
	//Eliminar objetoDOM-atributo de un objetoDOM concreto, ACCION 10
	/**/public String deleteStudentObjetoDOMAttributeByType(ObjetoDOM padre, ObjetoDOM hijo, User user, Group groupA);
	
	//Lista de atributos complejos disponibles para una instancia de objeto
	public List<AtributoComplejoDOM> getAtributosCDisponiblesObjetoDOM(	TipoObjetoDOM tipo, ObjetoDOM obj, int pag);
	
	public List<AtributoComplejoDOM> getAtributosCPorTipo(ObjetoDOM obj, int pag);
}
