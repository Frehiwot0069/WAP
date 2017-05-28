<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Sign-Up/Login Form</title>
		<link
			href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'
			rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="css/login.normalize.min.css">
		<link rel="stylesheet" href="css/login.style.css">
		<script src="js/login.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="form">
			<ul class="tab-group">
				<li class="tab active"><a href="#login">Log In</a></li>
				<li class="tab"><a href="#signup">Sign Up</a></li>
			</ul>
			<div class="tab-content">
				<div id="login">
					<h1>Welcome</h1>
					<%
						if(request.getParameter("exists") != null) {%>
							<div id="error">* The user has already been registered before</div>					
						<%}
					
						if(request.getParameter("need-sign") != null) {%>
							<div id="warning">* You must be logged in</div>					
						<%}
						
						if(request.getParameter("invalid") != null) {%>
							<div id="warning">* Invalid combination of e-mail and password</div>					
						<%}
					%>
	
					<form action="login" method="post">
	
						<div class="field-wrap">
							<label>Email Address<span class="req">*</span>
							</label> <input id="username" name="username" type="email" required
								autocomplete="off" />
						</div>
						<div class="field-wrap">
							<label> Password<span class="req">*</span>
							</label> <input id="pwd" name="password" type="password" required
								autocomplete="off" />
						</div>
						<p class="forgot">
							<a href="#">Forgot Password?</a>
						</p>
						<button class="button button-block">Log In</button>
					</form>
				</div>
				<div id="signup">
					<h1>Sign Up for Free</h1>
					<div id="must-match" class="error" style="display: none">* Password and password confirm does not match</div>
	
					<form id="sign-up-form" action="users" method="post">
						<div class="field-wrap">
							<label> Full Name<span class="req">*</span>
							</label> <input id="fullName" name="fullName" type="text" required
								autocomplete="off" />
						</div>

						<div class="top-row">
							<div class="field-wrap">
								<label> Gender<span class="req">*</span></label>
								<select id="gender" name="gender" required>
									<option disabled selected value=""></option>
									<option id="male" name="male">Male</option>
									<option id="female" name="female">Female</option>
								</select>
							</div>
	
							<div class="field-wrap">
								<label> Birthday (mm/dd/yyyy)<span class="req">*</span>
								</label> <input id="birthday" name="birthday" type="text" required
									autocomplete="off" />
							</div>
						</div>
	
						<div class="field-wrap">
							<label> Email Address<span class="req">*</span>
							</label> <input id="email1" name="email" type="email" required
								autocomplete="off" />
						</div>
	
						<div class="field-wrap">
							<label> Set A Password<span class="req">*</span>
							</label> <input id="password1" name="password" type="password" required
								autocomplete="off" />
						</div>
	
						<div class="field-wrap">
							<label> Repeat A Password<span class="req">*</span>
							</label> <input id="repeat" name="password" type="password" required
								autocomplete="off" />
						</div>
	
						<div class="top-row">
							<div class="field-wrap">
								<label> Street<span class="req">*</span>
								</label> <input id="street" name="street" type="text" required
									autocomplete="off" />
							</div>
							
							<div class="field-wrap">
								<label> City<span class="req">*</span>
								</label> <input id="city" name="city" type="text" required
									autocomplete="off" />
							</div>
							
						</div>
						<div class="top-row">	
							<div class="field-wrap">
								<label> State/Province/Region<span class="req">*</span>
								</label> <input id="state" name="state" type="text" required
									autocomplete="off" />
							</div>
							
							<div class="field-wrap">
								<label> ZIP<span class="req">*</span>
								</label> <input id="zipCode" name="zipCode" type="text" required
									autocomplete="off" />
							</div>
						</div>
						<button type="submit" class="button button-block">Get
							Started</button>
					</form>
				</div>
	
			</div>
			<!-- tab-content -->
	
		</div>
		<!-- /form -->
		<script
			src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	
		<script src="js/login.js"></script>
	
	</body>
</html>