package com.ubilink.service;

import java.sql.Blob;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ubilink.repository.EventRepository;
import com.ubilink.repository.OfferRepository;
import com.ubilink.repository.PlaceCategoryRepository;
import com.ubilink.repository.PlaceRepository;
import com.ubilink.repository.ProductcategoryRepository;

@Service
public class ImgServiceImpl implements ImgService
{
	private static final Logger logger=Logger.getLogger(ImgServiceImpl.class);
	@Resource
	private EventRepository eventRepository;
	@Resource
	private OfferRepository offerRepository;
	@Resource
	private PlaceRepository placeRepository;
	@Resource
	private PlaceCategoryRepository placeCatRepository;
	@Resource
	private ProductcategoryRepository productcategoryRepository;
	
	@Override
	public byte[] getEventImgById(int eventId)
	{
		logger.debug("Enter in getEventImgById(int eventId)");
		return eventRepository.findOne(eventId).getImgEvent();
	}
	@Override
	public byte[] getOfferImgById(int offerId) 
	{
		logger.debug("Enter in getOfferImgById(int offerId)");
		return offerRepository.findOne(offerId).getImgFile();
	}
	@Override
	public Blob getPlaceImgById(int placeId) 
	{
		logger.debug("Enter in getPlaceImgById(int placeId)");
		return placeRepository.findOne(placeId).getImgFile();
	}
	
	@Override
	public Blob getPlaceCatImgById(int placeCatId) 
	{
		logger.debug("Enter in getPlaceCatImgById(int placeCatId)");
		return placeCatRepository.findOne(placeCatId).getCategoryImg();
	}
	@Override
	public byte[] getPlaceLogoImg(int placeId) {
		logger.debug("Enter in getPlaceCatImgById(int placeCatId)");
		return placeRepository.findOne(placeId).getLogoImg();
	}
	@Override
	public Blob getProdCatImg(int prodCatId) {
		return productcategoryRepository.findOne(prodCatId).getProductCatImg();
	}
	
}
