package com.ubilink.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubilink.model.Placecategory;
import com.ubilink.repository.PlaceCategoryRepository;
@Service
public class PlaceCatImpl implements PlaceCatService
{

	@Resource
	private PlaceCategoryRepository placeCatRepository;
	@Override
	public List<Placecategory> findAll() {
		return placeCatRepository.findAll();
	}
	@Override
	public List<String> findCategoryByIsApplicable(boolean isApplicable) 
	{
		return placeCatRepository.findCategoryByIsApplicable(isApplicable);
	}
	@Override
	public Map<String, Integer> findCategoryAndIdByIsApplicable(
			boolean isApplicable) {
		Map<String,Integer>categoryMap=new HashMap<String, Integer>();
		for(Placecategory placecategory:placeCatRepository.findByIsApplicable(isApplicable))
		{
			categoryMap.put(placecategory.getCategory(), placecategory.getId());
		}
		return categoryMap;
	}
	@Override
	public List<Placecategory> findByIsApplicable(boolean isApplicable) {
		return placeCatRepository.findByIsApplicable(isApplicable);
	}
	@Override
	public List<Placecategory> findValidCategories()
	{
		return placeCatRepository.findValidCategories();
	}

}
