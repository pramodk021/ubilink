package com.ubilink.gservice;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ubilink.model.Offer;

public interface OfferService<T> extends GenericService<T> {

	List<Offer> findPageByPlaceIdAndProductcategoryId(int placeId, int productcategoryId, Pageable pageable);

	List<Offer> findPageByPlaceId(int placeId, Pageable pageable);

	List<Offer> findPageByHotspotIdAndProdCategoryId(int hotspotId, int productCategoryId, Pageable pageable);

	List<Offer> findPageByHotspotId(int hotspotId, Pageable pageable);

	List<Offer> findPageByProductcategoryId(int productcategoryId, Pageable pageable);

	long getCountByHotspotId(int hotspotId);

	long getCountByPlaceId(int placeId);

}
