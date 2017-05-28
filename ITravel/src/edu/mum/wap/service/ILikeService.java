package edu.mum.wap.service;

import java.sql.SQLException;
import java.util.List;

import edu.mum.wap.model.Like;

public interface ILikeService {
	public Like addNewLike(Like like) throws SQLException;
	public Like removeLike(Like like) throws SQLException;
	public List<Like> findLikesByPostId(int postId) throws SQLException;
	public boolean existsByPostIdAndUserId(int postId, int userId) throws SQLException;

}
