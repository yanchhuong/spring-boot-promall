package com.code.dao;
import com.code.model.CategoryBean;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface ICategory extends JpaRepository<CategoryBean,Long>{	
	 @Query(value=" WITH RECURSIVE category_tree AS "
			 + " (SELECT *,CAST(nm_eng As varchar(1000)) As fullengname,CAST(nm_kh As varchar(1000)) As fullkhname"
			 + " FROM category  WHERE parentid = 0"
			 + " UNION all  SELECT si.*, CAST( sp.fullengname || '-->' || si.nm_eng As varchar(1000)) As fullengname ,"
			 + " CAST( sp.fullkhname || '-->' ||  si.nm_kh As varchar(1000)) As fullkhname"
			 + " FROM category As si INNER JOIN category_tree AS sp"
			 + " ON (si.parentid = cast(sp.catgid as integer))  "
			 + " )SELECT * FROM category_tree ORDER BY fullengname;",nativeQuery = true)
	 public List<CategoryBean> findAlls();

	 //Modify
	 @Modifying(clearAutomatically = true)
	 @Query(value="update category set  nm_eng=COALESCE(:#{#category.nm_eng},nm_eng),"
	 		+ "  nm_kh=COALESCE(:#{#category.nm_kh},nm_kh),"
	 		+ "  pid=COALESCE(:#{#category.pid}, pid),"
	 		+ "  usercd=COALESCE(:#{#category.usercd},usercd)"
	 		+ "  where catgid= :#{#category.catgid}", nativeQuery=true)
	 public void updateMenu(@Param(value="category") CategoryBean category); 
	 
	 //Count 
	 @Query(value="select count (catgid) from category ",nativeQuery=true)
	 public int getCatgidCount(); 
	 
	 //Count Seq
	 @Query(value="select count (catgid) from category where lvl=:lvl and parentid=:parentid",nativeQuery=true)
	 public int getSeqCount(@Param(value="lvl") String lvl,@Param(value="parentid") long parentid); 
	 
	 
	 // Remove Tree
	 @Transactional
	 @Modifying
	 @Query(value=" with recursive all_posts as ("
		 		+ "   select catgid, parentid, catgid as rootid from category t1"
		 		+ "   union all"
		 		+ "     select c1.catgid,c1.parentid,p.rootid"
		 		+ "   from category c1 join all_posts p on p.catgid = c1.parentid) "
		 		+ "     DELETE FROM category WHERE catgid IN (SELECT catgid FROM all_posts WHERE rootid=:rootid);", nativeQuery=true)
	 public int removeMenuTree(@Param(value="rootid") int rootid); 
	 
	 
              
}
