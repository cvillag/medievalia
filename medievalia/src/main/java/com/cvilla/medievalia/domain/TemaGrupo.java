package com.cvilla.medievalia.domain;

public class TemaGrupo {

	private int idGroup;
	private int idTema;
	private String nombreTema;
	private String nombreGrupo;
	
	public TemaGrupo(int idGroup, int idTema, String nombreTema, String nombreGrupo) {
		super();
		this.idGroup = idGroup;
		this.idTema = idTema;
		this.nombreTema = nombreTema;
		this.nombreGrupo = nombreGrupo;
	}
	
	public TemaGrupo() {
		super();
	}

	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public int getIdTema() {
		return idTema;
	}

	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}

	public String getNombreTema() {
		return nombreTema;
	}

	public void setNombreTema(String nombreTema) {
		this.nombreTema = nombreTema;
	}

	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}
}
