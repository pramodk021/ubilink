package com.ubilink.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ubilink.model.Offer;
@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>,JpaSpecificationExecutor<Offer> {

	@Query("SELECT o FROM Offer o WHERE o.id=?1")
	public Offer findOne(int id);
	
	public List<Offer> findByPlaceId(int placeId);

	public List<Offer> findPageByPlaceId(int placeId, Pageable pageable);
	
	public List<Offer> findPageByPlaceIdAndProductcategoryId(int placeId,int productcategoryId, Pageable pageable);

	// Returns Offer Pages By Hot spots(Place parent) and product category.

	String queryOffersByHotspotsAndProdCat = "SELECT o FROM Offer o "+ " LEFT JOIN o.productcategory pc LEFT JOIN o.place p "+ "where p.hotspotId= :hotspotId and pc.id= :productCategoryId";

	String queryOffersCountByHotspotsAndProdCat = "SELECT count(o) FROM Offer o "+ " LEFT JOIN o.productcategory pc LEFT JOIN o.place p "+ "where p.hotspotId= :hotspotId and pc.id= :productCategoryId";

	@Query(value = queryOffersByHotspotsAndProdCat, countQuery = queryOffersCountByHotspotsAndProdCat)
	public Page<Offer> findPageByHotspotIdAndProdCategoryId(@Param("hotspotId") int hotspotId,@Param("productCategoryId") int productCategoryId, Pageable pageable);

	// Returns Offer Pages By Hotspots(Place parent).

	String queryOffersByHotspots = "SELECT o FROM Offer o "+ " LEFT JOIN o.place p  " + "where p.hotspotId= :hotspotId";

	String queryOffersCountByHotspots = "SELECT count(o) FROM Offer o "	+ " LEFT JOIN o.place p  " + "where p.hotspotId= :hotspotId";

	@Query(value = queryOffersByHotspots, countQuery = queryOffersCountByHotspots)
	public Page<Offer> findPageByHotspotId(@Param("hotspotId") int hotspotId,Pageable pageable);
	
	String queryOfferCountByHotspot = "select count(*) OfferCount "+ " FROM `offer` offer " + " join place on offer.placeId=place.id"+ " where place.hotspotId=?1";

	@Query(value = queryOfferCountByHotspot, nativeQuery = true)
	public BigInteger getOfferCountByHotspot(int mallId);

	String queryOfferCountByPlace = "select count(*) OfferCount "+ " FROM `offer` offer " + " where offer.placeId=?1";

	@Query(value = queryOfferCountByPlace, nativeQuery = true)
	public BigInteger getOfferCountByPlace(int mallId);

	public Page<Offer> findPageByProductcategoryId(int productcategoryId,Pageable pageable);

	String productNamesQuery = "SELECT  productName FROM offer as o "+ "where placeId=?1 and productName like CONCAT (?2,'%') limit 10";

	@Query(value = productNamesQuery, nativeQuery = true)
	public List<String> getProductNames(int placeId, String product);

}
