package com.code.model;

import java.io.InputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "filepicture")
@Table(name="filepicture")
public class FileUploadBean  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	@Column(name="pid")
	private long pid;  
	@Column(name="orname")
	private  String orname  ;
	@Column(name="randname")
	private	String  randname ;
	@Column(name="regdate")
	private	String  regdate ;
	@Column(name="exten")
	private	 String exten ;
	@Column(name="path")
	private	 String path ;
	@Column(name="type")	
	private	 String type;
	@Column(name="size")	
	private	int size ;
	@Column(name="prid")
	private	 int  prid ;
	
	@Column(name="bytes")
	private	 byte[]  bytes;
	
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	@Column(name="username")
	private	 String username ;
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getOrname() {
		return orname;
	}
	public void setOrname(String orname) {
		this.orname = orname;
	}
	public String getRandname() {
		return randname;
	}
	public void setRandname(String randname) {
		this.randname = randname;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getExten() {
		return exten;
	}
	public void setExten(String exten) {
		this.exten = exten;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPrid() {
		return prid;
	}
	public void setPrid(int prid) {
		this.prid = prid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
			
	
}
