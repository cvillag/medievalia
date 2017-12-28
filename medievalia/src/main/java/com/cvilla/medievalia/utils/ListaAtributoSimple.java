package com.cvilla.medievalia.utils;

import java.util.List;

import com.cvilla.medievalia.domain.InstanciaAtributoSencillo;
import com.cvilla.medievalia.domain.InstanciaObjeto;

public class ListaAtributoSimple {
	
	private InstanciaAtributoSencillo atributo;
	private List<InstanciaObjeto> disponibles;
	
	
	
	public ListaAtributoSimple() {
		super();
	}
	public List<InstanciaObjeto> getDisponibles() {
		return disponibles;
	}
	public void setDisponibles(List<InstanciaObjeto> disponibles) {
		this.disponibles = disponibles;
	}
	public InstanciaAtributoSencillo getAtributo() {
		return atributo;
	}
	public void setAtributo(InstanciaAtributoSencillo atributo) {
		this.atributo = atributo;
	}
}
