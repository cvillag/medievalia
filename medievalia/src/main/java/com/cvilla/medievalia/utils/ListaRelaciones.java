package com.cvilla.medievalia.utils;

import java.util.List;

import com.cvilla.medievalia.domain.InstanciaObjeto;
import com.cvilla.medievalia.domain.TipoAtributoComplejo;

public class ListaRelaciones {
	
	private TipoAtributoComplejo ac;
	private List<InstanciaObjeto> li;
	public ListaRelaciones() {
		super();
	}
	public TipoAtributoComplejo getAc() {
		return ac;
	}
	public void setAc(TipoAtributoComplejo ac) {
		this.ac = ac;
	}
	public List<InstanciaObjeto> getLi() {
		return li;
	}
	public void setLi(List<InstanciaObjeto> li) {
		this.li = li;
	}

}
