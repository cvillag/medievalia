package com.cvilla.medievalia.service.intf;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.InstanciaAtributoComplejo;
import com.cvilla.medievalia.domain.InstanciaAtributoSencillo;
import com.cvilla.medievalia.domain.InstanciaObjeto;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.TipoAtributoComplejo;
import com.cvilla.medievalia.domain.TipoObjeto;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.utils.ListaAtributoSimple;
import com.cvilla.medievalia.utils.ListaRelaciones;
import com.cvilla.medievalia.utils.SpecialDate;

@Component
public interface IObjectManager extends Serializable{
	
	//Lista de objetosDOM diferentes y sus atributos
	public List<TipoObjeto> getTiposObjetosDOM();
	
	public TipoObjeto getTipoObjetoDOM(int idType);
	/**/public List<TipoObjeto> getAtributosCObjetoDOM(TipoObjeto tipo);
	/**/public List<InstanciaAtributoSencillo> getAtributosSObjetoDOM(TipoObjeto tipo);
	
	public List<TipoAtributoComplejo> getTiposAtributosCompleos(TipoObjeto tipo);
	
	//Lista de objetos completa, ACCION 1
	public InstanciaObjeto getObjetoDOM(TipoObjeto tipo, int id);
	
	//Lista de objetos completa, ACCION 1
	public InstanciaObjeto getObjetoDOMUnvalidated(TipoObjeto tipo, int id, Group g, User u);
	
	//Lista de objetos completa, ACCION 1
	public List<InstanciaObjeto> getObjetoDOMListByTypeFilter(TipoObjeto tipo, HttpServletRequest req);
	public List<InstanciaObjeto> getObjetoDOMListByType(TipoObjeto tipo);
	//Crear objeto, ACCION 4
	public String addObjetoDOM(TipoObjeto tipo, InstanciaObjeto o, Group groupA, User user);
	//Modificar objeto validado, ACCION 8
	public String renameObjetoDOM(TipoObjeto tipo, String nombre, int id, User user, Group groupA);
	//Modificar objeto no validado propio, ACCION 10
	/**/public String renameObjetoDOMOwn(TipoObjeto tipo, String nombre, int id, User user, Group groupA);
	//Eliminar objeto validado, ACCION 5
	public String deleteObjetoDOM(InstanciaObjeto obj, User user, Group groupA);
	
	//Lista de objetos sin validar propios, ACCION 3
	/**/public List<InstanciaObjeto> getStudentObjetoDOMList(TipoObjeto tipo, User user, Group groupA);
	//Eliminar objeto no validado propio, ACCION 7
	public String deleteObjetoDOMOwn(InstanciaObjeto obj, User user, Group groupA);
	
	//Lista de objetos sin validar, ACCION 2
	public List<InstanciaObjeto> getTeachersObjetoDOMList(TipoObjeto tipo,User user, Group groupA);
	//Validar objeto, ACCION 11
	/**/public String validateObjetoDOM(TipoObjeto tipo, int id, User user, Group group, int val, String text);
	
	public String validateAtributoC(int idHijo, int tipoHijo, int idPadre, TipoObjeto tipo, User user, Group groupA, String textV, int val);
	
	//Ver lista de usuarios con objetos por validar, ACCION 2
	/**/public List<User> getUsersToValidateObjectDOMByGroup(User teacher, Group group, TipoObjeto tipo);
	//Ver número de usuarios con objetos por validar, ACCION 2
	/**/public int getNumUsersToValidateByGroup(User teacher, Group group, TipoObjeto tipo);
	//Ver número de objetos por validar por usuario, ACCION 2
	/**/public int getNumObjetoDOMToValidateByUser(User teacher, Group group, TipoObjeto tipo);
	
	//Ver lista de objetosDOM-atributo de un objetoDOM concreto, ACCION 1
	/**/public List<InstanciaObjeto> getObjetoDOMAtributeByType(TipoObjeto tipoPadre, TipoObjeto tipoHijo);
	//Añadir objetoDOM-atributo de un objetoDOM concreto, ACCION 8
	/**/public String addObjetoDOMAttributeByType(int padre, int hijo, TipoObjeto tipoP, int tipoH, int val, User user, Group groupA, int selRel, SpecialDate inicio, SpecialDate fin, String paginaDoc);
	//Eliminar objetoDOM-atributo de un objetoDOM concreto, ACCION 8
	/**/public String deleteObjetoDOMAttributeByType(int padre, int hijo, TipoObjeto tipoP, int tipoH, int val, User user, Group groupA);
	
	//Ver lista de objetosDOM-atributo de un objetoDOM concreto sin validar, ACCION 3
	/**/public List<InstanciaObjeto> getStudentObjetoDOMAtributeByType(TipoObjeto tipoPadre, TipoObjeto tipoHijo, User user, Group groupA);
	//Añadir objetoDOM-atributo de un objetoDOM concreto, ACCION 10
	/**/public String addStudentObjetoDOMAttributeByType(InstanciaObjeto padre, InstanciaObjeto hijo, User user, Group groupA);
	//Eliminar objetoDOM-atributo de un objetoDOM concreto, ACCION 10
	/**/public String deleteStudentObjetoDOMAttributeByType(InstanciaObjeto padre, InstanciaObjeto hijo, User user, Group groupA);
	
	//Lista de atributos complejos disponibles para una instancia de objeto
	public List<InstanciaAtributoComplejo> getAtributosCDisponiblesObjetoDOM(	TipoObjeto tipo, InstanciaObjeto obj, int pag);
	
	public List<InstanciaAtributoComplejo> getAtributosCPorTipo(InstanciaObjeto obj, int pag);
	public String modifySimpleAttribute(InstanciaObjeto obj,Group g, User u);
	
	public List<InstanciaObjeto> fillUsers(List<InstanciaObjeto> l);
	public Map<Integer, Integer> getBadgesFromObject(InstanciaObjeto obj);
	public List<ListaAtributoSimple> getListaDisponibleAtributoSimpleObjeto(InstanciaObjeto obj);
	
	//Desde la lista de atributos complejos adquiere la lista de instancias de relación para cada atributo complejo
	public List<ListaRelaciones> getRelaciones(List<TipoAtributoComplejo> ac);
	public String setObjectTextReaded(int idInstancia, User user, TipoObjeto tipo, Group groupA);
	public int getTypeRelacionForComplexAttribute(InstanciaObjeto obj,int pag);
	public InstanciaAtributoComplejo getComplexAttribute(TipoObjeto tipo,int idTipoHijo, int idInstPadre, int idInstHijo, Group groupA, User user);
	public String updateObjetoDOMAttributeByType(int idInstPadre,int idInstHijo, TipoObjeto tipo, int idTipoAttr, int val,User user, Group groupA, int selRel, SpecialDate inicio,SpecialDate fin, String paginaDoc);
	public InstanciaAtributoComplejo getComplexAttributeNotVal(TipoObjeto tipo,	int idTipoHijo, int idInstPadre, int idInstHijo, Group g, User u);
	public boolean isConFecha(int tipoDOM, int idTipoAttr);
	public String setComplexAttributeTextReaded(int idPadre, int idHijo,int tipoHijo, User user, TipoObjeto tipo, Group groupA);
	public boolean isConPag(int tipoDOM, int idTipoAttr);
	
	public Map<Integer,Integer> getStatisticsToValidate(Group g);
	public Map<Integer,Integer> getStatisticsUsersToValidate(Group g);
	public Map<Integer,Integer> getUserStatisticsObjetsToVal(User u, Group g);
	public Map<Integer,Integer> getUserStatisticsObjetsTotal(User u, Group g);
	public Map<Integer,Integer> getUserStatisticsObjetsToValAC(User u, Group g);
	public Map<Integer,Integer> getUserStatisticsObjetsTotalAC(User u, Group g);
	public Object getStatisticsTotalInstancesPerType();
	
	public boolean hasSimpleAttributes(TipoObjeto t);

	public InstanciaObjeto getObjetoDOM(TipoObjeto tipo, String nombre);
}
