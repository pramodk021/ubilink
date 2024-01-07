package com.ubilink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubilink.model.Mapplacecategory;
import com.ubilink.model.Mapreview;
import com.ubilink.model.Place;
import com.ubilink.model.Reviewtype;
import com.ubilink.model.User;
@Repository
public interface MapReviewRepository extends JpaRepository<Mapreview, Integer>
{
	@Query("SELECT mr FROM Mapreview mr WHERE mr.id=?1")
	public Mapreview findOne(int id);
	public List<Mapreview> findByReviewtypeAndUserAndEntityId(Reviewtype reviewType,User user,int entityId);
}
