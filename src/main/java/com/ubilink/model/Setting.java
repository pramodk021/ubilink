package com.ubilink.model;

// Generated 26 Apr, 2014 4:44:10 PM by Hibernate Tools 3.4.0.CR1

/**
 * Setting generated by hbm2java
 */
public class Setting implements java.io.Serializable {

	private int id;
	private Usermode usermode;
	private User user;
	private Boolean isNotificationActive;

	public Setting() {
	}

	public Setting(int id, User user) {
		this.id = id;
		this.user = user;
	}

	public Setting(int id, Usermode usermode, User user,
			Boolean isNotificationActive) {
		this.id = id;
		this.usermode = usermode;
		this.user = user;
		this.isNotificationActive = isNotificationActive;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usermode getUsermode() {
		return this.usermode;
	}

	public void setUsermode(Usermode usermode) {
		this.usermode = usermode;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsNotificationActive() {
		return this.isNotificationActive;
	}

	public void setIsNotificationActive(Boolean isNotificationActive) {
		this.isNotificationActive = isNotificationActive;
	}

}