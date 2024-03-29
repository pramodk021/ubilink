package com.ubilink.model;

// Generated 26 Apr, 2014 4:44:10 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User generated by hbm2java
 */
@Component
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
public class User implements java.io.Serializable {
	@JsonProperty
	private Integer id;
	@JsonProperty
	private String name;
	@JsonIgnore
	private Usertype usertype;
	@JsonIgnore
	private Place place;
	@JsonIgnore
	private Userstatus userstatus;
	@JsonIgnore
	private String mobile;
	@JsonIgnore
	private String email;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String FBId;
	@JsonIgnore
	private Date createdOn;
	@JsonIgnore
	private Integer createdBy;
	@JsonIgnore
	private Date updatedOn;
	@JsonIgnore
	private Integer updatedBy;
	
	@JsonIgnore
	private Set settings = new HashSet(0);
	@JsonIgnore
	private Set mappeoplesForUserId = new HashSet(0);
	@JsonIgnore
	private Set maployaltypointusers = new HashSet(0);
	@JsonIgnore
	private Set mappeoplesForPeopleId = new HashSet(0);
	@JsonIgnore
	private Set mapfavourites = new HashSet(0);
	@JsonIgnore
	private Set mapreviews = new HashSet(0);
	@JsonIgnore
	private Set coupons = new HashSet(0);
	@JsonIgnore
	private Set mapusercoupons = new HashSet(0);

	public User() {
	}
	

	public User(String mobile,String password,String email,String name,String FBId)
	{
		this.mobile=mobile;
		this.email=email;
		this.name=name;
		this.FBId=FBId;
		this.password=password;
	}
	public User(Usertype usertype, Place place, Userstatus userstatus,
			String name, String mobile, String email, String password,
			String FBId, Date createdOn, Integer createdBy, Date updatedOn,
			Integer updatedBy, Set settings, Set mappeoplesForUserId,
			Set maployaltypointusers, Set mappeoplesForPeopleId,
			Set mapfavourites, Set mapreviews, Set coupons, Set mapusercoupons) {
		this.usertype = usertype;
		this.place = place;
		this.userstatus = userstatus;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.FBId=FBId;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.settings = settings;
		this.mappeoplesForUserId = mappeoplesForUserId;
		this.maployaltypointusers = maployaltypointusers;
		this.mappeoplesForPeopleId = mappeoplesForPeopleId;
		this.mapfavourites = mapfavourites;
		this.mapreviews = mapreviews;
		this.coupons = coupons;
		this.mapusercoupons = mapusercoupons;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usertype getUsertype() {
		return this.usertype;
	}

	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	}

	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Userstatus getUserstatus() {
		return this.userstatus;
	}

	public void setUserstatus(Userstatus userstatus) {
		this.userstatus = userstatus;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Set getSettings() {
		return this.settings;
	}

	public void setSettings(Set settings) {
		this.settings = settings;
	}

	public Set getMappeoplesForUserId() {
		return this.mappeoplesForUserId;
	}

	public void setMappeoplesForUserId(Set mappeoplesForUserId) {
		this.mappeoplesForUserId = mappeoplesForUserId;
	}

	public Set getMaployaltypointusers() {
		return this.maployaltypointusers;
	}

	public void setMaployaltypointusers(Set maployaltypointusers) {
		this.maployaltypointusers = maployaltypointusers;
	}

	public Set getMappeoplesForPeopleId() {
		return this.mappeoplesForPeopleId;
	}

	public void setMappeoplesForPeopleId(Set mappeoplesForPeopleId) {
		this.mappeoplesForPeopleId = mappeoplesForPeopleId;
	}

	public Set getMapfavourites() {
		return this.mapfavourites;
	}

	public void setMapfavourites(Set mapfavourites) {
		this.mapfavourites = mapfavourites;
	}

	public Set getMapreviews() {
		return this.mapreviews;
	}

	public void setMapreviews(Set mapreviews) {
		this.mapreviews = mapreviews;
	}

	public Set getCoupons() {
		return this.coupons;
	}

	public void setCoupons(Set coupons) {
		this.coupons = coupons;
	}

	public Set getMapusercoupons() {
		return this.mapusercoupons;
	}

	public void setMapusercoupons(Set mapusercoupons) {
		this.mapusercoupons = mapusercoupons;
	}


	public String getFBId() {
		return this.FBId;
	}


	public void setFBId(String FBId) {
		this.FBId = FBId;
	}

}
