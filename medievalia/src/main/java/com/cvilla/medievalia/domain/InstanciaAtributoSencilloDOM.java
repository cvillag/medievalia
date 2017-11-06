package com.cvilla.medievalia.domain;

public class InstanciaAtributoSencilloDOM {
	private int tipoAtributo;
	private Object valor;
	private String nombreTipoAtributo;
	private int idAtributo;
	private int subtipo;
	
	public InstanciaAtributoSencilloDOM() {
		super();
	}

	public int getTipoAtributo() {
		return tipoAtributo;
	}

	public void setTipoAtributo(int tipo) {
		this.tipoAtributo = tipo;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public String getNombreTipoAtributo() {
		return nombreTipoAtributo;
	}

	public void setNombreTipoAtributo(String nombreTipoAtributo) {
		this.nombreTipoAtributo = nombreTipoAtributo;
	}

	public int getIdAtributo() {
		return idAtributo;
	}

	public void setIdAtributo(int idAtributo) {
		this.idAtributo = idAtributo;
	}

	public int getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(int subtipo) {
		this.subtipo = subtipo;
	}
	
}
