package edu.mum.wap.service;

import java.sql.SQLException;

import edu.mum.wap.model.User;

public interface IUserService {

	public User addNewUser(User user) throws SQLException;
	public User updateUser(User user) throws SQLException;
	public User findUserByUsername(String username) throws SQLException;
	public boolean existsByUsername(String username, int notUserId) throws SQLException;

}
