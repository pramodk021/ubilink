package com.ubilink.model;

// Generated 26 Apr, 2014 4:44:10 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Favoritetype generated by hbm2java
 */
public class Favoritetype implements java.io.Serializable {

	private int id;
	private String type;
	@JsonIgnore
	private Set mapfavourites = new HashSet(0);

	public Favoritetype() {
	}

	public Favoritetype(int id, String type) {
		this.id = id;
		this.type = type;
	}

	public Favoritetype(int id, String type, Set mapfavourites) {
		this.id = id;
		this.type = type;
		this.mapfavourites = mapfavourites;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set getMapfavourites() {
		return this.mapfavourites;
	}

	public void setMapfavourites(Set mapfavourites) {
		this.mapfavourites = mapfavourites;
	}

}
