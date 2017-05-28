<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery.waypoints.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBXk5APxLRBoChcFpUaIqH1XjMKLRB3vQw&callback=initMap">
</script>
<link rel="stylesheet"
	href="http://twitter.github.com/bootstrap/1.3.0/bootstrap.min.css" />
<script type="text/javascript" src="js/gmaps.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="css/map.css" />
<link rel="stylesheet" type="text/css" href="css/weathercard.css"/>
<link rel="stylesheet" type="text/css" href="css/mapdialog.css"/>
</head>
<body>
<a href="#openModal" class="location">Fairfield IA</a><br>
<a href="#openModal" class="location">Urumqi China</a><br>
<a href="#openModal" class="location">London UK</a><br>
<a href="#openModal" class="location">Tokya Japan</a><br>
<a href="#" >Tokya Japan</a><br>
	<div id="openModal" class="modalDialog">
		<div>
			<a href="#close" title="Close" class="close">X</a>
			<div class="row">
				<div class="span11">
					<form method="post" id="geocoding_form">
						<label for="address">Address:</label>
						<div class="input">
							<input type="text" id="address" name="address" /> <input
								type="submit" class="btn" value="Search" />
						</div>
					</form>
					<div id="map"></div>


					<div class="weather_five_days">

						<div class="container_weather">
							<div class="row">
								<div class="col">
									<div class="weather-card one">
										<div class="top" id="top_1">
											<div class="wrapper">
												<h1 class="heading" id="weather1">Clear night</h1>
												<div><img id="weather_icon_1" src=""></div>
												<div><h3 class="location" id="city1">Dhaka, Bangladesh</h3></div>
												
												<p class="temp">
													<span class="temp-value" id="temp1">20</span> <span
														class="deg">0</span> <a href="javascript:;"><span
														class="temp-type">C</span></a>
												</p>
											</div>
										</div>
										<div class="bottom">
											<div class="wrapper">
												<ul class="forecast">

													<li class="active"><span class="date" id="date1">Yesterday</span>

													</li>

												</ul>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>

						<div class="container_weather">
							<div class="row">
								<div class="col">
									<div class="weather-card one">
										<div class="top" id="top_2">
											<div class="wrapper">

												<h1 class="heading" id="weather2">Clear night</h1>
												<div><img id="weather_icon_2" src=""></div>
												<h3 class="location" id="city2">Dhaka, Bangladesh</h3>
												<p class="temp">
													<span class="temp-value" id="temp2">20</span> <span
														class="deg">0</span> <a href="javascript:;"><span
														class="temp-type">C</span></a>
												</p>
											</div>
										</div>
										<div class="bottom">
											<div class="wrapper">
												<ul class="forecast">

													<li class="active"><span class="date" id="date2">Yesterday</span>

													</li>

												</ul>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
						
						<div class="container_weather">
							<div class="row">
								<div class="col">
									<div class="weather-card one">
										<div class="top" id="top_3">
											<div class="wrapper">

												<h1 class="heading" id="weather3">Clear night</h1>
												<div><img id="weather_icon_3" src=""></div>
												<div><h3 class="location" id="city3">Dhaka, Bangladesh</h3></div>
												<p class="temp">
													<span class="temp-value" id="temp3">20</span> <span
														class="deg">0</span> <a href="javascript:;"><span
														class="temp-type">C</span></a>
												</p>
											</div>
										</div>
										<div class="bottom">
											<div class="wrapper">
												<ul class="forecast">

													<li class="active"><span class="date" id="date3">Yesterday</span>

													</li>

												</ul>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
						
						<div class="container_weather">
							<div class="row">
								<div class="col">
									<div class="weather-card one">
										<div class="top" id="top_4">
											<div class="wrapper">

												<h1 class="heading" id="weather4">Clear night</h1>
												<div><img id="weather_icon_4" src=""></div>
												<div><h3 class="location" id="city4">Dhaka, Bangladesh</h3></div>
												<p class="temp">
													<span class="temp-value" id="temp4">20</span> <span
														class="deg">0</span> <a href="javascript:;"><span
														class="temp-type">C</span></a>
												</p>
											</div>
										</div>
										<div class="bottom">
											<div class="wrapper">
												<ul class="forecast">

													<li class="active"><span class="date" id="date4">Yesterday</span>

													</li>

												</ul>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
						
					<div class="container_weather">
							<div class="row">
								<div class="col">
									<div class="weather-card one">
										<div class="top" id="top_5">
											<div class="wrapper">

											<h1 class="heading" id="weather5">Clear night</h1>
												<div><img id="weather_icon_5" src=""></div>
												<div><h3 class="location" id="city5">Dhaka, Bangladesh</h3></div>
												<p class="temp">
													<span class="temp-value" id="temp5">20</span> <span
														class="deg">0</span> <a href="javascript:;"><span
														class="temp-type">C</span></a>
												</p>
											</div>
										</div>
										<div class="bottom">
											<div class="wrapper">
												<ul class="forecast">

													<li class="active"><span class="date" id="date5">Yesterday</span>

													</li>

												</ul>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>


					</div>



				</div>
			</div>
		</div>
	</div>
</body>
</html>