package com.ubilink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubilink.model.Mapplacecategory;
@Repository
public interface MapplacecategoryRepository extends JpaRepository<Mapplacecategory, Integer>
{
	@Query("SELECT mpc FROM Mapplacecategory mpc WHERE mpc.id=?1")
	public Mapplacecategory findOne(int id);
	public List<String> findListByPlaceId(int placeId);
}
