package edu.mum.wap.service;

import java.sql.SQLException;
import java.util.List;

import edu.mum.wap.model.Picture;

public interface IPictureService {
	public List<Picture> findPicturesByPostId(int postId)  throws SQLException;
}
