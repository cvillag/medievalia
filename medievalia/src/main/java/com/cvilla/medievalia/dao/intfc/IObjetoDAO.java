package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.InstanciaAtributoComplejoDOM;
import com.cvilla.medievalia.domain.InstanciaAtributoSencilloDOM;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.InstanciaObjetoDOM;
import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;

public interface IObjetoDAO {
	public List<TipoObjetoDOM> getObjectTypeList();
	public TipoObjetoDOM getObjectType(int idType);
	public List<InstanciaObjetoDOM> getObjectListByTipe(TipoObjetoDOM tipo);
	public InstanciaObjetoDOM getObjectInstance(TipoObjetoDOM tipo, int id);
	public List<InstanciaAtributoSencilloDOM> getAtributosSencillos(TipoObjetoDOM tipo,int id);
	public List<InstanciaAtributoComplejoDOM> getAtributosComplejos(TipoObjetoDOM tipo, int id);
	public String createObjectInstance(InstanciaObjetoDOM o);
	public List<TipoAtributoComplejoDOM> getTiposAtributosCompleos(TipoObjetoDOM tipo);
	public String addComplexAttribute(InstanciaAtributoComplejoDOM ao, int padre);
	public InstanciaAtributoComplejoDOM getAtributoComplejo(int tipoDOM, int padre,int tipoHijo, int hijo);
	public InstanciaAtributoComplejoDOM getAtributoComplejoNotVal(int tipoDOM, int padre,int tipoHijo, int hijo);
	public List<InstanciaAtributoComplejoDOM> getAtributosComplejosNotVal(TipoObjetoDOM tipo, int id);
	public String remAtributoComplejo(InstanciaAtributoComplejoDOM acd, int padre);
	public String updateSimpleAttributes(InstanciaObjetoDOM obj);
	public InstanciaObjetoDOM getObjectByName(TipoObjetoDOM tipo, String nombre);
	public String renameObject(TipoObjetoDOM tipo, int id, String nombre);
	public String deleteObjetoDOM(InstanciaObjetoDOM obj);
	public List<InstanciaObjetoDOM> getTeachersObjetoDOMList(TipoObjetoDOM tipo,	Group groupA);
}
