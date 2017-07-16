package com.cvilla.medievalia.domain;

public class Charge {
	private int idCharge;
	private int idCreator;
	private int idGroup;
	private int validado;
	private String nombre;
	public Charge(int idCharge, int idCreator, int idGroup, int validado,
			String nombre) {
		super();
		this.idCharge = idCharge;
		this.idCreator = idCreator;
		this.idGroup = idGroup;
		this.validado = validado;
		this.nombre = nombre;
	}
	
	public Charge() {
		super();
	}

	public int getIdCharge() {
		return idCharge;
	}
	public void setIdCharge(int idCharge) {
		this.idCharge = idCharge;
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
}
