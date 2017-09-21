package com.cvilla.medievalia.domain;

public class TipoAtributoComplejoDOM {
	
	private int idTipoPadre;
	private int idTipoHijo;
	private String nombreAtributo;
	public TipoAtributoComplejoDOM() {
		super();
	}
	public int getIdTipoPadre() {
		return idTipoPadre;
	}
	public void setIdTipoPadre(int idTipoPadre) {
		this.idTipoPadre = idTipoPadre;
	}
	public int getIdTipoHijo() {
		return idTipoHijo;
	}
	public void setIdTipoHijo(int idTipoHijo) {
		this.idTipoHijo = idTipoHijo;
	}
	public String getNombreAtributo() {
		return nombreAtributo;
	}
	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}
}
