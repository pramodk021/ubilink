package com.ubilink.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubilink.model.Loyaltypoint;
import com.ubilink.model.Maployaltypointuser;
import com.ubilink.model.Reviewtype;
import com.ubilink.model.User;
@Repository
public interface SocialMediaRepository extends JpaRepository<Maployaltypointuser, Integer>{

	@Query("SELECT mlpu FROM Maployaltypointuser mlpu WHERE mlpu.id=?1")
	public Maployaltypointuser findOne(int id);
	
	public Maployaltypointuser findByUserAndLoyaltypoint(User user,Loyaltypoint loyaltypoint);
	
	@Query(value="SELECT sum(count) FROM maployaltypointuser where userId=?1",nativeQuery=true)
	public BigDecimal getLoyaltyPointCount(int userId);
	
	
}
