package com.ubilink.service;

import java.util.List;

import com.ubilink.model.Mapplacecategory;

public interface MapplacecategoryService 
{
	public Mapplacecategory create(Mapplacecategory mapplacecategory);
	public List<String> findCategoryByPlaceId(int placeId);
}
