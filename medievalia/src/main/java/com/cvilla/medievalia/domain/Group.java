package com.cvilla.medievalia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="group")
public class Group {

	@Id
	@Column(name="idGrupo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGrupo;
	
	@Column(name="director")
	private int director;
	
	@Column(name="name")
	private String name;
	
	public Group(int profesor, String nombre) {
		super();
		this.director = profesor;
		this.name = nombre;
	}
	public Group(int idGrupo, int director, String name) {
		super();
		this.idGrupo = idGrupo;
		this.director = director;
		this.name = name;
	}
	public Group() {
		super();
	}
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	public int getDirector() {
		return director;
	}
	public void setDirector(int director) {
		this.director = director;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
