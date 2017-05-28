package edu.mum.wap.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import edu.mum.wap.service.impl.LikeServiceImpl;

public class Post {
	private int postId;
	private User user;
	private String postText;
	private java.util.Date dateCreated;
	private List<Comment> comments;
	private List<Picture> pictures;
	private List<Like> likes;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public Post() {}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}
	
	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(java.util.Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		boolean exists = false;
		try {
			exists = (new LikeServiceImpl()).existsByPostIdAndUserId(postId, user.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "{  \"postId\" :" + postId + ", "
					+ "\"postText\" : \"" + postText + "\", "
					+ "\"dateCreated\" : \"" + dateCreated + "\", "
					+ "\"user\" : " + user.toString() + ", "
					+ "\"comments\" : [" + comments.stream().map(s->s.toString()).collect(Collectors.joining(", ")) + "], "
					+ "\"likes\" : [" + likes.stream().map(l->l.toString()).collect(Collectors.joining(", ")) + "], "
					+ "\"pictures\" : [" + pictures.stream().map(p->p.toString()).collect(Collectors.joining(", ")) + "],"
					+ "\"liked\" : " + exists
					+ "}";
	}
}
