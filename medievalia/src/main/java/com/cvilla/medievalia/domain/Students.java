package com.cvilla.medievalia.domain;

public class Students {

	private int idGroup;
	private int idDirector;
	private String groupName;
	private int idStudent;
	private String directorName;
	
	public Students(int idGroup, int idDirector, String groupName,
			int idStudent, String directorName) {
		super();
		this.idGroup = idGroup;
		this.idDirector = idDirector;
		this.groupName = groupName;
		this.idStudent = idStudent;
		this.directorName = directorName;
	}
	public Students() {
		super();
	}
	public int getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}
	public int getIdDirector() {
		return idDirector;
	}
	public void setIdDirector(int idDirector) {
		this.idDirector = idDirector;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
}
