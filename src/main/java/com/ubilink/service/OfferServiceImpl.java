package com.ubilink.service;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubilink.exception.OfferNotFound;
import com.ubilink.model.Offer;
import com.ubilink.model.Place;
import com.ubilink.model.Productcategory;
import com.ubilink.repository.OfferRepository;
@Service
public class OfferServiceImpl implements OfferService
{
	private static final Logger logger=Logger.getLogger(OfferServiceImpl.class);
	@Resource
	private OfferRepository offerRepository;
	@Override
	public Offer create(Offer offer) {
		logger.debug("Entered in create(Offer offer)");
		return offerRepository.save(offer);
	}

	@Override
	@Transactional(rollbackFor=OfferNotFound.class)
	public Offer delete(int id) throws OfferNotFound 
	{
		logger.debug("Entered in delete offer by id "+id);
		Offer deletedOffer=offerRepository.findOne(id);
		if(deletedOffer==null)
		{
			logger.error("Offer not found having id "+id);
			throw new OfferNotFound();
		}
		offerRepository.delete(deletedOffer);
		logger.error("Exit from delete offer");
		return deletedOffer;
	}

	@Override
	@Transactional
	public List<Offer> findAll() {
		
		logger.debug("Entered in findAll() offer");
		return offerRepository.findAll();
	}
//For pagination
	@Override
	@Transactional
	public Page<Offer> findPage(int pageNum,int pageSize,int category) 
	{
		logger.debug("Entered in findPage ");
		Page<Offer> requestedPage=null;
		if(category>=1)
		{
			Pageable pageSpecification = new PageRequest(pageNum, pageSize);
			Productcategory productcategory=new Productcategory();
			productcategory.setId(category);
			requestedPage = offerRepository.findPageByProductcategoryId(category, pageSpecification);
		}
		else
		{
			requestedPage=offerRepository.findAll(new PageRequest(pageNum, pageSize));
		}
		logger.debug("Exit from findPage");
		return requestedPage;
		 
	}
	
	/**
     * Returns a new object which specifies the the wanted result page.
     * @param pageIndex The index of the wanted result page
     * @return
     */
   /* private Pageable constructPageSpecification(int pageIndex) {
        Pageable pageSpecification = new PageRequest(pageIndex, NUMBER_OF_PERSONS_PER_PAGE, sortByLastNameAsc());
        return pageSpecification;
    }*/
	
	@Override
	@Transactional(rollbackFor=OfferNotFound.class)
	public Offer update(Offer offer) throws OfferNotFound {
		logger.debug("Entered in update(Offer offer) by offerId "+offer.getId());
		Offer offerToUpdate=offerRepository.findOne(offer.getId());
		
		if(offerToUpdate==null)
		{
			logger.error("Offer not found with offerId "+offer.getId());
			throw new OfferNotFound();
		}
			
		logger.error("Exit from update(Offer offer)");
		return offerToUpdate;
	}

	@Override
	@Transactional
	public Offer findById(int id) 
	{
		logger.debug("Entered in findById(int id)");
		Offer offer=offerRepository.findOne(id);
		if(offer!=null)
		{
			Hibernate.initialize(offer.getPlace());
			Place place=offer.getPlace();
			
			if(place!=null)
			{
				offer.setPlace(place);
			}
		}
		return offer;
	}

	@Override
	public List<Offer> getAllOffersByMall(int placeId) 
	{
		logger.debug("Entered in getAllOffersByMall(int mallId)");
		return offerRepository.findByPlaceId(placeId);
	}

	@Override
	public long getOfferCountByHotspot(int placeId)
	{
		logger.debug("Entered in getOfferCountByHotspot(int placeId)");
		return ((BigInteger)offerRepository.getOfferCountByHotspot(placeId)).longValue();
	}
	@Override
	public long getOfferCountByPlace(int placeId)
	{
		logger.debug("Entered in getOfferCountByPlace(int placeId)");
		return ((BigInteger)offerRepository.getOfferCountByPlace(placeId)).longValue();
	}
	
	@Override
	public List<String> getProductNames(int placeId,String product)
	{
		logger.debug("Entered in getProductNames(int placeId,String product)");
		return offerRepository.getProductNames(placeId, product);
	}
	@Override
	public List<Offer> findPageByMall(int mallId,int pageNum,int pageSize)
	{
		logger.debug("Entered in findPage ");
		List<Offer> requestedPage=null;
		
		requestedPage=offerRepository.findPageByPlaceId( mallId,new PageRequest(pageNum, pageSize));
		
		logger.debug("Exit from findPage");
		 return requestedPage;
	}

	@Override
	@Transactional
	public List<Offer> findByRetailerMallAndProductcategory(int placeId,int categoryId,int pageNum,int pageSize)
	{
		logger.debug("Entered in findPage ");
		List<Offer> requestedPage=null;
		
		if(categoryId>=1)
		{
			Place place=new Place();
			place.setId(placeId);
			Productcategory productcategory=new Productcategory();
			productcategory.setId(categoryId);
			requestedPage=offerRepository.findPageByPlaceIdAndProductcategoryId(placeId,categoryId,new PageRequest(pageNum, pageSize));
		}
		
		logger.debug("Exit from findPage");
		 return requestedPage;
	}
	
	/*@Override
	@Transactional
	public List<Offer> findByPlaceId(int placeId,int pageNum,int pageSize)
	{
		logger.debug("Entered in findByPlaceId(int placeId,int pageNum,int pageSize) ");
		List<Offer> requestedPage=null;
		
		requestedPage=offerRepository.findByPlaceId(placeId,new PageRequest(pageNum, pageSize));
		
		logger.debug("Exit from findByPlaceId(int placeId,int pageNum,int pageSize)");
		 return requestedPage;
	}*/

	//Returns Offer List by hotspots or by hotspots and product category.
	@Override
	@Transactional
	public List<Offer> findByHotspot(int hotspotId ,int productCatId, int pageNum,int pageSize) 
	{
		List<Offer>offerList=null;
		
		if(productCatId>0)
		{
			offerList=offerRepository.findPageByHotspotIdAndProdCategoryId(hotspotId,productCatId, new PageRequest(pageNum, pageSize)).getContent();
		}
		else
		{
			offerList=offerRepository.findPageByHotspotId(hotspotId, new PageRequest(pageNum, pageSize)).getContent();
		}
		return offerList;
	}
	
}
