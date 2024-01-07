package com.ubilink.service;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubilink.exception.MaployaltypointuserNotFound;
import com.ubilink.model.Loyaltypoint;
import com.ubilink.model.Maployaltypointuser;
import com.ubilink.model.User;
import com.ubilink.repository.LoyaltyPointRepository;
import com.ubilink.repository.SocialMediaRepository;

@Service
public class SocialMediaServiceImpl implements SocialMediaService {

	Logger logger=Logger.getLogger(SocialMediaServiceImpl.class);
	@Resource
	private SocialMediaRepository maployaltypointuserRepository;
	@Resource
	private LoyaltyPointRepository loyaltyPointRepository;
	@Override
	public Maployaltypointuser create(Maployaltypointuser maployaltypointuser) {
		
		logger.debug("Entered in create(Maployaltypointuser maployaltypointuser)");
		return maployaltypointuserRepository.save(maployaltypointuser);
	}
	@Override
	public Loyaltypoint findLoyaltypointById(int id) 
	{
		logger.debug("Entered in findBonuspointById(int id) id "+id);
		return loyaltyPointRepository.findOne(id);
	}
	@Override
	@Transactional
	public Maployaltypointuser findByUserAndLoyaltypoint(User user,
			Loyaltypoint loyaltypoint) 
	{
		logger.debug("Entered in findByUserAndLoyaltypoint(User user,Loyaltypoint loyaltypoint)");
		return maployaltypointuserRepository.findByUserAndLoyaltypoint(user, loyaltypoint);
	}
	@Override
	@Transactional(rollbackFor=MaployaltypointuserNotFound.class)
	public Maployaltypointuser update(Maployaltypointuser maployaltypointuser)
			throws MaployaltypointuserNotFound {
		logger.debug("Entered in update Mapbonuspointuser");
		Maployaltypointuser updatedMaployaltypointuser = maployaltypointuserRepository.findOne(maployaltypointuser.getId());
		
		
		if (updatedMaployaltypointuser == null)
		{
			logger.debug("MapbonuspointuserNotFound to update");
			throw new MaployaltypointuserNotFound();
		}
		
		updatedMaployaltypointuser.setCount(maployaltypointuser.getCount());
		return updatedMaployaltypointuser;
	}
	@Override
	public BigDecimal getLoyaltyPointCount(int userId)
	{
		logger.debug("Entered in getLoyaltyPointCount(int userId) userId "+userId);
		return maployaltypointuserRepository.getLoyaltyPointCount(userId);
	}
	@Override
	public Loyaltypoint findLoyaltypointByRule(String rule) {
		return loyaltyPointRepository.findByRule(rule);
	}
	@Override
	public BigDecimal getLoyatyPoints(int userId) 
	{
		return loyaltyPointRepository.getLoyatyPoints(userId);
	}

}
