package com.cvilla.medievalia.dao.intfc;

import java.util.List;

import com.cvilla.medievalia.domain.ObjetoDOM;
import com.cvilla.medievalia.domain.Place;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;

public interface IObjetoDAO {
	public List<TipoObjetoDOM> getObjectTypeList();
	public TipoObjetoDOM getObjectType(int idType);
	public List<ObjetoDOM> getObjectListByTipe(TipoObjetoDOM tipo);
}
