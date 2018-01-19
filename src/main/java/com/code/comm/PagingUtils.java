package com.code.comm;

import java.util.ArrayList;

public class PagingUtils {
	
	private int totalRows;
	private int totalPages;
	private int pageSize;
	private int pageNo;
	PAGINATION pagination;
	
	public PAGINATION getPagination() {
		return pagination;
	}

	public void setPagination(PAGINATION pagination) {
         this.pagination = pagination;
		
		if(pagination != null) {
			pageSize = Integer.parseInt(StringUtils.null2void(pagination.getPageSize(), "15"));
			pageNo =  Integer.parseInt(StringUtils.null2void(pagination.getPageNo(), "1"));
			
			pagination.setPageSize(String.valueOf(pageSize));
			pagination.setPageNo(String.valueOf(pageNo));
		}
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getOffset() {
		
		return pageSize * (pageNo - 1);
	//	return pageSize * (Integer.parseInt(getPagination().getPageNo()) - 1);
	}
	public PagingUtils() {
		pageSize = 15;
		pageNo = 1;
	}
	public PagingUtils(PAGINATION pagination, String totalRows) {
		this(pagination);
		setTotalRows(totalRows);
	}
	public void setTotalRows(String str) {
		this.totalRows = Integer.parseInt(str);
		setTotalPages((int)Math.abs(Math.ceil(new Integer(totalRows).doubleValue() / new Integer(pageSize).doubleValue())));
		
		pagination.setTotalRows(String.valueOf(this.totalRows));
		pagination.setTotalPages(String.valueOf(this.totalPages));
	}

	public PagingUtils(PAGINATION pagination) {
		this();
		setPagination(pagination);
	}

	public StringBuffer getDynamic(String dynamic) {
		StringBuffer dQuery = new StringBuffer(dynamic);
		ArrayList<String> dParam = new ArrayList<String>();
		dQuery.append("\n LIMIT " + getPageSize() + " OFFSET " + getOffset());
		
		return dQuery;
	}
	
	public DynamicDAOData getDynamic() {
		DynamicDAOData dynamic = new DynamicDAOData();
		dynamic.setSQL("\n LIMIT " + getPageSize() + " OFFSET " + getOffset());
		return dynamic;
	}
	
}

