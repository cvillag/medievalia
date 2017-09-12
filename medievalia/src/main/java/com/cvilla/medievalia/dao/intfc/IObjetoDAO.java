package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.ObjetoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;

public interface IObjetoDAO {
	public List<TipoObjetoDOM> getObjectTypeList();
	public TipoObjetoDOM getObjectType(int idType);
	public List<ObjetoDOM> getObjectListByTipe(TipoObjetoDOM tipo);
}
