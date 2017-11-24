package com.code.service;

import java.util.List;

import com.code.model.LocatMapBeanIn_C001;
import com.code.model.LocatMapBeanIn_R001;
import com.code.model.LocatMapBeanOut_R001;

public interface ILocationMapService {
	public List<LocatMapBeanOut_R001> getLocationMapList(LocatMapBeanIn_R001 input);
	public void addLocationMap(LocatMapBeanIn_C001 input);
}
