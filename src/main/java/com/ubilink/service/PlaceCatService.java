package com.ubilink.service;

import java.util.List;
import java.util.Map;

import com.ubilink.model.Placecategory;

public interface PlaceCatService 
{
	public List<Placecategory> findAll();
	public List<String> findCategoryByIsApplicable(boolean isApplicable);
	public Map<String,Integer> findCategoryAndIdByIsApplicable(boolean isApplicable);
	public List<Placecategory> findByIsApplicable(boolean isApplicable);
	public List<Placecategory> findValidCategories();
}
