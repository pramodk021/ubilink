package com.ubilink.service;

import java.util.List;

import com.ubilink.exception.ProductCategoryNotFound;
import com.ubilink.model.Productcategory;

public interface ProductCategoryService
{
	public Productcategory create(Productcategory productcategory);
	public Productcategory delete(int id) throws ProductCategoryNotFound;
	public List<Productcategory> findAll();
	public Productcategory update(Productcategory productcategory) throws ProductCategoryNotFound;
	public Productcategory findById(int id);
}
