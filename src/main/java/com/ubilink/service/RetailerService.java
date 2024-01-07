package com.ubilink.service;

import java.util.List;

import com.ubilink.exception.RetailerNotFound;
import com.ubilink.model.Retailer;

public interface RetailerService
{
	public Retailer create(Retailer retailer);
	public Retailer delete(int id) throws RetailerNotFound;
	public List<Retailer> findAll();
	public Retailer update(Retailer retailer) throws RetailerNotFound;
	public Retailer findById(int id);
	public Retailer getRetailerByOffer(int offerId);
}
