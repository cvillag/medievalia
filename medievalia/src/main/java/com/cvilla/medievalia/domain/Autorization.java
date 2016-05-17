package com.cvilla.medievalia.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="autorization")
public class Autorization implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idRol")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rol;
	@Id
	@Column(name="idAction")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int action;

	
	public Autorization(int rol, int action) {
		super();
		this.rol = rol;
		this.action = action;
	}
	public Autorization() {
		super();
	}
	public int getRol() {
		return rol;
	}
	public void setRol(int rol) {
		this.rol = rol;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	
	

}
