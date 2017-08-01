package com.code.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@Entity(name = "RoleListBean")
@Table(name="roles_list")
public class RoleListBean_R001  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id")	
	private long id;
	@Column(name="role")	
	private String role;
	@Column(name="regdate")	
	private String regdate;
	@Column(name="usercd")	
	private String usercd;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
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

}
