package com.code.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="articles")
public class CategoryBean implements Serializable { 
	private static final long serialVersionUID = 1L;
	public int getCatgid() {
		return catgid;
	}

	public void setCatgid(int catgid) {
		this.catgid = catgid;
	}

	public String getCatgcd() {
		return catgcd;
	}

	public void setCatgcd(String catgcd) {
		this.catgcd = catgcd;
	}

	public String getNm_eng() {
		return nm_eng;
	}

	public void setNm_eng(String nm_eng) {
		this.nm_eng = nm_eng;
	}

	public String getNm_kh() {
		return nm_kh;
	}

	public void setNm_kh(String nm_kh) {
		this.nm_kh = nm_kh;
	}

	public String getParent_cd() {
		return parent_cd;
	}

	public void setParent_cd(String parent_cd) {
		this.parent_cd = parent_cd;
	}

	public String getLvl() {
		return lvl;
	}

	public void setLvl(String lvl) {
		this.lvl = lvl;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUsercd() {
		return usercd;
	}

	public void setUsercd(String usercd) {
		this.usercd = usercd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="catgid")
        private int catgid;  
	@Column(name="catgcd")
        private String catgcd;
	@Column(name="nm_eng")	
	private String nm_eng;
	
	@Column(name="nm_kh")	
	private String nm_kh;
	
	@Column(name="parent_cd")	
	private String parent_cd;
	@Column(name="lvl")	
	private String lvl;
	
	@Column(name="pid")	
	private String pid;
	
	@Column(name="usercd")	
	private String usercd;
	
	
}  