package com.code.model;

import java.util.ArrayList;
import java.util.List;


public class PostProductBean_C001 {
	private String catgcd;
	private String title;
	private double price;
	private String desc;
	private String regdate;
	
	private List<FileUploadBean> inRec = new ArrayList<FileUploadBean>();
	
	private boolean isChk;
	
	private String country;
	private String province;
	private String detail;
	private String cphone;
	private String usercd;
	private String prcd;


	
	public String getCatgcd() {
		return catgcd;
	}
	public void setCatgcd(String catgcd) {
		this.catgcd = catgcd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	public List<FileUploadBean> getInRec() {
		return inRec;
	}
	public void setInRec(List<FileUploadBean> inRec) {
		this.inRec = inRec;
	}
	
	public boolean isChk() {
		return isChk;
	}
	public void setChk(boolean isChk) {
		this.isChk = isChk;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getUsercd() {
		return usercd;
	}
	public void setUsercd(String usercd) {
		this.usercd = usercd;
	}
	public String getPrcd() {
		return prcd;
	}
	public void setPrcd(String prcd) {
		this.prcd = prcd;
	}
	
	
	
}
