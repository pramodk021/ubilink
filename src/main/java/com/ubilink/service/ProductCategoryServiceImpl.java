package com.ubilink.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ubilink.exception.ProductCategoryNotFound;
import com.ubilink.model.Productcategory;
import com.ubilink.repository.ProductcategoryRepository;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	private static final Logger logger=Logger.getLogger(ProductCategoryServiceImpl.class);
	@Resource
	public ProductcategoryRepository productcategoryRepository;
	@Override
	public Productcategory create(Productcategory productcategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Productcategory delete(int id) throws ProductCategoryNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Productcategory> findAll() 
	{
		logger.debug("Entered in findAll() product categories");
		//Sort sort=new Sort(Sort.Direction.DESC, "id");
		return productcategoryRepository.findALL();
	}

	@Override
	public Productcategory update(Productcategory productcategory)
			throws ProductCategoryNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Productcategory findById(int id) 
	{
		return null;
	}

}
