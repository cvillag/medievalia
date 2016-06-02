package com.cvilla.medievalia.domain;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_long_name")
	private String userLongName;
	
	@Column(name="user_pass")
	private byte[] userPass;
	
	@Column(name="user_role")
	private int userRole;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return userName;
	}

	public void setUser_name(String user_name) {
		this.userName = user_name;
	}

	public String getUser_long_name() {
		return userLongName;
	}

	public void setUser_long_name(String user_long_name) {
		this.userLongName = user_long_name;
	}

	public byte[] getUser_pass() {
		return userPass;
	}

	public void setUser_pass(byte[] user_pass) {
		this.userPass = user_pass;
	}

	public int getUser_role() {
		return userRole;
	}

	public void setUser_role(int user_role) {
		this.userRole = user_role;
	}

}
