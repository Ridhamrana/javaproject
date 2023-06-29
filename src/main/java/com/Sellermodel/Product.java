package com.Sellermodel;

public class Product {
	private int Pid,Sid;
	private String Image,Name,MRP,Services,Details;
	public int getPid() {
		return Pid;
	}
	public void setPid(int Pid) {
		this.Pid = Pid;
	}
	public int getSid() {
		return Sid;
	}
	public void setSid(int Sid) {
		this.Sid = Sid;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getMRP() {
		return MRP;
	}
	public void setMRP(String mRP) {
		MRP = mRP;
	}
	public String getServices() {
		return Services;
	}
	public void setServices(String services) {
		Services = services;
	}
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
	@Override
	public String toString() {
		return "Product [Pid=" + Pid + ", Sid=" + Sid + ", Image=" + Image + ", Name=" + Name + ", MRP=" + MRP
				+ ", Services=" + Services + ", Details=" + Details + ", getPid()=" + getPid() + ", getSid()="
				+ getSid() + ", getImage()=" + getImage() + ", getName()=" + getName() + ", getMRP()=" + getMRP()
				+ ", getServices()=" + getServices() + ", getDetails()=" + getDetails() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
