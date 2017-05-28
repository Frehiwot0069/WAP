package edu.mum.wap.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.model.User;
import edu.mum.wap.service.IUserService;
import edu.mum.wap.service.impl.UserServiceImpl;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet("/profile")
public class ProfileController extends Controller {
	private static final long serialVersionUID = 1L;

    public ProfileController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		callUnknownUrl(response);
	}

	//updating profile
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getQueryString());
		if(request.getParameter("fullname") == null ||
				request.getParameter("gender") == null ||
				request.getParameter("email") == null ||
				request.getParameter("street") == null ||
				request.getParameter("city") == null ||
				request.getParameter("state") == null ||
				request.getParameter("zip") == null) {
			callErrorPage(response, new Exception("Unexpected request."));
			return;
		}
		
		User user = new User();
		user.setFullName(request.getParameter("fullname"));
		user.setGender(request.getParameter("gender"));
		user.setEmail(request.getParameter("email"));
		user.setStreet(request.getParameter("street"));
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state"));
		user.setZipCode(request.getParameter("zip"));
		user.setUserId(((User) request.getSession().getAttribute("user")).getUserId());
		user.setBirthDate(((User) request.getSession().getAttribute("user")).getBirthDate());
		
		response.setContentType("application/json");
		IUserService userService = new UserServiceImpl();
		try {
			if(userService.existsByUsername(user.getEmail(), user.getUserId())) {
				response.getWriter().write("[{ \"status\" : false }]");
				return;
			}
			user = userService.updateUser(user);
		} catch (SQLException e) {
			callErrorPage(response, e);
		}
		response.getWriter().write("[" + user.toString() + "]");
	}

}
