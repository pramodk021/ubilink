package com.ubilink.model;


// Generated 13 May, 2014 1:29:51 PM by Hibernate Tools 3.4.0.CR1

/**
 * Ubiterms generated by hbm2java
 */
public class Ubiterms implements java.io.Serializable {

	@Override
	public String toString() {
		return "Ubiterms [id=" + id + ", key=" + key + ", value=" + value
				+ ", cateory=" + category + "]";
	}

	private Integer id;
	private String key;
	private String value;
	private String category;

	public Ubiterms() {
	}

	public Ubiterms(String key) {
		this.key = key;
	}

	public Ubiterms(String key, String value, String cateory) {
		this.key = key;
		this.value = value;
		this.category = cateory;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
