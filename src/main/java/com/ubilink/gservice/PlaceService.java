package com.ubilink.gservice;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ubilink.model.Place;

public interface PlaceService<T> extends GenericService<T>{

	List<Place> findPageByHotspotId(int hotspotId, Pageable pageable);

	
}
