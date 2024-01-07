package com.ubilink.gserviceimpl;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubilink.gservice.OfferService;
import com.ubilink.model.Offer;
import com.ubilink.repository.OfferRepository;

@Service
public class OfferServiceGImpl implements OfferService {

	@Resource
	private OfferRepository offerRepository;

	@Override
	public void delete(int id) {
		offerRepository.delete(id);
	}

	@Override
	public Offer saveAndFlush(Object entity) {
		Offer offer = (Offer) entity;
		return offerRepository.saveAndFlush(offer);
	}

	@Override
	@Transactional
	public Offer findOne(int id) {
		Offer offer = offerRepository.findOne(id);
		Hibernate.initialize(offer.getProductcategory().getCategory());
		Hibernate.initialize(offer.getProductcategory().getId());
		Hibernate.initialize(offer.getPlace().getId());
		Hibernate.initialize(offer.getPlace().getName());

		return offer;
	}

	@Override
	@Transactional
	public List<Offer> findAll() {
		List<Offer> offers = offerRepository.findAll();

		return initializeOffers(offers);
	}

	@Override
	public List<Offer> findPageByPlaceIdAndProductcategoryId(int placeId, int productcategoryId, Pageable pageable) {
		List<Offer> offers = offerRepository.findPageByPlaceIdAndProductcategoryId(placeId, productcategoryId,
				pageable);

		return initializeOffers(offers);
	}

	@Override
	public List<Offer> findPageByPlaceId(int placeId, Pageable pageable) {
		List<Offer> offers = offerRepository.findPageByPlaceId(placeId, pageable);

		return initializeOffers(offers);
	}

	@Override
	public List<Offer> findPageByHotspotIdAndProdCategoryId(int hotspotId, int productCategoryId, Pageable pageable) {
		List<Offer> offers = offerRepository
				.findPageByHotspotIdAndProdCategoryId(hotspotId, productCategoryId, pageable).getContent();

		return initializeOffers(offers);
	}

	@Override
	public List<Offer> findPageByHotspotId(int hotspotId, Pageable pageable) {
		List<Offer> offers = offerRepository.findPageByHotspotId(hotspotId, pageable).getContent();

		return initializeOffers(offers);
	}

	@Override
	public List<Offer> findPageByProductcategoryId(int productcategoryId, Pageable pageable) {
		List<Offer> offers = offerRepository.findPageByProductcategoryId(productcategoryId, pageable).getContent();

		return initializeOffers(offers);
	}

	@Override
	public long getCountByHotspotId(int hotspotId) {
		return ((BigInteger) offerRepository.getOfferCountByHotspot(hotspotId)).longValue();
	}

	@Override
	public long getCountByPlaceId(int placeId) {
		return ((BigInteger) offerRepository.getOfferCountByPlace(placeId)).longValue();
	}

	@Transactional
	private List<Offer> initializeOffers(List<Offer> offers) {
		for (Offer offer : offers) {
			Hibernate.initialize(offer.getProductcategory().getCategory());
			Hibernate.initialize(offer.getProductcategory().getId());
			Hibernate.initialize(offer.getPlace().getId());
			Hibernate.initialize(offer.getPlace().getName());
		}
		return offers;
	}

	@Override
	public long count() {
		return offerRepository.count();
	}
}
