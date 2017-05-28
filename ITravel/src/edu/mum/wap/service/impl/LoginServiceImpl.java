package edu.mum.wap.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import edu.mum.wap.datasource.DBConnection;
import edu.mum.wap.service.ILoginService;

public class LoginServiceImpl implements ILoginService {

	PreparedStatement ps;

	@Override
	public boolean IsUserRegistered(String username, String password) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("SELECT email, password FROM users WHERE email=? AND password=?");
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();

		boolean registered = rs.next();
		
		rs.close();
		ps.close();
		
		return registered;
	}
}
