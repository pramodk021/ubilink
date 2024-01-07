/*package com.ubilink.service;

import org.springframework.stereotype.Service;

@Service
public class RetailerImpl //implements RetailerService
{
	
	Logger logger=Logger.getLogger(RetailerImpl.class);
	@Resource
	private RetailerRepository retailerRepository;
	@Override
	public Retailer create(Retailer retailer) {
		logger.debug("Entered in create(Retailer retailer)");
		return retailerRepository.save(retailer);
	}

	@Override
	@Transactional(rollbackFor=RetailerNotFound.class)
	public Retailer delete(int id) throws RetailerNotFound {
		Retailer deletedRetailer=retailerRepository.findOne(id);
		if(deletedRetailer==null)
		{
			throw new RetailerNotFound();
		}
		retailerRepository.delete(deletedRetailer);
		return deletedRetailer;
	}

	@Override
	@Transactional
	public List<Retailer> findAll() {
		logger.debug("Entered in findAll() retailers");
		return retailerRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor=RetailerNotFound.class)
	public Retailer update(Retailer retailer) throws RetailerNotFound {
		
		Retailer retailerToUpdate=retailerRepository.findOne(retailer.getId());
		
		if(retailerToUpdate==null)
			throw new RetailerNotFound();
		
		retailerToUpdate.setAddress(retailer.getAddress());
		mallToUpdate.setName(place.getName());
		mallToUpdate.setLocation(place.getLocation());
		mallToUpdate.setUsers(place.getUsers());
		mallToUpdate.setEvents(place.getEvents());
		mallToUpdate.setRetailers(place.getRetailers());
		mallToUpdate.setDescription(place.getDescription());
		
		return retailerToUpdate;
	}

	@Override
	@Transactional
	public Retailer findById(int id) 
	{
		logger.debug("Entered in findById(int id) "+id );
		return retailerRepository.findOne(id);
	}

	@Override
	public Retailer getRetailerByOffer(int offerId) 
	{
		logger.debug("Entered in getRetailerByOffer(int offerId) "+offerId );
		return retailerRepository.getRetailerByOffer(offerId);
	}

}
*/