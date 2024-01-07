package com.ubilink.service;

import java.util.List;

import com.ubilink.model.Mapreview;
import com.ubilink.model.Reviewtype;
import com.ubilink.model.User;

public interface MapReviewService 
{
	public Mapreview create(Mapreview mapreview);
	public Mapreview findByReviewtypeAndUserAndEntityId(Reviewtype reviewType,User user,int entityId);
	Mapreview findOne(int id);
	List<Mapreview> findAll();
}
