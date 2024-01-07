package com.ubilink.model;

// Generated 26 Apr, 2014 4:44:10 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Mapfavourite generated by hbm2java
 */
public class Mapfavourite implements java.io.Serializable {

	private Integer id;
	private Favoritetype favoritetype;
	private User user;
	private Reminder reminder;
	private Integer mapFavouriteEntityId;
	private boolean isActive;
	private Boolean isReminderActivated;
	private Date createdOn;

	public Mapfavourite() {
	}

	public Mapfavourite(User user, boolean isActive) {
		this.user = user;
		this.isActive = isActive;
	}

	public Mapfavourite(Favoritetype favoritetype, User user,
			Reminder reminder, Integer mapFavouriteEntityId, boolean isActive,
			Boolean isReminderActivated, Date createdOn) {
		this.favoritetype = favoritetype;
		this.user = user;
		this.reminder = reminder;
		this.mapFavouriteEntityId = mapFavouriteEntityId;
		this.isActive = isActive;
		this.isReminderActivated = isReminderActivated;
		this.createdOn = createdOn;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Favoritetype getFavoritetype() {
		return this.favoritetype;
	}

	public void setFavoritetype(Favoritetype favoritetype) {
		this.favoritetype = favoritetype;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Reminder getReminder() {
		return this.reminder;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}

	public Integer getMapFavouriteEntityId() {
		return this.mapFavouriteEntityId;
	}

	public void setMapFavouriteEntityId(Integer mapFavouriteEntityId) {
		this.mapFavouriteEntityId = mapFavouriteEntityId;
	}

	public boolean isIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsReminderActivated() {
		return this.isReminderActivated;
	}

	public void setIsReminderActivated(Boolean isReminderActivated) {
		this.isReminderActivated = isReminderActivated;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}