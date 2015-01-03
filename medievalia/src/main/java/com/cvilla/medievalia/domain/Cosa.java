package com.cvilla.medievalia.domain;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;


public class Cosa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	
	private String nombre;
	@Autowired
	public Cosa(int id, String nombre){
		this.id = id;
		this.nombre = nombre;
	}
	
	public Cosa(String n){
		//this.id = i;
		this.nombre = n;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Nombre: "+ this.nombre);
		sb.append("ID: " + this.id);
		return sb.toString();
	}

}
