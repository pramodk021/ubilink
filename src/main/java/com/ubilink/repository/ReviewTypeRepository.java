package com.ubilink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubilink.model.Productcategory;
import com.ubilink.model.Reviewtype;
@Repository
public interface ReviewTypeRepository extends JpaRepository<Reviewtype, Integer>
{
	@Query("SELECT rt FROM Reviewtype rt WHERE rt.id=?1")
	public Reviewtype findOne(int id);
	
	public Reviewtype findByType(String type) ;
}
