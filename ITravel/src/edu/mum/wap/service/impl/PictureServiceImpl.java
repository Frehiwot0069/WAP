package edu.mum.wap.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.wap.datasource.DBConnection;
import edu.mum.wap.model.Picture;
import edu.mum.wap.service.IPictureService;

public class PictureServiceImpl implements IPictureService {

	private PreparedStatement ps;
	
	@Override
	public List<Picture> findPicturesByPostId(int postId) throws SQLException {
		String query = "SELECT pictureId, src, postId FROM pictures WHERE postId=?";
		List<Picture> pictures = new ArrayList<Picture>();
		ps = DBConnection.getConnection().conn.prepareStatement(query);
		ps.setInt(1, postId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
			pictures.add(new Picture(rs.getInt("pictureId"), rs.getString("src"), postId));
		return pictures;
	}

}
