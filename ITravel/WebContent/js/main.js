$(document).ready(function() { //onload
	retrievePosts();
	
	$(window).scroll(function() {
		   if($(window).scrollTop() + $(window).height() == $(document).height()) {
		       //alert(postCount);
			   retrieveNextPosts();
			   //alert(postCount)
		   }
		});
});

var postCount = 0;

function retrieveNextPosts() {
	$.ajax("posts", {
		"type" : "POST",
		"data" : {
			"start" : postCount,
			"count" : 15
		}
	})
	.done(printPosts)
	.fail(function() {
		alert("Request failed. Post")
	});
}

function retrievePosts() {
	$.ajax("posts", {
		"type" : "POST"
	})
	.done(printPosts)
	.fail(function() {
		alert("Request failed. Post")
	});
}

function printPosts(json) {
	$.each(json, function(i, post) {
		insertPost(post, "append");
	});
};

function insertPost(post, mode) {
	postCount++;
	if(mode == "append")
		$("#posts").append("<div id=\""+ post.postId +"\" class=\"post\"></div>"); //<div id="37" class="post"></div>
	else
		$("#posts").prepend("<div id=\""+ post.postId +"\" class=\"post\"></div>");
	$("#"+post.postId).append("<div class=\"pic\"><img src=\"images/img-" + post.user.gender + ".png\" /></div>");
	$("#"+post.postId).append("<div class=\"user\">" + post.user.fullName + "</div>");
	$("#"+post.postId).append("<div class=\"datetime\">" + post.dateCreated + "</div>");
	$("#"+post.postId).append("<div class=\"content\">" + post.postText + "</div>");
	
	$("#"+post.postId).append("<div id=\"addr" + post.postId + "\" class=\"addr\"></div>");		
	$("#addr" + post.postId).append("<div id=\"ico\"><img src=\"images/loc.png\" /></div>");
	//$("#addr" + post.postId).append("<div id=\"loc\"><a href=\"#\">" + post.user.street + ", " + post.user.city + ", " + post.user.state + ", United States " + post.user.zip + "</a></div><hr />");
	
	$("#addr" + post.postId).append("<div id=\"loc\">" + post.user.street + ", " + post.user.city + ", " + post.user.state + ", United States <a href=\"#openModal\" class=\"location\">" + post.user.zip + "</a></div><hr />");
	
	$("#"+post.postId).append("<div id=\"control" + post.postId + "\" class=\"control\"></div>");
	
	$("#control"+post.postId).append("<span class=\"like\"><a id=\"likeButton"+post.postId+"\" href=\"javascript:likeClicked("+post.postId+")\">" + ((post.liked) ? "Unlike" : "Like") + "</a></span>");
	$("#control"+post.postId).append("<span class=\"like\"><a href=\"javascript:commentClicked("+post.postId+");\">Comment</a></span>");
	$("#control"+post.postId).append("<span class=\"like\"><a href=\"\">Pictures</a></span>");
	$("#control"+post.postId).append("<span id=\"likeInfo"+post.postId + "\" class=\"like\">"+post.likes.length+" like(s)</span>");
	
	$("#control"+post.postId).append("<div id=\"com"+post.postId+"\" style=\"display: none\" class=\"com-main\"></div>");
	$("#com"+post.postId).append("<span style=\"font-weight: bold; color: #359; margin: 0px;\">Comment</span><input id=\"newComment"+post.postId+"\" type=\"text\" class=\"textbox\"><input class=\"button\" type=\"button\" value=\"Submit\" onclick=\"submitComment("+post.postId+")\" >");
	
	if(post.comments.length > 0) {
		$("#"+post.postId).append("<div id=\"comments"+post.postId+"\" class=\"comments\"></div>");		
		for(i in post.comments) {
			$("#comments"+post.postId).append("<div id=\"comment"+post.comments[i].commentId+"\" class=\"comment\"></div>");
			$("#comment"+post.comments[i].commentId).append("<div class=\"cpic\"><img src=\"images/little-" +post.comments[i].user.gender + ".png\" /></div>");
			
			$("#comment"+post.comments[i].commentId).append("<div class=\"comment-name\">"+post.comments[i].user.fullName+"</div>");
			$("#comment"+post.comments[i].commentId).append("<div class=\"comment-text\">"+post.comments[i].comment+"</div>");
			$("#comment"+post.comments[i].commentId).append("<div class=\"comment-date\">"+post.comments[i].dateCreated+"</div>");
		}
	}
}

/*================LIKE Clicked=================*/
var tempPostId;
function likeClicked(postId) {
	tempPostId=postId;
	$.ajax("like", {
		"type" : "POST",
		"data" : {
			"postId" : postId
		}
	})
	.done(printLike)
	.fail(printError);
}

function printError(xhr, status, exception) {
	console.log(xhr, status, exception);
}

function printLike(json) {
	$.each(json, function(i, likeInfo) {
		$("#likeInfo"+tempPostId).empty();
		$("#likeInfo"+tempPostId).append(likeInfo.count + " like(s)");
		
		$("#likeButton"+tempPostId).empty();
		$("#likeButton"+tempPostId).append(likeInfo.status == "unliked" ? "Like" : "Unlike");
	});
}
/*===================Comment======================*/

function appendComment(postId, comment) {
	$("#comments"+postId).append("<div id=\"comment"+comment.commentId+"\" class=\"comment\"></div>");
	$("#comment"+comment.commentId).append("<div class=\"cpic\"><img src=\"images/little-" +comment.user.gender + ".png\" /></div>");
	
	$("#comment"+comment.commentId).append("<div class=\"comment-name\">"+comment.user.fullName+"</div>");
	$("#comment"+comment.commentId).append("<div class=\"comment-text\">"+comment.comment+"</div>");
	$("#comment"+comment.commentId).append("<div class=\"comment-date\">"+comment.dateCreated+"</div>");
}

function submitComment(postId) {
	if($("#newComment"+postId).val() == null){ 
		alert("Enter comment!");
	} else if($("#newComment"+postId).val() == '') {
		alert("Enter comment!");
	} else {
		tempPostId = postId;
		$.ajax("comment", {
			"type" : "POST",
			"data" : {
				"postId" : postId,
				"commentText" : $("#newComment"+postId).val()
			}
		})
		.done(appendNewComment)
		.fail(function() {
			alert("Request failed.")
		});
	}
}

function commentClicked(postId) {
	if($("#com" + postId).css("display") == "none")
		$("#com" + postId).css("display", "block");
	else
		$("#com" + postId).css("display", "none");
}

function appendNewComment(json) {
	$.each(json, function(i, comment) {
		appendComment(tempPostId, comment);
	});
	$("#newComment"+tempPostId).val('');
	commentClicked(tempPostId);
}

function showInputForm() {
	if($("#new-post-form").css("display") == "none") {
		$("#new-post-form").css("display", "block");
		$("#new-post-button").css("display", "none");
	} else {
		$("#new-post-form").css("display", "none");
		$("#new-post-button").css("display", "block");
	}
}

/*=============New Post================*/
function createNewPost() {
	if($("#textarea#new-content").val() == "") {
		alert("Insert text of post");
	} else {
		$.ajax("createPost", {
			"type" : "POST",
			"data" : {
				"post-text" : $("textarea#new-content").val()
			}
		})
		.done(prependPost)
		.fail(function() {
			alert("Request failed.")
		});
	}
}

function prependPost(json) {
	$.each(json, function(i, post) {
		insertPost(post, "prepend");
	});
	showInputForm();
	$("textarea#new-content").val("");
}


function editClicked() {
	$("#user-info").css("display", "none");
	$("#edit-profile").css("display", "block");
}

function cancelEditProfile() {
	$("#user-info").css("display", "block");
	$("#edit-profile").css("display", "none");
}

function sendNewProfile() {	
	$.ajax("profile" , {
		"type" : "POST",
		"data" : {
			"fullname" : $("#edit-fullname").val(),
			"gender" : $("#edit-gender").val(),
			"email" : $("#edit-email").val(),
			"street" : $("#edit-street").val(),
			"city" : $("#edit-city").val(),
			"state" : $("#edit-state").val(),
			"zip" : $("#edit-zip").val()
		}
	})
	.done(printNewProfile)
	.fail(printError)
}

function printNewProfile(json) {
	$("#edit-fullname").val(json[0].fullname);
	$("#edit-gender").val(json[0].gender);
	$("#edit-email").val(json[0].email);
	$("#edit-street").val(json[0].street);
	$("#edit-city").val(json[0].city);
	$("#edit-state").val(json[0].state);
	$("#edit-zip").val(json[0].zip);
	
	$("#fullname").html(json[0].fullname);
	$("#gender").html(json[0].gender);
	$("#email").html(json[0].email);
	$("#fulladdress").html(json[0].street + ", " + json[0].city + ", " + json[0].state + ", United States " + json[0].zip);
	
	$("#user-info").css("display", "block");
	$("#edit-profile").css("display", "none");
}