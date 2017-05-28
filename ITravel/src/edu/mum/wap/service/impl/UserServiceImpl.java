package edu.mum.wap.service.impl;

import java.net.URLDecoder;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.mysql.jdbc.Statement;

import edu.mum.wap.datasource.DBConnection;
import edu.mum.wap.model.User;
import edu.mum.wap.service.IUserService;

public class UserServiceImpl implements IUserService {
	PreparedStatement ps;


	@Override
	public User updateUser(User user) throws SQLException {
		String query = "UPDATE Users SET fullname=?, gender=?, state=?, city=?, street=?, zipcode=?, email=? WHERE userid=?";
		
		ps = DBConnection.getConnection().conn.prepareStatement(query);
		ps.setString(1, user.getFullName());
		ps.setString(2, user.getGender());
		ps.setString(3, user.getState());
		ps.setString(4, user.getCity());
		ps.setString(5, user.getStreet());
		ps.setString(6, user.getZipCode());
		ps.setString(7, URLDecoder.decode(user.getEmail()));
		ps.setInt(8, user.getUserId());
		
		ps.executeUpdate();
		
		ps.close();
		
		return user;
	}

	@Override
	public User addNewUser(User user) throws SQLException {
		String query = "INSERT INTO Users (fullname, gender, state, city, street, zipcode, birthdate, email, password) values(?,?,?,?,?,?,?,?,?)";
		
		ps = DBConnection.getConnection().conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, user.getFullName());
		ps.setString(2, user.getGender());
		ps.setString(3, user.getState());
		ps.setString(4, user.getCity());
		ps.setString(5, user.getStreet());
		ps.setString(6, user.getZipCode());
		ps.setDate(7, new java.sql.Date(user.getBirthDate().getTime()));
		ps.setString(8, URLDecoder.decode(user.getEmail()));
		ps.setString(9, user.getPassword());
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next())
			user.setUserId(rs.getInt(1));
		
		rs.close();
		ps.close();
		return user;
	}

	@Override
	public User findUserByUsername(String username) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("SELECT userid, fullname, gender, state, city, street, zipcode, birthdate, email, password pwd, datecreated FROM Users WHERE email = ?");
		ps.setString(1, URLDecoder.decode(username));
		ResultSet rs = ps.executeQuery();
		User user = new User();
		if (rs.next()) {
			user.setUserId(rs.getInt("userid"));
			user.setFullName(rs.getString("fullname"));
			user.setGender(rs.getString("gender"));
			user.setState(rs.getString("state"));
			user.setCity(rs.getString("city"));
			user.setStreet(rs.getString("street"));
			user.setZipCode(rs.getString("zipcode"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("pwd"));
			user.setBirthDate(rs.getDate("birthdate"));
			user.setDateCreated(rs.getDate("datecreated"));
			
			rs.close();
			ps.close();
			
			return user;
		}
		return null;
	}

	@Override
	public boolean existsByUsername(String username, int notUserId) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("SELECT * FROM Users WHERE email = ? AND userId<>?");
		ps.setString(1, URLDecoder.decode(username));
		ps.setInt(2, notUserId);
		ResultSet rs = ps.executeQuery();
		boolean exists = rs.next();
		
		rs.close();
		ps.close();
		return exists;
	}

}
