package com.code.dao;
import java.util.List;

import com.code.model.LocatMapBeanIn_C001;
import com.code.model.LocatMapBeanIn_R001;
import com.code.model.LocatMapBeanOut_R001;
import com.code.model.ProvinceBean_R001;
public interface ILocationMapRepository {
	List<LocatMapBeanOut_R001> getLocationMapList(LocatMapBeanIn_R001 input);
    void addLocationMap(LocatMapBeanIn_C001 input);
    List<ProvinceBean_R001> getProvinceList();
   
}
