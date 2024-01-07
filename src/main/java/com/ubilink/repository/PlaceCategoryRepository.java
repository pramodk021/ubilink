package com.ubilink.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubilink.model.Offer;
import com.ubilink.model.Place;
import com.ubilink.model.Placecategory;
@Repository
public interface PlaceCategoryRepository extends JpaRepository<Placecategory,Integer>
{
	@Query("SELECT pc FROM Placecategory pc WHERE pc.id=?1")
	public Placecategory findOne(int id);
	
	public List<String> findCategoryByIsApplicable(boolean isApplicable);
	
	public List<Placecategory> findByIsApplicable(boolean isApplicable);
	
	@Query(value="SELECT * from placecategory where categoryImg is not null",nativeQuery=true)
	public List<Placecategory> findValidCategories();
	
	
}
