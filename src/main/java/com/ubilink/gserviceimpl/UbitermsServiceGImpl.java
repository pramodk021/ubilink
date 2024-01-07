package com.ubilink.gserviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubilink.gservice.UbitermsService;
import com.ubilink.model.Ubiterms;
import com.ubilink.repository.UbitermsRepository;

@Service
public class UbitermsServiceGImpl implements UbitermsService {

	@Resource 
	private UbitermsRepository ubitermsRepository;
	@Override
	public void delete(int id)
	{
		ubitermsRepository.delete(id);
	}

	@Override
	public Ubiterms saveAndFlush(Object entity) 
	{
		Ubiterms ubiterms=(Ubiterms)entity;
		return ubitermsRepository.saveAndFlush(ubiterms);
	}

	@Override
	public List<Ubiterms> findAll() {
		// TODO Auto-generated method stub
		return  ubitermsRepository.findAll();
	}

	@Override
	public Ubiterms findOne(int id) {
		// TODO Auto-generated method stub
		return ubitermsRepository.findOne(id);
	}

	@Override
	public long count() {
		return ubitermsRepository.count();
	}

}
