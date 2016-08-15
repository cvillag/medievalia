package com.cvilla.medievalia.domain;

public class SubTema {

	private int idSubtema;
	private int idTema;
	private String nombreSubtema;
	private String nombreTema;
	
	public SubTema(int idSubtema, int idTema, String nombreSubtema,
			String nombreTema) {
		super();
		this.idSubtema = idSubtema;
		this.idTema = idTema;
		this.nombreSubtema = nombreSubtema;
		this.nombreTema = nombreTema;
	}
	
	public SubTema() {
		super();
	}

	public int getIdSubtema() {
		return idSubtema;
	}
	public void setIdSubtema(int idSubtema) {
		this.idSubtema = idSubtema;
	}
	public int getIdTema() {
		return idTema;
	}
	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}
	public String getNombreSubtema() {
		return nombreSubtema;
	}
	public void setNombreSubtema(String nombreSubtema) {
		this.nombreSubtema = nombreSubtema;
	}
	public String getNombreTema() {
		return nombreTema;
	}
	public void setNombreTema(String nombreTema) {
		this.nombreTema = nombreTema;
	}
	
	
}
