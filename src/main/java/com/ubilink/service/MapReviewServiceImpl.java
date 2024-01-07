package com.ubilink.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubilink.model.Mapreview;
import com.ubilink.model.Reviewtype;
import com.ubilink.model.User;
import com.ubilink.repository.MapReviewRepository;

@Service
public class MapReviewServiceImpl implements MapReviewService 
{
	@Resource
	private MapReviewRepository mapReviewRepo;

	@Override
	@Transactional
	public Mapreview create(Mapreview mapreview) 
	{
		Mapreview createdMapReview=mapReviewRepo.save(mapreview);
		return createdMapReview;
	}

	@Override
	@Transactional
	public Mapreview findByReviewtypeAndUserAndEntityId(
			Reviewtype reviewType, User user, int entityId) {
		// TODO Auto-generated method stub
		Mapreview mapreview=null;
		List<Mapreview> mapreviews=mapReviewRepo.findByReviewtypeAndUserAndEntityId(reviewType, user, entityId);
		if(mapreviews.size()>0)
			mapreview=mapreviews.get(0);
		return mapreview;
	}

	@Override
	@Transactional
	public List<Mapreview> findAll() {
		List<Mapreview> mapreviews=mapReviewRepo.findAll();
		for(Mapreview mapreview:mapreviews)
		{
			Hibernate.initialize(mapreview.getUser());
			Hibernate.initialize(mapreview.getUser().getName());
			mapreview.setUser(mapreview.getUser());
			//Hibernate.initialize(mapreview.getUser().getName());
			//mapreview.setUser(mapreview.getUser());
		}
		return mapreviews;
	}

	@Override
	@Transactional
	public Mapreview findOne(int id) {
		// TODO Auto-generated method stub
		return mapReviewRepo.findOne(id);
	}
	
	
}
