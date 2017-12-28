package com.cvilla.medievalia.domain;

public class TipoObjeto {
	private int tipoDOM;
	private String nombreDOM;
	
	public TipoObjeto(int tipoDOM, String nombreDOM) {
		super();
		this.tipoDOM = tipoDOM;
		this.nombreDOM = nombreDOM;
	}
	
	public TipoObjeto() {
		super();
	}

	public int getTipoDOM() {
		return tipoDOM;
	}

	public void setTipoDOM(int tipoDOM) {
		this.tipoDOM = tipoDOM;
	}

	public String getNombreDOM() {
		return nombreDOM;
	}

	public void setNombreDOM(String nombreDOM) {
		this.nombreDOM = nombreDOM;
	}

}
