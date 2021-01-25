package com.login.entity;

public class id_entity {
	
	private String userid;
	private String profileimg="";

	public String getProfileimg() {
		return profileimg;
	}

	public void setProfileimg(String profileimg) {
		this.profileimg = profileimg;
	}

	public id_entity() {
		super();
	}
	
	public id_entity(String userid) {
		super();
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	
	

}
