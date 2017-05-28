package edu.mum.wap.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import edu.mum.wap.datasource.DBConnection;
import edu.mum.wap.model.Post;
import edu.mum.wap.model.User;
import edu.mum.wap.service.IPostService;

public class PostServiceImpl implements IPostService {
	PreparedStatement ps;

	@Override
	public Post addNewPost(Post post) throws SQLException {
		
		String query = "INSERT INTO posts (userid, post) VALUES(?, ?)";
		ps = DBConnection.getConnection().conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, post.getUser().getUserId());
		ps.setString(2, post.getPostText());
		ps.execute();
		
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			post.setPostId(rs.getInt(1));
			post.setDateCreated(new java.util.Date());
		}
		
		rs.close();
		ps.close();
		
		return post;
	}

	@Override
	public List<Post> findPosts(int start, int count) throws SQLException {
		String query = "SELECT postid, post, DATE_FORMAT(p.datecreated, '%Y-%m-%d %H:%i:%s') as dateCreated, "
				+ "u.userid, u.fullname, u.gender, u.street, u.city, u.state, u.zipcode "
				+ "FROM posts p INNER JOIN users u ON p.userid=u.userid "
				+ "ORDER BY p.datecreated DESC LIMIT ?, ?";
		System.out.println(query);
		ps = DBConnection.getConnection().conn.prepareStatement(query);
		ps.setInt(1, start);
		ps.setInt(2, count);
		
		ResultSet rs = ps.executeQuery();
		List<Post> posts = new ArrayList<Post>();
		Post post;

		while(rs.next()) {
			post = new Post();
			post.setPostId(rs.getInt("postId"));
			post.setUser(new User(rs.getInt("userid"),
					rs.getString("fullname"),
					rs.getString("gender"),
					rs.getString("state"),
					rs.getString("city"),
					rs.getString("street"),
					rs.getString("zipcode")));
			post.setPostText(rs.getString("post"));
			post.setDateCreated(rs.getTimestamp("datecreated"));
			posts.add(post);
		}
		
		rs.close();
		ps.close();
		
		return posts;
	}
}
