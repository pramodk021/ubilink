package com.ubilink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubilink.model.Event;
import com.ubilink.model.Favoritetype;
@Repository
public interface FavouritetypeRepository extends JpaRepository<Favoritetype, Integer>
{
	@Query("SELECT f FROM Favoritetype f WHERE f.id=?1")
	public Favoritetype findOne(int id);
	
	public Favoritetype findOneByType(String type);
}
