package com.ubilink.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubilink.model.Ubiterms;
import com.ubilink.repository.UbitermsRepository;

@Service
public class UbiTermServiceImpl implements UbiTermService
{

	
	@Resource
	private UbitermsRepository ubiTermRepo;
	
	private static final Logger logger=Logger.getLogger(UbiTermServiceImpl.class);
	@Override
	@Transactional
	public List<Ubiterms> getUbiTerms() 
	{
		logger.debug("Entered in getUbiTerms()");
		List<Ubiterms> ubiterms=ubiTermRepo.findAll();
		logger.debug("Exit from getUbiTerms() with result:= "+ubiterms);
		return ubiterms;
	}

}
