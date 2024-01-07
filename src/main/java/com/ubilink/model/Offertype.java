package com.ubilink.model;

// Generated 26 Apr, 2014 4:44:10 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Offertype generated by hbm2java
 */
public class Offertype implements java.io.Serializable {

	private int id;
	private String type;
	private Set offers = new HashSet(0);

	public Offertype() {
	}

	public Offertype(int id, String type) {
		this.id = id;
		this.type = type;
	}

	public Offertype(int id, String type, Set offers) {
		this.id = id;
		this.type = type;
		this.offers = offers;
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

	public Set getOffers() {
		return this.offers;
	}

	public void setOffers(Set offers) {
		this.offers = offers;
	}

}
