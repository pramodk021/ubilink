package com.ubilink.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubilink.model.Reviewtype;
import com.ubilink.repository.ReviewTypeRepository;

@Service
public class ReviewTypeServiceImpl implements ReviewTypeService
{

	@Resource
	private ReviewTypeRepository reviewTypeRepo;
	@Override
	public Reviewtype findIdByType(String type) 
	{
		return reviewTypeRepo.findByType(type);
	}

}
