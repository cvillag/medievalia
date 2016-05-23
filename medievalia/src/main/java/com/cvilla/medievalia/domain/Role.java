package com.cvilla.medievalia.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idRol")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rol;
	
	@Column(name="nombreRol")
	private String nombreRol;

	public Role(int rol, String nombreRol) {
		super();
		this.rol = rol;
		this.nombreRol = nombreRol;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
}
