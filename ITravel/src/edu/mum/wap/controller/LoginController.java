package edu.mum.wap.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.mum.wap.model.User;
import edu.mum.wap.service.ILoginService;
import edu.mum.wap.service.IUserService;
import edu.mum.wap.service.impl.LoginServiceImpl;
import edu.mum.wap.service.impl.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends Controller {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getParameter("username") == null || request.getParameter("password") == null) {
			response.sendRedirect("error.html");
			return;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		ILoginService loginService = new LoginServiceImpl();
		IUserService userService = new UserServiceImpl();
		try {
			if(loginService.IsUserRegistered(username, password)) {
				User user = userService.findUserByUsername(username);
				request.getSession().setAttribute("user", user);
				response.sendRedirect("main");
			} else {
				response.sendRedirect("login.jsp?invalid");
			}
		} catch (SQLException e) {
			callErrorPage(response, e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
}
