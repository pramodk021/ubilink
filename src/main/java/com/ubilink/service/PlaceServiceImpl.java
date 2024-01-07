package com.ubilink.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubilink.exception.PlaceNotFound;
import com.ubilink.model.Event;
import com.ubilink.model.Mapplacecategory;
import com.ubilink.model.Place;
import com.ubilink.repository.EventRepository;
import com.ubilink.repository.PlaceRepository;


@Service
public class PlaceServiceImpl implements PlaceService{

	private static final Logger logger=Logger.getLogger(PlaceServiceImpl.class);
	
	@Resource
	private PlaceRepository placeRepository;
	@Resource
	private EventRepository eventRepository;
	
	@Autowired
	public PlaceServiceImpl(PlaceRepository placeRepository)
	{
		this.placeRepository=placeRepository;
	}
	
	@Override
	public Place create(Place place) {
		logger.debug("Entered in create(Place place)");
		placeRepository.save(place);
		return place;
	}

	@Override
	@Transactional
	public void delete(int id)  {
		logger.debug("Entered in delete by id :"+id);
		//Place deletedMall=placeRepository.findOne(id);
		
		placeRepository.delete(id);
		logger.info("Deleted place by id "+id);
	}

	@Override
	@Transactional
	public List<Place> findAll() {
		logger.debug("Entered in findAll()");
		List<Place>malls=placeRepository.findAll();
		for(Place place:malls)
		{
			place.setMapplacecategories(place.getMapplacecategories());
		}
		return malls;
	}

	@Override
	@Transactional(rollbackFor=PlaceNotFound.class)
	public Place update(Place place) throws PlaceNotFound {
		Place mallToUpdate=placeRepository.findOne(place.getId());
		
		if(mallToUpdate==null)
			throw new PlaceNotFound();
		mallToUpdate.setAddress(place.getAddress());
		mallToUpdate.setName(place.getName());
		mallToUpdate.setLocation(place.getLocation());
		mallToUpdate.setUsers(place.getUsers());
		mallToUpdate.setEvents(place.getEvents());
		//mallToUpdate.setRetailers(place.getRetailers());
		mallToUpdate.setGpId(place.getGpId());
		
		return mallToUpdate;
	}

	@Override
	@Transactional
	public Place findOne(int id) 
	{
		logger.debug("Entered in findById "+id);
		Place place=placeRepository.findOne(id);
		try
		{
		if(place!=null)
		{
			 List<Mapplacecategory>mapplacecategories=new ArrayList<Mapplacecategory>();
	    		mapplacecategories.addAll(place.getMapplacecategories());
	    		for(Mapplacecategory mapplacecategory:mapplacecategories)
	    		{
	    			Hibernate.initialize(mapplacecategory.getPlacecategory());
	    			mapplacecategory.setPlacecategory(mapplacecategory.getPlacecategory());
	    		}
	    		
	    		List<Event>events=new ArrayList<Event>();
	    		events.addAll(place.getEvents());
	    		for(Event event:events)
	    		{
	    			Hibernate.initialize(event);
	    			
	    		}
		}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		logger.debug("Exit from in findById "+place);
		return place;
	}

	@Override
	@Transactional
	public Place findByGpId(String gpId) {
		logger.debug("Entered in findByDescription "+gpId);
		
		Place place=placeRepository.getPlaceByGpId(gpId);
		if(place!=null)
		{
			List<Mapplacecategory>mapplacecategories=new ArrayList<Mapplacecategory>();
    		mapplacecategories.addAll(place.getMapplacecategories());
    		for(Mapplacecategory mapplacecategory:mapplacecategories)
    		{
    			Hibernate.initialize(mapplacecategory.getPlacecategory());
    			mapplacecategory.setPlacecategory(mapplacecategory.getPlacecategory());
    		}
		}
		return place;
	}

	
	@Override
	@Transactional
	public List<Place> getHotspotsById()
	{
		logger.debug("Entered in getHotspotsById ");
		List<Place>places=placeRepository.findHotspots();
		for(Place place : places)
		{
			if(place!=null)
			{
				 List<Mapplacecategory>mapplacecategories=new ArrayList<Mapplacecategory>();
		    		mapplacecategories.addAll(place.getMapplacecategories());
		    		for(Mapplacecategory mapplacecategory:mapplacecategories)
		    		{
		    			Hibernate.initialize(mapplacecategory.getPlacecategory());
		    		}
				
			}
		}
		
		return places;
	}

	@Override
	public List<Event> findAllEventsByMall(int mallId) 
	{
		logger.debug("Entered in findAllEventsByMall "+mallId);
		return eventRepository.findByPlaceId(mallId);
	}

	
	@Override
	@Transactional
	public List<Place> findMallByMall(int hotspotId,int pageNum,int pageSize) 
	{
		List<Place> places=placeRepository.findPageByHotspotId(hotspotId,new PageRequest(pageNum, pageSize));
		for(Place place : places)
		{
			if(place!=null)
			{
				 List<Mapplacecategory>mapplacecategories=new ArrayList<Mapplacecategory>();
		    		mapplacecategories.addAll(place.getMapplacecategories());
		    		for(Mapplacecategory mapplacecategory:mapplacecategories)
		    		{
		    			Hibernate.initialize(mapplacecategory.getPlacecategory());
		    		}
				
			}
		}
		return places;
	}

	@Override
	@Transactional
	public List<Place> findFavouritePlaces(int userId, String type,int pageNum, int pageSize) {
		List<Place>places=placeRepository.findListByFavouriteTypeAndUserId(type,userId);
		for(Place place : places)
		{
			if(place!=null)
			{
				 List<Mapplacecategory>mapplacecategories=new ArrayList<Mapplacecategory>();
		    		mapplacecategories.addAll(place.getMapplacecategories());
		    		for(Mapplacecategory mapplacecategory:mapplacecategories)
		    		{
		    			Hibernate.initialize(mapplacecategory.getPlacecategory());
		    		}
				
			}
		}
		return places;
	}
	@Override
	public Place saveAndFlush(Place place)
	{
		return placeRepository.saveAndFlush(place);
	}

}
