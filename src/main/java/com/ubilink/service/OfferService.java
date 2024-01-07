package com.ubilink.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ubilink.exception.OfferNotFound;
import com.ubilink.model.Offer;

public interface OfferService
{
	public Offer create(Offer offer);
	public Offer delete(int id) throws OfferNotFound;
	public List<Offer> findAll();
	public Offer update(Offer offer) throws OfferNotFound;
	public Offer findById(int id);
	public Page<Offer> findPage(int pageNum,int pageSize,int category);
	public List<Offer> findPageByMall(int mallId,int pageNum,int pageSize);
	public List<Offer> getAllOffersByMall(int mallId);
	public long getOfferCountByHotspot(int placeId);
	public long getOfferCountByPlace(int placeId);
	public List<String> getProductNames(int retailerId,String product);
	public List<Offer> findByRetailerMallAndProductcategory(int mallId,int categoryId,int pageNum,int pageSize);
	public List<Offer> findByHotspot(int hotspotId,int productCatId,int pageNum,int pageSize);
	/*public Page<Offer> findByPlaceId(int placeId,int pageNum,int pageSize);*/
}
