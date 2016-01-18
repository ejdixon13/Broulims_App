package com.example.broulims_1.helperClasses;

import java.io.Serializable;

public class Store implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8504036610681552921L;
	
	private String cityLocation;
	private String state;
	private String address;
	
	public Store(String cityLoc) {
		cityLocation = cityLoc;
	}
	
	public String getCityLocation() {
		return cityLocation;
	}
	public void setCityLocation(String cityLocation) {
		this.cityLocation = cityLocation;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
