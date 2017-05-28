<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ITravel</title>
<link href="css/home.css" rel="stylesheet" type="text/css" >
</head>
<body>
    <img id="image" alt="" src="images/itravellife.png">
    <div id="welcome">
    <span> Welcome
   		<select name="value">
	    	<option selected> Merhawi</option>
			<option>Edit Profile</option>
			<option>LogOut</option>
		</select>
	</span>
	<br>
	<span id="weather" ><a href="">Weather</a></span>
	</div>
	<% int count=0; %>
	<% count++; %>
	<%=count %>
	
	
	
</body>
</html>