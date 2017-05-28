<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

  <meta charset="UTF-8">
  <title>Weather layer</title>
  <link rel="stylesheet" type="text/css" href="weather.css">

  <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js"></script>

  <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVqCOrYKw58RqXIvpkg50-8Tmspnd1AZM&libraries=places&callback"></script>
  
 <!--  <script type="text/javascript">
	  var currentUserCity = ${user.city};
	  var currentUserState =  $("#userState").val();
	  var currentUserCountry =  $("#userCountry").val();
	  var currentUserZip =  $("#userZipCode").val();
	  alert("hello"+currentUserCity);
  </script> -->


</head>
<body>
  <div style="margin: 40px;">
    <h4>5 days forecast:</h4> <div id="fdForecast">30 C </div>
  </div>
  <div style="margin: 40px;">
    <label>Search Weather:</label><input id="pac-input" class="controls" type="text" placeholder="Please Enter a location">
  </div>
  <div id="map-canvas"></div>
  
 <%--  <input type="text" id="userCity" placeholder="${user.city}" >
  --%>  <input type="hidden" id="userCity" value="${user.city}" > 
  <%-- <input type="hidden"  id="userCountry" value="${user.country}" > 
  <input type="hidden" id="userZipCode" value="${user.zipCode}" > 
  <input type="hidden" id="userState" value="${user.state}" >  --%>
  
  <script type="text/javascript" src="weather.js"></script> 

</body>
</html>