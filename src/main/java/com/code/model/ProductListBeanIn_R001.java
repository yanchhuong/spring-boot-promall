package com.code.model;

public class ProductListBeanIn_R001 {
	private String sprice ;
	private String eprice ;
	private String enabled;
	private String regdate;
	private String keyword; //
	/*ud.fname
	  ud.lname
	  p.description
	  p.url
	  p.title
	  sp.store_nm)
    */

	public String getEnabled() {
		return enabled;
	}
	public String getSprice() {
		return sprice;
	}
	public void setSprice(String sprice) {
		this.sprice = sprice;
	}
	public String getEprice() {
		return eprice;
	}
	public void setEprice(String eprice) {
		this.eprice = eprice;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
