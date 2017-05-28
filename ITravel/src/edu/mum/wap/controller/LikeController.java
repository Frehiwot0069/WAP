package edu.mum.wap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.model.Like;
import edu.mum.wap.model.User;
import edu.mum.wap.service.ILikeService;
import edu.mum.wap.service.impl.LikeServiceImpl;

@WebServlet("/like")
public class LikeController extends Controller {
	private static final long serialVersionUID = 1L;

	//Like
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("postId") == null) {
			response.sendRedirect("error.html");
			return;
		}

		int postID = Integer.parseInt(request.getParameter("postId"));
		User user = (User) request.getSession().getAttribute("user");
		
		ILikeService likeService = new LikeServiceImpl();
		
		Like like = new Like();
		like.setPostId(postID);
		like.setUser(user);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json;

		try {
			if(likeService.existsByPostIdAndUserId(postID, user.getUserId())) {
				likeService.removeLike(like);
				json = "[{\"status\" : \"unliked\", \"count\" : ";
			}
			else {
				like = likeService.addNewLike(like);
				json = "[{\"status\" : \"liked\", \"count\" : ";
			}
			json += likeService.findLikesByPostId(postID).size() + "}]";
			
			out.write(json);
		} catch(SQLException e) {
			callErrorPage(response, e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		callErrorPage(response, new Exception("Unexpected request"));
	}

}
