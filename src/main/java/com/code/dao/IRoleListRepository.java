package com.code.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.code.model.RoleListBean_R001;
@Repository
public interface IRoleListRepository extends JpaRepository<RoleListBean_R001,Long>{
	 @Transactional
	 @Modifying
	 @Query(value="DELETE FROM roles_list WHERE role=:role", nativeQuery=true)
	public void removeRoleListByRole(@Param(value="role") String input);

}


