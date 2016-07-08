package com.cvilla.medievalia.domain;

import java.sql.Date;

public class Log {

	private int idUser;
	private int idAction;
	private Date time;
	private int idLog;
	private String description;
	private int success;
	
	public Log() {
		super();
	}
	
	public Log(int idUser, int idAction, Date time, int idLog,
			String description, int success) {
		super();
		this.idUser = idUser;
		this.idAction = idAction;
		this.time = time;
		this.idLog = idLog;
		this.description = description;
		this.success = success;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdAction() {
		return idAction;
	}
	public void setIdAction(int idAction) {
		this.idAction = idAction;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getIdLog() {
		return idLog;
	}
	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	
}
