package edu.mum.wap.model;

public class Picture {
	private int picId;
	private String src;
	private int postId;
	
	public Picture(int picId, String source, int postId) {
		this.picId = picId;
		this.src = source;
		this.postId = postId;
	}
	
	public int getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	@Override
	public String toString() {
		return "{ \"picId\" : " + picId + ", \"src\" : \"" + src + "\", \"postId\" : " + postId + "}";
	}
	
}
