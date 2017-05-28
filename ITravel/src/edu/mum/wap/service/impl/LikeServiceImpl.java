package edu.mum.wap.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.wap.datasource.DBConnection;
import edu.mum.wap.model.Like;
import edu.mum.wap.model.User;
import edu.mum.wap.service.ILikeService;

public class LikeServiceImpl implements ILikeService {

	PreparedStatement ps;
	
	@Override
	public Like addNewLike(Like like) throws SQLException {
		String query = "INSERT INTO likes (userid, postid) VALUES (?, ?)";
		ps = DBConnection.getConnection().conn.prepareStatement(query);
		ps.setInt(1, like.getUser().getUserId());
		ps.setInt(2, like.getPostId());
		ps.executeUpdate();
		
		ps.close();
		
		return like;
	}

	@Override
	public Like removeLike(Like like) throws SQLException {
		String query = "DELETE FROM likes WHERE userid=? AND postid=?";
		ps = DBConnection.getConnection().conn.prepareStatement(query);
		ps.setInt(1, like.getUser().getUserId());
		ps.setInt(2, like.getPostId());
		ps.executeUpdate();
		
		ps.close();
		
		return like;
	}

	@Override
	public List<Like> findLikesByPostId(int postId) throws SQLException {
		String query = "SELECT likeid, postid, u.userid, u.fullname, u.gender"
				+ " FROM likes INNER JOIN users u ON likes.userid=u.userid WHERE postid=? ORDER BY likes.datecreated";
		List<Like> likes = new ArrayList<Like>();
		ps = DBConnection.getConnection().conn.prepareStatement(query);
		ps.setInt(1, postId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
			likes.add(
					new Like(rs.getInt("likeid"),
							new User(rs.getInt("userid"),
									rs.getString("fullname"),
									rs.getString("gender"), "", "", "", ""), postId));
		rs.close();
		ps.close();
		
		return likes;
	}

	@Override
	public boolean existsByPostIdAndUserId(int postId, int userId) throws SQLException {
		String query = "SELECT * FROM likes WHERE postId=? AND userId=?";
		
		ps = DBConnection.getConnection().conn.prepareStatement(query);
		ps.setInt(1, postId);
		ps.setInt(2, userId);
		ResultSet rs = ps.executeQuery();
		boolean exists = rs.next();
		
		rs.close();
		ps.close();
		
		return exists;
	}



}
