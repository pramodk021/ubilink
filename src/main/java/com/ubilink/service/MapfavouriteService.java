package com.ubilink.service;

import com.ubilink.exception.MapfavouriteNotFound;
import com.ubilink.model.Favoritetype;
import com.ubilink.model.Mapfavourite;
import com.ubilink.model.User;

public interface MapfavouriteService 
{
	public Mapfavourite create(Mapfavourite mapfavourite);
	public Mapfavourite findByUserAndFavoritetypeAndMapFavouriteEntityId(User user,Favoritetype favoritetype,Integer mapFavouriteEntityId);
	public Mapfavourite update(Mapfavourite mapfavourite) throws MapfavouriteNotFound; 
	public Mapfavourite delete(int id) throws MapfavouriteNotFound ;
	public Favoritetype findByType(String type);
}
