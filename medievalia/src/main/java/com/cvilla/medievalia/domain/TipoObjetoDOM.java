package com.cvilla.medievalia.domain;

public class TipoObjetoDOM {
	private int tipoDOM;
	private String nombreDOM;
	
	public TipoObjetoDOM(int tipoDOM, String nombreDOM) {
		super();
		this.tipoDOM = tipoDOM;
		this.nombreDOM = nombreDOM;
	}
	
	public TipoObjetoDOM() {
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
