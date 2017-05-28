package edu.mum.wap.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void callErrorPage(HttpServletResponse response, Exception e) throws IOException {
		e.printStackTrace();
		response.sendRedirect("error.html");
	}
	
	protected void callUnknownUrl(HttpServletResponse response) throws IOException {
		response.sendRedirect("notfound.html");
	}

}
