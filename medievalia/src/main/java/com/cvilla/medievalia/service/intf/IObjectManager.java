package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.InstanciaAtributoComplejoDOM;
import com.cvilla.medievalia.domain.InstanciaAtributoSencilloDOM;
import com.cvilla.medievalia.domain.InstanciaObjetoDOM;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;

@Component
public interface IObjectManager extends Serializable{
	
	//Lista de objetosDOM diferentes y sus atributos
	public List<TipoObjetoDOM> getTiposObjetosDOM();
	public TipoObjetoDOM getTipoObjetoDOM(int idType);
	/**/public List<TipoObjetoDOM> getAtributosCObjetoDOM(TipoObjetoDOM tipo);
	/**/public List<InstanciaAtributoSencilloDOM> getAtributosSObjetoDOM(TipoObjetoDOM tipo);
	
	public List<TipoAtributoComplejoDOM> getTiposAtributosCompleos(TipoObjetoDOM tipo);
	
	//Lista de objetos completa, ACCION 1
	public InstanciaObjetoDOM getObjetoDOM(TipoObjetoDOM tipo, int id);
	
	//Lista de objetos completa, ACCION 1
	public InstanciaObjetoDOM getObjetoDOMUnvalidated(TipoObjetoDOM tipo, int id, Group g, User u);
	
	//Lista de objetos completa, ACCION 1
	public List<InstanciaObjetoDOM> getObjetoDOMListByType(TipoObjetoDOM tipo);
	//Crear objeto, ACCION 4
	public String addObjetoDOM(TipoObjetoDOM tipo, InstanciaObjetoDOM o, Group groupA, User user);
	//Modificar objeto validado, ACCION 8
	public String renameObjetoDOM(TipoObjetoDOM tipo, String nombre, int id, User user, Group groupA);
	//Modificar objeto no validado propio, ACCION 10
	/**/public String renameObjetoDOMOwn(TipoObjetoDOM tipo, String nombre, int id, User user, Group groupA);
	//Eliminar objeto validado, ACCION 5
	public String deleteObjetoDOM(InstanciaObjetoDOM obj, User user, Group groupA);
	
	//Lista de objetos sin validar propios, ACCION 3
	/**/public List<InstanciaObjetoDOM> getStudentObjetoDOMList(TipoObjetoDOM tipo, User user);
	//Eliminar objeto no validado propio, ACCION 7
	public String deleteObjetoDOMOwn(InstanciaObjetoDOM obj, User user, Group groupA);
	
	//Lista de objetos sin validar, ACCION 2
	public List<InstanciaObjetoDOM> getTeachersObjetoDOMList(TipoObjetoDOM tipo,User user, Group groupA);
	//Validar objeto, ACCION 11
	/**/public String validateObjetoDOM(TipoObjetoDOM tipo, int id, User user, Group group, int val, String text);
	
	public String validateAtributoC(int idHijo, int tipoHijo, int idPadre, TipoObjetoDOM tipo, User user, Group groupA, String textV, int val);
	
	//Ver lista de usuarios con objetos por validar, ACCION 2
	/**/public List<User> getUsersToValidateObjectDOMByGroup(User teacher, Group group, TipoObjetoDOM tipo);
	//Ver número de usuarios con objetos por validar, ACCION 2
	/**/public int getNumUsersToValidateByGroup(User teacher, Group group, TipoObjetoDOM tipo);
	//Ver número de objetos por validar por usuario, ACCION 2
	/**/public int getNumObjetoDOMToValidateByUser(User teacher, Group group, TipoObjetoDOM tipo);
	
	//Ver lista de objetosDOM-atributo de un objetoDOM concreto, ACCION 1
	/**/public List<InstanciaObjetoDOM> getObjetoDOMAtributeByType(TipoObjetoDOM tipoPadre, TipoObjetoDOM tipoHijo);
	//Añadir objetoDOM-atributo de un objetoDOM concreto, ACCION 8
	/**/public String addObjetoDOMAttributeByType(int padre, int hijo, TipoObjetoDOM tipoP, int tipoH, int val, User user, Group groupA);
	//Eliminar objetoDOM-atributo de un objetoDOM concreto, ACCION 8
	/**/public String deleteObjetoDOMAttributeByType(int padre, int hijo, TipoObjetoDOM tipoP, int tipoH, int val, User user, Group groupA);
	
	//Ver lista de objetosDOM-atributo de un objetoDOM concreto sin validar, ACCION 3
	/**/public List<InstanciaObjetoDOM> getStudentObjetoDOMAtributeByType(TipoObjetoDOM tipoPadre, TipoObjetoDOM tipoHijo, User user, Group groupA);
	//Añadir objetoDOM-atributo de un objetoDOM concreto, ACCION 10
	/**/public String addStudentObjetoDOMAttributeByType(InstanciaObjetoDOM padre, InstanciaObjetoDOM hijo, User user, Group groupA);
	//Eliminar objetoDOM-atributo de un objetoDOM concreto, ACCION 10
	/**/public String deleteStudentObjetoDOMAttributeByType(InstanciaObjetoDOM padre, InstanciaObjetoDOM hijo, User user, Group groupA);
	
	//Lista de atributos complejos disponibles para una instancia de objeto
	public List<InstanciaAtributoComplejoDOM> getAtributosCDisponiblesObjetoDOM(	TipoObjetoDOM tipo, InstanciaObjetoDOM obj, int pag);
	
	public List<InstanciaAtributoComplejoDOM> getAtributosCPorTipo(InstanciaObjetoDOM obj, int pag);
	public String modifySimpleAttribute(InstanciaObjetoDOM obj);
	
	public List<InstanciaObjetoDOM> fillUsers(List<InstanciaObjetoDOM> l);
	public Map<Integer, Integer> getBadgesFromObject(InstanciaObjetoDOM obj);
	
}
