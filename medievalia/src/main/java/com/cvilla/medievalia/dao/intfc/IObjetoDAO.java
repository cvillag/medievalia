package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.InstanciaAtributoComplejo;
import com.cvilla.medievalia.domain.InstanciaAtributoSencillo;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.InstanciaObjeto;
import com.cvilla.medievalia.domain.TipoAtributoComplejo;
import com.cvilla.medievalia.domain.TipoObjeto;
import com.cvilla.medievalia.domain.User;

public interface IObjetoDAO {
	public List<TipoObjeto> getObjectTypeList();
	public TipoObjeto getObjectType(int idType);
	public List<InstanciaObjeto> getObjectListByTipe(TipoObjeto tipo);
	public InstanciaObjeto getObjectInstance(TipoObjeto tipo, int id);
	public List<InstanciaAtributoSencillo> getAtributosSencillos(TipoObjeto tipo,int id);
	public List<InstanciaAtributoComplejo> getAtributosComplejos(TipoObjeto tipo, int id);
	public String createObjectInstance(InstanciaObjeto o);
	public List<TipoAtributoComplejo> getTiposAtributosCompleos(TipoObjeto tipo);
	public String addComplexAttribute(InstanciaAtributoComplejo ao, int padre);
	public InstanciaAtributoComplejo getAtributoComplejo(int tipoDOM, int padre,int tipoHijo, int hijo);
	public InstanciaAtributoComplejo getAtributoComplejoNotVal(int tipoDOM, int padre,int tipoHijo, int hijo);
	public List<InstanciaAtributoComplejo> getAtributosComplejosNotVal(TipoObjeto tipo, int id);
	public String remAtributoComplejo(InstanciaAtributoComplejo acd, int padre);
	public String updateSimpleAttributes(InstanciaObjeto obj);
	public InstanciaObjeto getObjectByName(TipoObjeto tipo, String nombre);
	public String renameObject(TipoObjeto tipo, int id, String nombre);
	public String deleteObjetoDOM(InstanciaObjeto obj);
	public List<InstanciaObjeto> getTeachersObjetoDOMList(TipoObjeto tipo,	Group groupA);
	public InstanciaObjeto getObjectInstanceNotVal(TipoObjeto tipo, int id);
	public List<InstanciaAtributoComplejo> getAtributosComplejosNoVal(TipoObjeto tipo, int id);
	public String validateAtributoComplejo(int tipoDOM, int idPadre,int tipoHijo, int idHijo, String textV);
	public String commentAtributoComplejoNoVal(int tipoDOM, int idPadre,int tipoHijo, int idHijo, String textV);
	public String validateObjectInstance(InstanciaObjeto obj, String text);
	public String commentObjectInstance(InstanciaObjeto obj, String text);
	public List<InstanciaObjeto> getStudentObjetoDOMList(TipoObjeto tipo, Group groupA, User user);
	public boolean atributoSimpleObjetoExists(int idAtributo, int tipoDOM,int subtipo);
	public String setObjectTextReaded(InstanciaObjeto obj);
	public int getRelacionForComplexAttribute(InstanciaObjeto obj, int pag);
	public boolean isConFecha(int tipoDOM, int tipoH);
	public String updateComplexAttribute(InstanciaAtributoComplejo ao,int idInstPadre);
	public boolean hasRelationObject(int tipoDOM, int tipoH);
	public String setComplexAttributeTextReaded(int tipoDOM, int tipoHijo,int idPadre, int idHijo);
	public int getNumUnvalidatedInstances(int tipo, int group);
	public int getNumValidatedInstancesWithUnvalidatedAC(int tipo, int group);
	public int getNumStudentsUnvalidatedInstances(int tipo, int group);
	public List<InstanciaObjeto> getObjectListByTipeFilter(TipoObjeto tipo,List<InstanciaAtributoComplejo> filtros);
	public int numAtributosSencillos(int tipo);
}
