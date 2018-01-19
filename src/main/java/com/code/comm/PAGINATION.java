package com.code.comm;

public class PAGINATION {
	private String PageNo ;
    private String PageSize ;
    private String TotalRows;
    private String TotalPages ;
	public String getPageNo() {
		return PageNo;
	}
	public void setPageNo(String pageNo) {
		PageNo = pageNo;
	}
	public String getPageSize() {
		return PageSize;
	}
	public void setPageSize(String pageSize) {
		PageSize = pageSize;
	}
	public String getTotalRows() {
		return TotalRows;
	}
	public void setTotalRows(String totalRows) {
		TotalRows = totalRows;
	}
	public String getTotalPages() {
		return TotalPages;
	}
	public void setTotalPages(String totalPages) {
		TotalPages = totalPages;
	}
}
