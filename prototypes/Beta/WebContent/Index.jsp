<%@ page extends="Database.ConnectionManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link REL=StyleSheet HREF="style.css" TYPE="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- libraries needed for "fancybox" -->
<!-- Add jQuery library -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
<!-- Add fancyBox -->
<link rel="stylesheet"
	href="fancybox/source/jquery.fancybox.css?v=2.1.3" type="text/css"
	media="screen" />
<script type="text/javascript"
	src="fancybox/source/jquery.fancybox.pack.js?v=2.1.3"></script>
	<!-- libraries needed for Google Maps API -->
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBNopqBwFCC2CZdkpPVylS1mn5AQeHj9EM&sensor=false"></script>
<script type="text/javascript" src="GMapConfig2.js"></script>
<title>Food for Uranus - Beta</title>
</head>
<!-- initialize map when document gets loaded -->
<body onload="initialize()">

	<div id="main">
		<div id="head">
			<div style="float: left; width: 67%;">
				<h>Food for Uranus</h>
			</div>
			<div>
			<!-- check for a current session -->
				<%
					if (request.getSession(true).getAttribute("currentSessionUser") == null) {
				%>
				<!-- if null then show login objects -->
				<form action="LoginServlet" method="get" name="login"
					style="position: absolute; top: 20px; left: 800px;">
					<a href='javaScript:document.login.submit()'><h_>LOGIN /</h_></a> <br>
					<input type="text" name="uname" value="username" size="14" /> <input
						type="password" name="password" value="password" size="14" />

				</form>
				<%
					} else {
				%>
				<!-- else show logout option -->
				<form action="LogoutServlet" method="get" name="logout"
					style="position: absolute; top: 20px; left: 800px;">
					<a href='javaScript:document.logout.submit()'><h_>LOGOUT /</h_></a>
				</form>
				<%
					}
				%>
				<!-- create links for further menu options -->
				<!-- ### <Not functional ### -->
				<form action="EditServlet" method="get" name="edit"
					style="position: absolute; top: 20px; left: 910px;">
					<a href='javaScript:document.edit.submit()'><h_>EDIT /</h_></a>
				</form>
				<form action="AboutServlet" method="get" name="about"
					style="position: absolute; top: 20px; left: 1000px;">
					<a href='javaScript:document.about.submit()'><h_>ABOUT </h_></a>
				</form>
				<!-- ### Not functional> ### -->
			</div>
		</div>
		<!-- content-->
		<div id="content">
			<div id="ram">
				<!-- menu--->
				<div id="menu">
				<!-- create checkboxes to set preferences -->
				<!-- <### Not functional ### -->
					<form action="LoginServlet">
						<div id="key">
							<input type="checkbox" name="pref1" value="Italien">
							<ki>Meat</ki>
						</div>
						<div id="key">
							<input type="checkbox" name="pref1" value="Italien">
							<ki>Fish</ki>
						</div>
						<div id="key">
							<input type="checkbox" name="pref1" value="Italien">
							<ki>Beer</ki>
						</div>
						<div id="key">
							<input type="checkbox" name="pref1" value="Italien">
							<ki>Thai</ki>
						</div>
						<div id="key">
							<input type="checkbox" name="pref1" value="Italien">
							<ki>Unhealthy</ki>
						</div>
						<div id="key">
							<input type="checkbox" name="pref1" value="Italien">
							<ki>Bar</ki>
						</div>
						<input type="submit" value="Set Preferences">
					</form>
					<!-- ### Not functional ###> -->
				</div>
			</div>
			<!-- implement Google map -->
			<div id="ram">
				<div id="map" style="width: 792px; height: 400px;"></div>
			</div>

			<!-- copyright -->
			<div id="ram"><c>&copyGroup 14, Uranus</c></div>

			<!--end of content-->
		</div>

		<!-- end of main-->
	</div>

</body>
</html>