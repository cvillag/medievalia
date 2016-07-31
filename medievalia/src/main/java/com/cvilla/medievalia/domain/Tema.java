package com.cvilla.medievalia.domain;

public class Tema {

	private int idTema;
	private String nombre;
	
	public Tema(int idTema, String nombre) {
		super();
		this.idTema = idTema;
		this.nombre = nombre;
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
}
