package com.code.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.model.RoleListBean_R001;
@Repository
public interface IRoleListRepository extends JpaRepository<RoleListBean_R001,Long>{

}


