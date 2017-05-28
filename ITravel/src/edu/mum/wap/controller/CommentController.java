package edu.mum.wap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.model.Comment;
import edu.mum.wap.model.User;
import edu.mum.wap.service.ICommentService;
import edu.mum.wap.service.impl.CommentServiceImpl;

@WebServlet("/comment")
public class CommentController extends Controller {
	private static final long serialVersionUID = 1L;

	public CommentController() {

	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		callErrorPage(response, new Exception("Wrong request exception"));
	}

	//Inserting new comment
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getParameter("postId") == null || request.getParameter("commentText") == null) {
			response.sendRedirect("error.html");
			return;
		}
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		String commentText = (String) request.getParameter("commentText");
		User user = (User) request.getSession(false).getAttribute("user");
		
		ICommentService commentService = new CommentServiceImpl();
		
		Comment comment = new Comment();
		comment.setComment(commentText);
		comment.setPostId(postId);
		comment.setUser(user);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		try {
			comment = commentService.addNewComment(comment);
			String json = "[" + comment.toString() + "]";
			out.write(json);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
	}
}
