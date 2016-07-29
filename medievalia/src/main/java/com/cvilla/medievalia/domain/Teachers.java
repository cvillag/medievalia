package com.cvilla.medievalia.domain;

public class Teachers {
	
	private String name;
	private int idGroup;
	private int idTeacher;
	private int idDirector;
	private String directorName;
	
	public Teachers(String name, int idGroup, int idTeacher) {
		super();
		this.name = name;
		this.idGroup = idGroup;
		this.idTeacher = idTeacher;
	}

	public Teachers() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public int getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(int idTeacher) {
		this.idTeacher = idTeacher;
	}

	public int getIdDirector() {
		return idDirector;
	}

	public void setIdDirector(int idDirector) {
		this.idDirector = idDirector;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
}
