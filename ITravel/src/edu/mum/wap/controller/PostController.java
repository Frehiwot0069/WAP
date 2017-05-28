package edu.mum.wap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.model.Comment;
import edu.mum.wap.model.Like;
import edu.mum.wap.model.Picture;
import edu.mum.wap.model.Post;
import edu.mum.wap.model.User;
import edu.mum.wap.service.IPostService;
import edu.mum.wap.service.impl.PostServiceImpl;
import edu.mum.wap.util.ITravelMarshaller;

@WebServlet("/createPost")
public class PostController extends Controller {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		callUnknownUrl(response);
	}
	
	//Creating new post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getQueryString());
		
		if(request.getParameter("post-text") == null)
			callErrorPage(response, new Exception("Unknown request"));
		
		String postText = request.getParameter("post-text");
		
		User user = (User) request.getSession().getAttribute("user");
		Post post = new Post();
		post.setComments(new ArrayList<Comment>());
		post.setLikes(new ArrayList<Like>());
		post.setPictures(new ArrayList<Picture>());
		post.setPostText(postText);
		post.setUser(user);
		
		IPostService postService = new PostServiceImpl();
		
		try {
			post = postService.addNewPost(post);
		} catch (SQLException e) {
			callErrorPage(response, e);
		}
		
		response.setContentType("application/json");
		response.getWriter().write("["+post.toString()+"]");
	}
}
