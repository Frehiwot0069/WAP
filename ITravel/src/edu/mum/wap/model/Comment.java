package edu.mum.wap.model;

import java.util.Date;

public class Comment {
	private int commentId;
	private int postId;
	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String comment;
	private Date dateCreated;

	public Comment() {
		
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "{\"commentId\" :" + commentId + ", \"postId\" : " + postId + ", \"comment\" : \"" + comment + "\", \"dateCreated\" : \""
				+ dateCreated + "\", \"user\" : " + user.toString() + "}";
	}
}
