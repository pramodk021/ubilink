package com.ubilink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubilink.model.Productcategory;
@Repository
public interface ProductcategoryRepository extends JpaRepository<Productcategory,Integer> 
{
	@Query("SELECT pc FROM Productcategory pc WHERE pc.id=?1")
	public Productcategory findOne(int id);
	@Query("SELECT id,category FROM Productcategory pc")
	public List<Productcategory> findALL();
}
