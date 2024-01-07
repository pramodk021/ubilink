package com.ubilink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubilink.model.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Integer>
{
	@Query("SELECT e FROM Event e WHERE e.id=?1")
	public Event findOne(int id);
	
	@Query(value="SELECT * FROM Event  WHERE placeId = ?1 " ,nativeQuery=true)
	public List<Event> findByPlaceId(int placeId);
}
