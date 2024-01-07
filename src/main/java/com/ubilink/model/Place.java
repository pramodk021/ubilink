package com.ubilink.model;

// Generated 26 Apr, 2014 4:44:10 PM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mysql.cj.jdbc.Blob;

/**
 * Place generated by hbm2java
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Place implements java.io.Serializable  {

	private int id;
	private String name;
	private String location;
	private String address;
	private String gpId;
	private String[] arrCats;
	private String description;
	private Integer parentId;
	private String workingTime;
	private String contact;
	private Integer hotspotId;
	private Date createdOn;
	private Integer createdBy;
	private Date updatedOn;
	private Integer updatedBy;
	private Boolean isActive;
	@JsonIgnore
	private byte[] logoImg;
	@JsonIgnore
	private Blob imgFile;
	@JsonIgnore
	private Set<Mapplacecategory> mapplacecategories = new HashSet<Mapplacecategory>(0);
	@JsonIgnore
	private Set offers = new HashSet(0);
	@JsonIgnore
	private Set users = new HashSet(0);
	@JsonIgnore
	private Set events = new HashSet(0);
	@JsonIgnore
	private Set loyaltypoints = new HashSet(0);
	@JsonIgnore
	private List<String>placeCategories=new ArrayList<String>();

	public Place() {
	}
	public Place(String name) {
		this.name = name;
	}
	public Place(String name, String location, String address, String gpId,
			 String description, String categoryImg,
			byte[] logoImg, Blob imgFile, Integer parentId,
			String workingTime, String contact, Integer hotspotId,
			Date createdOn, Integer createdBy, Date updatedOn,
			Integer updatedBy, Boolean isActive, Set mapplacecategories,
			Set offers, Set users, Set events, Set loyaltypoints) {
		this.name = name;
		this.location = location;
		this.address = address;
		this.gpId = gpId;
		this.description = description;
		this.logoImg = logoImg;
		this.imgFile = imgFile;
		this.parentId = parentId;
		this.workingTime = workingTime;
		this.contact = contact;
		this.hotspotId = hotspotId;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.isActive = isActive;
		this.mapplacecategories = mapplacecategories;
		this.offers = offers;
		this.users = users;
		this.events = events;
		this.loyaltypoints = loyaltypoints;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGpId() {
		return this.gpId;
	}

	public void setGpId(String gpId) {
		this.gpId = gpId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public byte[] getLogoImg() {
		return this.logoImg;
	}

	public void setLogoImg(byte[] logoImg) {
		this.logoImg = logoImg;
	}

	public Blob getImgFile() {
		return this.imgFile;
	}

	public void setImgFile(Blob imgFile) {
		this.imgFile = imgFile;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getWorkingTime() {
		return this.workingTime;
	}

	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getHotspotId() {
		return this.hotspotId;
	}

	public void setHotspotId(Integer hotspotId) {
		this.hotspotId = hotspotId;
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

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set getMapplacecategories() {
		return this.mapplacecategories;
	}

	public void setMapplacecategories(Set mapplacecategories) {
		this.mapplacecategories = mapplacecategories;
	}

	public Set getOffers() {
		return this.offers;
	}

	public void setOffers(Set offers) {
		this.offers = offers;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	public Set getEvents() {
		return this.events;
	}

	public void setEvents(Set events) {
		this.events = events;
	}

	public Set getLoyaltypoints() {
		return this.loyaltypoints;
	}

	public void setLoyaltypoints(Set loyaltypoints) {
		this.loyaltypoints = loyaltypoints;
	}

	public List<String> getPlaceCategories() {
		return this.placeCategories;
	}

	public void setPlaceCategories(List<String> placeCategories) {
		this.placeCategories = placeCategories;
	}

	public String[] getArrCats() {
		return arrCats;
	}

	public void setArrCats(String[] arrCats) {
		this.arrCats = arrCats;
	}

	
}
