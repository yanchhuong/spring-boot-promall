package com.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dao.ILocationMapRepository;
import com.code.model.LocatMapBeanIn_C001;
import com.code.model.LocatMapBeanIn_R001;
import com.code.model.LocatMapBeanOut_R001;
import com.code.service.ILocationMapService;

@Service 
public class LocationMapServiceImpl implements ILocationMapService{
	private ILocationMapRepository iLocationMapRepository;
	
	@Autowired
	public LocationMapServiceImpl(ILocationMapRepository iLocationMapRepository){
		this.iLocationMapRepository=iLocationMapRepository;
	}

	@Override
	public List<LocatMapBeanOut_R001> getLocationMapList(LocatMapBeanIn_R001 input) {
		// TODO Auto-generated method stub
		return this.iLocationMapRepository.getLocationMapList(input);
	}

	@Override
	public void addLocationMap(LocatMapBeanIn_C001 input) {
		 this.iLocationMapRepository.addLocationMap(input);
		
	}



}
