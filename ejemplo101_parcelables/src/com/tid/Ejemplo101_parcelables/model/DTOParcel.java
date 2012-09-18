package com.tid.Ejemplo101_parcelables.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DTOParcel implements Parcelable {

	private String name="";
	private String phone="";
	private String address="";

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public DTOParcel(String name, String phone, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	public DTOParcel(Parcel in){
		String[] data = new String[3];
		in.readStringArray(data);
		this.name = data[0];
		this.phone = data[1];
		this.address = data[2];
	}
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		 dest.writeStringArray(new String[] {this.name,
				                                    this.phone,
				                                      this.address});

	}
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {


		public DTOParcel createFromParcel(Parcel in) {
			return new DTOParcel(in); 
		}

		public DTOParcel[] newArray(int size) {
			return new DTOParcel[size];
		}
	};

}
