package edu.mum.wap.model;

public class Like {
	private int likeId;
	private User user;
	private int postId;

	public Like() {}

	public Like(int likeID, User user, int postId) {
		 this.likeId = likeID;
		 this.user = user;
		 this.postId = postId;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "{ \"likeId\" : " + likeId + ", \"user\" : " + user.toString() + ", \"postId\" : " + postId + "}";
	}
	
	
}
