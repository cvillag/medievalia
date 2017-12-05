package com.cvilla.medievalia.domain;

public class TipoAtributoComplejoDOM {
	
	private int idTipoPadre;
	private int idTipoHijo;
	private String nombreAtributo;
	private int idTipoRelacion;
	private int conFecha;
	private int conPaginaDoc;
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
	public int getIdTipoRelacion() {
		return idTipoRelacion;
	}
	public void setIdTipoRelacion(int idTipoRelacion) {
		this.idTipoRelacion = idTipoRelacion;
	}
	public int getConFecha() {
		return conFecha;
	}
	public void setConFecha(int conFecha) {
		this.conFecha = conFecha;
	}
	public int getConPaginaDoc() {
		return conPaginaDoc;
	}
	public void setConPaginaDoc(int conPaginaDoc) {
		this.conPaginaDoc = conPaginaDoc;
	}
	public boolean isConPaginaDoc(){
		return this.conPaginaDoc == 1;
	}
}
