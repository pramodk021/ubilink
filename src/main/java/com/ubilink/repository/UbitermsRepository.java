package com.ubilink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ubilink.model.Maployaltypointuser;
import com.ubilink.model.Ubiterms;
@Repository
public interface UbitermsRepository extends JpaRepository<Ubiterms, Integer>
{
	@Query("SELECT u FROM Ubiterms u WHERE u.id=?1")
	public Ubiterms findOne(int id);
	
	/*@Query(value=" DELETE FROM Ubiterms ut WHERE ut.id=?1 ")
	public void delete(int id);*/
	
	public Ubiterms save(Ubiterms ubiterms);
	
	
}
