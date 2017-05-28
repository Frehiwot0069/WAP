package edu.mum.wap.service.impl;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.mum.wap.datasource.DBConnection;
import edu.mum.wap.model.Comment;
import edu.mum.wap.model.Post;
import edu.mum.wap.model.User;
import edu.mum.wap.service.ICommentService;

public class CommentServiceImpl implements ICommentService {

	private PreparedStatement ps;

	@Override
	public Comment addNewComment(Comment comment) throws SQLException {
		
		comment.setDateCreated(new Date());
		
		String query = "INSERT INTO comments (postid, userid, comment) VALUES (?, ?, ?)";
		ps = DBConnection.getConnection().conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				
		ps.setInt(1, comment.getPostId());
		ps.setInt(2, comment.getUser().getUserId());
		ps.setString(3, comment.getComment());
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next())
			comment.setCommentId(rs.getInt(1));
		
		return comment;
	}

	@Override
	public List<Comment> findCommentsByPostId(int postId) throws SQLException {
		List<Comment> postComments = new ArrayList<Comment>();
		
		String query = "SELECT commentId, postId, comment, comments.datecreated, "
				+ "u.userid, u.fullname, u.gender, u.street, u.city, u.state, u.zipcode "
				+ "FROM comments INNER JOIN users u ON comments.userid=u.userid WHERE postId=?";
		
		ps = DBConnection.getConnection().conn.prepareStatement(query);
		ps.setInt(1, postId);
		ResultSet rs = ps.executeQuery();
		Comment comment;
		while (rs.next()) {
			comment = new Comment();
			comment.setCommentId(rs.getInt("commentId"));
			comment.setPostId(rs.getInt("postId"));
			comment.setComment(rs.getString("comment"));
			comment.setDateCreated(rs.getTimestamp("datecreated"));
			comment.setUser(new User(rs.getInt("userid"),
					rs.getString("fullname"),
					rs.getString("gender"),
					rs.getString("state"),
					rs.getString("city"),
					rs.getString("street"),
					rs.getString("zipcode")));
			postComments.add(comment);
		}
		return postComments;
	}
}
