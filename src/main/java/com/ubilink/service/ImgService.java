package com.ubilink.service;

import java.sql.Blob;

import org.springframework.stereotype.Service;


@Service
public interface ImgService
{
	public byte[] getEventImgById(int eventId);
	public byte[] getOfferImgById(int offerId);
	public Blob getPlaceImgById(int placeId);
	public Blob getPlaceCatImgById(int placeCatId);
	public byte[] getPlaceLogoImg(int placeId);
	public Blob getProdCatImg(int prodCatId);
	
}
