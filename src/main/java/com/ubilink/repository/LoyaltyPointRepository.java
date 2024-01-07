package com.ubilink.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubilink.model.Loyaltypoint;
@Repository
public interface LoyaltyPointRepository extends
		JpaRepository<Loyaltypoint, Integer> {

	@Query("SELECT lp FROM Loyaltypoint lp WHERE lp.id=?1")
	public Loyaltypoint findOne(int id);
	
	public Loyaltypoint findByRule(String rule);

	String queryCalculateLPs = "select ( "
			+ "SELECT IFNULL(sum(Points),0) FROM loyaltypoint as lp "
			+ "join maployaltypointuser as mlpu on lp.id=mlpu.loyaltypointid "
			+ "where mlpu.userid=?1) - "
			+ "(SELECT IFNULL(sum(Points),0) FROM loyaltyredeemprize as lrp "
			+ "join mapuserloyaltyredeem as mulr on lrp.id=mulr.loyaltypointprizeid "
			+ "where mulr.userid=?1) status";

	@Query(value = queryCalculateLPs, nativeQuery = true)
	public BigDecimal getLoyatyPoints(int userId);
}
