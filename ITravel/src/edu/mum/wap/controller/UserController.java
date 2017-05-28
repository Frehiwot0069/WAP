package edu.mum.wap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.model.User;
import edu.mum.wap.service.IUserService;
import edu.mum.wap.service.impl.UserServiceImpl;
import edu.mum.wap.util.ITravelMarshaller;

@WebServlet("/users")
public class UserController extends Controller {
	private static final long serialVersionUID = 112335355L;
	
	
	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		callUnknownUrl(response);
	}

	@Override //User creation
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User newUser = new User();
		IUserService userService = new UserServiceImpl();
		try {
			if(request.getParameter("fullName") == null
					|| request.getParameter("gender") == null 
					|| request.getParameter("birthday") == null
					|| request.getParameter("email") == null
					|| request.getParameter("password") == null
					|| request.getParameter("street") == null
					|| request.getParameter("city") == null
					|| request.getParameter("state") == null
					|| request.getParameter("zipCode") == null) {
				callErrorPage(response, new Exception("Unexpected request"));
				return;
			}
			newUser.setGender(request.getParameter("gender"));
			newUser.setFullName(request.getParameter("fullName"));
			newUser.setBirthDate(new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("birthday")));
			newUser.setEmail(request.getParameter("email"));
			newUser.setPassword(request.getParameter("password"));
			newUser.setStreet(request.getParameter("street"));
			newUser.setCity(request.getParameter("city"));
			newUser.setState(request.getParameter("state"));
			newUser.setZipCode(request.getParameter("zipCode"));
			if(userService.findUserByUsername(newUser.getEmail()) != null) {
				response.sendRedirect("login.jsp?exists");
				return;
			}
			newUser = userService.addNewUser(newUser);
		} catch(ParseException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
			return;
		}
		request.getSession().setAttribute("user", newUser);
		response.sendRedirect("main");
	}
}
