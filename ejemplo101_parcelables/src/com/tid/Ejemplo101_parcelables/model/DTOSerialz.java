package com.tid.Ejemplo101_parcelables.model;

import java.io.Serializable;

public class DTOSerialz implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name="";
	private String phone="";
	private String address="";
	
	public DTOSerialz(String name, String phone, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}

}
