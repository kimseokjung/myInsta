package com.follow;

public class follow_entity {

	private int idx;
	private String target;
	private String userid;
	

	public follow_entity() {
		super();
	}
	public follow_entity(int idx, String target, String userid) {
		super();
		this.idx = idx;
		this.target = target;
		this.userid = userid;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}


}
