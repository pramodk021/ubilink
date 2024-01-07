package com.ubilink.gserviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.ubilink.gservice.PlaceService;
import com.ubilink.model.Offer;
import com.ubilink.model.Place;
import com.ubilink.repository.PlaceRepository;

@Service
public class PlaceServiceGImpl implements PlaceService {
	@Resource
	private PlaceRepository placeRepository;

	@Override
	public void delete(int id) {
		placeRepository.delete(id);
	}

	@Override
	public Place saveAndFlush(Object entity) {
		Place place = (Place) entity;
		return placeRepository.saveAndFlush(place);
	}

	@Override
	public List<Place> findAll() {
		List<Place> places = placeRepository.findAll();
		for (Place place : places) {
			// Hibernate.initialize();
			Set<Offer> offers = place.getOffers();
			ArrayList<Offer> offerList = new ArrayList<Offer>(offers);
			for (Offer offer : offerList) {
				Hibernate.initialize(offer.getProductcategory().getCategory());
			}
		}
		return places;
	}

	@Override
	public Place findOne(int id) {
		return placeRepository.findOne(id);
	}

	@Override
	public long count() {
		return placeRepository.count();
	}

	@Override
	public List findPageByHotspotId(int hotspotId, Pageable pageable) {
		return placeRepository.findPageByHotspotId(hotspotId, pageable);
	}
}
