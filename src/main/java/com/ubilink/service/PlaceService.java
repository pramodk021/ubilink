package com.ubilink.service;

import java.util.List;

import com.ubilink.exception.PlaceNotFound;
import com.ubilink.model.Event;
import com.ubilink.model.Place;

public interface PlaceService extends UbilinkService
{
	public Place create(Place place);
	//public Place delete(int id) throws PlaceNotFound;
	public List<Place> findAll();
	public Place update(Place place) throws PlaceNotFound;
	public Place findOne(int id);
	public Place findByGpId(String gpId);
	public List<Place> getHotspotsById();
	public List<Event> findAllEventsByMall(int mallId);
	public List<Place> findMallByMall(int hotspotId,int pageNum,int pageSize);
	public List<Place> findFavouritePlaces(int userId,String type,int pageNum,int pageSize);
	void delete(int id);
	Place saveAndFlush(Place place);
}
