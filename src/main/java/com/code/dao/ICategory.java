package com.code.dao;
import com.code.model.CategoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ICategory extends JpaRepository<CategoryBean,Long> {
	
}
