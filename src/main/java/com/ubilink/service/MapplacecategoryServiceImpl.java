package com.ubilink.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubilink.model.Mapplacecategory;
import com.ubilink.repository.MapplacecategoryRepository;

@Service
public class MapplacecategoryServiceImpl implements MapplacecategoryService{

	@Resource
	private MapplacecategoryRepository mapplacecategoryRepo;
	@Override
	@Transactional
	public Mapplacecategory create(Mapplacecategory mapplacecategory) {
		
		return mapplacecategoryRepo.save(mapplacecategory);
	}
	@Override
	@Transactional
	public List<String> findCategoryByPlaceId(int placeId)
	{
		
		return mapplacecategoryRepo.findListByPlaceId(placeId);
	}
	/*@Override
	public  List<Mapplacecategory> findAll() {
		return mapplacecategoryRepo.findAll();
	}
	@Override
	public  Mapplacecategory findOne(int id) {
		// TODO Auto-generated method stub
		return mapplacecategoryRepo.findOne(id);
	}*/
	/*@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object saveAndFlush(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
