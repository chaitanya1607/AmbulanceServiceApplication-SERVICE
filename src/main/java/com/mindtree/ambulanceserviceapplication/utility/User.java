package com.mindtree.ambulanceserviceapplication.utility;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author priya
 *
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String password;
	private String roles;
	private boolean active;

	public User() {
		super();
	}

	public User(String userName, String password, String roles, boolean active) {
		super();
		this.userName = userName;
		this.password = password;
		this.roles = roles;
		this.active = active;
	}

	public User(User user) {
		super();
		this.id = user.id;
		this.userName = user.userName;
		this.password = user.password;
		this.roles = user.roles;
		this.active = user.active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
