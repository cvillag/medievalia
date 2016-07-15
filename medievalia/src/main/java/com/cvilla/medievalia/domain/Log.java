package com.cvilla.medievalia.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Log {

	private int idUser;
	private String userName;
	private String userLongName;
	private int idAction;
	private String actionName;
	private Timestamp time;
	private int idLog;
	private String description;
	private int success;
	
	public Log() {
		super();
	}
	

	public Log(int idUser, String userName, String userLongName, int idAction,
			String actionName, Timestamp time, int idLog, String description,
			int success) {
		super();
		this.idUser = idUser;
		this.userName = userName;
		this.userLongName = userLongName;
		this.idAction = idAction;
		this.actionName = actionName;
		this.time = time;
		this.idLog = idLog;
		this.description = description;
		this.success = success;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserLongName() {
		return userLongName;
	}


	public void setUserLongName(String userLongName) {
		this.userLongName = userLongName;
	}


	public String getActionName() {
		return actionName;
	}


	public void setActionName(String actionName) {
		this.actionName = actionName;
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
	public  Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
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
