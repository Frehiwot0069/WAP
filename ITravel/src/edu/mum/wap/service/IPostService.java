package edu.mum.wap.service;

import java.sql.SQLException;
import java.util.List;

import edu.mum.wap.model.Post;
import edu.mum.wap.model.User;

public interface IPostService {
	public Post addNewPost(Post post) throws SQLException;
	public List<Post> findPosts(int start, int count) throws SQLException;
}