package com.cvilla.medievalia.domain;

public class Tema {

	private int idTema;
	private String nombre;
	private int idGroup;
	private String nombreGrupo;
	private int numSubtemas;
	
	public Tema(int idTema, String nombre, int idGroup, String nombreGrupo) {
		super();
		this.idTema = idTema;
		this.nombre = nombre;
		this.idGroup = idGroup;
		this.nombreGrupo = nombreGrupo;
	}
	public Tema() {
		super();
	}
	public int getIdTema() {
		return idTema;
	}
	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}
	public String getNombreGrupo() {
		return nombreGrupo;
	}
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}
	public int getNumSubtemas() {
		return numSubtemas;
	}
	public void setNumSubtemas(int numSubtemas) {
		this.numSubtemas = numSubtemas;
	}
	
	
}
