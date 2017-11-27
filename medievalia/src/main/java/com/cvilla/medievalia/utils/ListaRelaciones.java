package com.cvilla.medievalia.utils;

import java.util.List;

import com.cvilla.medievalia.domain.InstanciaObjetoDOM;
import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;

public class ListaRelaciones {
	
	private TipoAtributoComplejoDOM ac;
	private List<InstanciaObjetoDOM> li;
	public ListaRelaciones() {
		super();
	}
	public TipoAtributoComplejoDOM getAc() {
		return ac;
	}
	public void setAc(TipoAtributoComplejoDOM ac) {
		this.ac = ac;
	}
	public List<InstanciaObjetoDOM> getLi() {
		return li;
	}
	public void setLi(List<InstanciaObjetoDOM> li) {
		this.li = li;
	}

}
