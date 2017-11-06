package com.cvilla.medievalia.utils;

import java.util.List;

import com.cvilla.medievalia.domain.InstanciaAtributoSencilloDOM;
import com.cvilla.medievalia.domain.InstanciaObjetoDOM;

public class ListaAtributoSimple {
	
	private InstanciaAtributoSencilloDOM atributo;
	private List<InstanciaObjetoDOM> disponibles;
	
	
	
	public ListaAtributoSimple() {
		super();
	}
	public List<InstanciaObjetoDOM> getDisponibles() {
		return disponibles;
	}
	public void setDisponibles(List<InstanciaObjetoDOM> disponibles) {
		this.disponibles = disponibles;
	}
	public InstanciaAtributoSencilloDOM getAtributo() {
		return atributo;
	}
	public void setAtributo(InstanciaAtributoSencilloDOM atributo) {
		this.atributo = atributo;
	}
}
