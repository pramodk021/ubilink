package com.ubilink.service;

import java.math.BigDecimal;

import com.ubilink.exception.MaployaltypointuserNotFound;
import com.ubilink.model.Loyaltypoint;
import com.ubilink.model.Maployaltypointuser;
import com.ubilink.model.User;

public interface SocialMediaService
{
	public Maployaltypointuser create(Maployaltypointuser maployaltypointuser);
	public Loyaltypoint findLoyaltypointById(int id);
	public Loyaltypoint findLoyaltypointByRule(String rule);
	public Maployaltypointuser findByUserAndLoyaltypoint(User user,Loyaltypoint loyaltypoint);
	public Maployaltypointuser update(Maployaltypointuser maployaltypointuser) throws MaployaltypointuserNotFound;
	public BigDecimal getLoyaltyPointCount(int userId);
	public BigDecimal getLoyatyPoints(int userId);
}
