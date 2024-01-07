package com.ubilink.model;

// Generated 26 Apr, 2014 4:44:10 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Mapusercoupon generated by hbm2java
 */
public class Mapusercoupon implements java.io.Serializable {

	private int id;
	private Coupon coupon;
	private User user;
	private int dealStatus;
	private Date createdOn;

	public Mapusercoupon() {
	}

	public Mapusercoupon(int id, Coupon coupon, User user, int dealStatus) {
		this.id = id;
		this.coupon = coupon;
		this.user = user;
		this.dealStatus = dealStatus;
	}

	public Mapusercoupon(int id, Coupon coupon, User user, int dealStatus,
			Date createdOn) {
		this.id = id;
		this.coupon = coupon;
		this.user = user;
		this.dealStatus = dealStatus;
		this.createdOn = createdOn;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Coupon getCoupon() {
		return this.coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getDealStatus() {
		return this.dealStatus;
	}

	public void setDealStatus(int dealStatus) {
		this.dealStatus = dealStatus;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
