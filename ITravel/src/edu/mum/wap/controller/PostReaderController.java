package edu.mum.wap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.mum.wap.model.Post;
import edu.mum.wap.service.ICommentService;
import edu.mum.wap.service.ILikeService;
import edu.mum.wap.service.IPictureService;
import edu.mum.wap.service.IPostService;
import edu.mum.wap.service.impl.CommentServiceImpl;
import edu.mum.wap.service.impl.LikeServiceImpl;
import edu.mum.wap.service.impl.PictureServiceImpl;
import edu.mum.wap.service.impl.PostServiceImpl;

@WebServlet("/posts")
public class PostReaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IPostService postService = new PostServiceImpl();
	private ICommentService commentService = new CommentServiceImpl();
	private ILikeService likeService = new LikeServiceImpl();
	private IPictureService pictureService = new PictureServiceImpl();
	
    public PostReaderController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int start = request.getParameter("start") == null ? 0 : Integer.parseInt(request.getParameter("start"));
		int count = request.getParameter("count") == null ? 25 : Integer.parseInt(request.getParameter("count"));
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("login.jsp?need-sign");
			return;
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		try {
			List<Post> posts = postService.findPosts(start, count);
			
			for(Post p : posts) {
				p.setComments(commentService.findCommentsByPostId(p.getPostId()));
				p.setLikes(likeService.findLikesByPostId(p.getPostId()));
				p.setPictures(pictureService.findPicturesByPostId(p.getPostId()));
			}
			String json = "[" + posts.stream().map(p -> p.toString()).collect(Collectors.joining(", ")) + "]";
			out.write(json);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
	}

}
