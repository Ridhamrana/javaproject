package com.CustomerModel;

public class Cart {
	private int id,Sid,Cid,Pid;
	private String Image,Name,MRP,Details;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSid() {
		return Sid;
	}
	public void setSid(int sid) {
		Sid = sid;
	}
	public int getCid() {
		return Cid;
	}
	public void setCid(int cid) {
		Cid = cid;
	}
	public int getPid() {
		return Pid;
	}
	public void setPid(int pid) {
		Pid = pid;
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
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
	@Override
	public String toString() {
		return "CartModel [id=" + id + ", Sid=" + Sid + ", Cid=" + Cid + ", Pid=" + Pid + ", Image=" + Image + ", Name="
				+ Name + ", MRP=" + MRP + ", Details=" + Details + "]";
	}
	
}
