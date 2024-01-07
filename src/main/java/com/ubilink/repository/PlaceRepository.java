package com.ubilink.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ubilink.model.Place;
@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer>{

	String queryFavouritePlaces="SELECT * FROM place as p "+
			"join mapfavourite as mf on p.id=mf.mapfavouriteentityid "+
			"join favoritetype as ft on mf.favouritetypeid =ft.id "+
			"where mf.userid=?2 and ft.type=?1";
	
	String queryPlacesByHotspotId = "SELECT p FROM Place p "+ "where p.hotspotId= :hotspotId";

	String queryPlaceCountByHotspotId = "SELECT count(p) FROM Place p " + "where p.hotspotId= :hotspotId";
	
	@Query("SELECT p FROM Place p WHERE p.id=?1")
	public Place findOne(int id);

	@Query("SELECT m FROM Place m WHERE m.gpId = ?1 ")
	public Place getPlaceByGpId(String gpId);
	
	@Query(value="SELECT * FROM place where hotspotId is null",nativeQuery=true)
	public List<Place> findHotspots();
	
	@Query(value="SELECT * FROM place where parentId=?1",nativeQuery=true)
	public List<Place> findByPlace(int parentId);
	
	@Query(value=queryFavouritePlaces,nativeQuery=true)
	public List<Place> findListByFavouriteTypeAndUserId(String type,int userId);
	
	@Query(value = queryPlacesByHotspotId, countQuery = queryPlaceCountByHotspotId)
	public List<Place> findPageByHotspotId(@Param("hotspotId") int hotspotId, Pageable pageable);
	
}
