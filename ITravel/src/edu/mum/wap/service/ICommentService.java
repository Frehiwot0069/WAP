package edu.mum.wap.service;

import java.sql.SQLException;
import java.util.List;

import edu.mum.wap.model.Comment;

public interface ICommentService {
	public Comment addNewComment(Comment comment) throws SQLException;
	public List<Comment> findCommentsByPostId(int postid) throws SQLException;
}
