package com.ubilink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubilink.model.Favoritetype;
import com.ubilink.model.Mapfavourite;
import com.ubilink.model.User;

@Repository
public interface MapfavouriteRepository extends JpaRepository<Mapfavourite, Integer> 
{
	@Query("SELECT mf FROM Mapfavourite mf WHERE mf.id=?1")
	public Mapfavourite findOne(int id);
	public Mapfavourite findByUserAndFavoritetypeAndMapFavouriteEntityId(User user,Favoritetype favoritetype,Integer mapFavouriteEntityId);
}
