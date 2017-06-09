package com.code.dao.impl;
import org.hibernate.Session;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.code.dao.ICategory;
import com.code.model.CategoryBean;

@Repository
public class CategoryDaoImp  implements ICategory {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImp.class);
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addCategory(CategoryBean p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Category saved successfully, Category Details="+p);
		
	}

	@Override
	public void updateCategory(CategoryBean p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Category updated successfully, Category Details="+p);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryBean> listCategorys() {
		Session session = this.sessionFactory.getCurrentSession();
		List<CategoryBean> CategorysList = session.createQuery("from Category").list();
		for(CategoryBean p : CategorysList){
			logger.info("Category List::"+p);
		}
		return CategorysList;
	}

	@Override
	public CategoryBean getCategoryById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		CategoryBean p = (CategoryBean) session.load(CategoryBean.class, new Integer(id));
		logger.info("Category loaded successfully, Category details="+p);
		return p;
	}

	@Override
	public void removeCategory(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		CategoryBean p = (CategoryBean) session.load(CategoryBean.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Category deleted successfully, Category details="+p);
		
	}

}
