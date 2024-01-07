package com.ubilink.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubilink.exception.MapfavouriteNotFound;
import com.ubilink.model.Favoritetype;
import com.ubilink.model.Mapfavourite;
import com.ubilink.model.User;
import com.ubilink.repository.FavouritetypeRepository;
import com.ubilink.repository.MapfavouriteRepository;

@Service
public class MapfavouriteServiceImpl implements MapfavouriteService{

	private static final Logger logger=Logger.getLogger(MapfavouriteServiceImpl.class);
	@Autowired
	private MapfavouriteRepository mapfavouriteRepository;
	@Autowired
	private FavouritetypeRepository favouritetypeRepo;
	@Override
	@Transactional
	public Mapfavourite create(Mapfavourite mapfavourite)
	{
		logger.debug("Entered in create(Mapfavourite mapfavourite)");
		Mapfavourite favouriteCreated=mapfavouriteRepository.save(mapfavourite);
		return favouriteCreated;
	}
	
	@Override
	@Transactional(rollbackFor=MapfavouriteNotFound.class)
	public Mapfavourite update(Mapfavourite mapfavourite) throws MapfavouriteNotFound {
		logger.debug("Entered in update(Mapfavourite mapfavourite)");
		Mapfavourite updatedMapFavourite = mapfavouriteRepository.findOne(mapfavourite.getId());
		
		if (updatedMapFavourite == null)
		{
			logger.error("Mapfavourite not found by id "+mapfavourite.getId());
			throw new MapfavouriteNotFound();
		}
		updatedMapFavourite.setIsActive(mapfavourite.isIsActive());
		logger.info("Mapfavourite isActive = "+mapfavourite.isIsActive());
		return updatedMapFavourite;
	}
	@Override
	@Transactional
	public Mapfavourite findByUserAndFavoritetypeAndMapFavouriteEntityId(User user,Favoritetype favoritetype,Integer mapFavouriteEntityId)
	{
		logger.debug("Entered in findByUserAndFavoritetypeAndMapFavouriteEntityId");
		return mapfavouriteRepository.findByUserAndFavoritetypeAndMapFavouriteEntityId(user, favoritetype, mapFavouriteEntityId);
	}
	
	@Override
	@Transactional(rollbackFor=MapfavouriteNotFound.class)
	public Mapfavourite delete(int id) throws MapfavouriteNotFound {
		logger.debug("Entered in delete by id :"+id);
		Mapfavourite deletedMapfavourite=mapfavouriteRepository.findOne(id);
		if(deletedMapfavourite==null)
		{
			logger.error("Place not found by id "+id);
			throw new MapfavouriteNotFound();
		}
		mapfavouriteRepository.delete(deletedMapfavourite);
		logger.info("Deleted place by id "+id);
		return deletedMapfavourite;
	}

	@Override
	public Favoritetype findByType(String type) 
	{
		return favouritetypeRepo.findOneByType(type);
	}
	
}
