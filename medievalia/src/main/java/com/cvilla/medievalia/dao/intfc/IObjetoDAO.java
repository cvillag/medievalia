package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.AtributoComplejoDOM;
import com.cvilla.medievalia.domain.AtributoSencilloDOM;
import com.cvilla.medievalia.domain.ObjetoDOM;
import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;

public interface IObjetoDAO {
	public List<TipoObjetoDOM> getObjectTypeList();
	public TipoObjetoDOM getObjectType(int idType);
	public List<ObjetoDOM> getObjectListByTipe(TipoObjetoDOM tipo);
	public ObjetoDOM getObjectInstance(TipoObjetoDOM tipo, int id);
	public List<AtributoSencilloDOM> getAtributosSencillos(TipoObjetoDOM tipo,int id);
	public List<AtributoComplejoDOM> getAtributosComplejos(TipoObjetoDOM tipo, int id);
	public String createObjectInstance(ObjetoDOM o);
	public List<TipoAtributoComplejoDOM> getTiposAtributosCompleos(TipoObjetoDOM tipo);
	public String addComplexAttribute(AtributoComplejoDOM ao, int padre);
	public AtributoComplejoDOM getAtributoComplejo(int tipoDOM, int padre,int tipoHijo, int hijo);
	public AtributoComplejoDOM getAtributoComplejoNotVal(int tipoDOM, int padre,int tipoHijo, int hijo);
	public List<AtributoComplejoDOM> getAtributosComplejosNotVal(TipoObjetoDOM tipo, int id);
	public String remAtributoComplejo(AtributoComplejoDOM acd, int padre);
	public String updateSimpleAttributes(ObjetoDOM obj);
	public ObjetoDOM getObjectByName(TipoObjetoDOM tipo, String nombre);
	public String renameObject(TipoObjetoDOM tipo, int id, String nombre);
}
