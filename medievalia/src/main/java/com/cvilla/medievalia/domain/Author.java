package com.cvilla.medievalia.domain;

public class Author {
	private int idAutor;
	private int idCreator;
	private String nameCreator;
	private int idGroup;
	private int validado;
	private String nombre;
	public Author(int idAutor, int idCreator, int idGroup, int validado,
			String nombre, String nameCreator) {
		super();
		this.idAutor = idAutor;
		this.idCreator = idCreator;
		this.nameCreator = nameCreator;
		this.idGroup = idGroup;
		this.validado = validado;
		this.nombre = nombre;
	}
	
	public Author() {
		super();
	}

	public int getIdAuthor() {
		return idAutor;
	}
	public void setIdAuthor(int idAuthor) {
		this.idAutor = idAuthor;
	}
	public int getIdCreator() {
		return idCreator;
	}
	public void setIdCreator(int idCreator) {
		this.idCreator = idCreator;
	}
	public int getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}
	public int getValidado() {
		return validado;
	}
	public void setValidado(int validado) {
		this.validado = validado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNameCreator() {
		return nameCreator;
	}

	public void setNameCreator(String nameCreator) {
		this.nameCreator = nameCreator;
	}
}
