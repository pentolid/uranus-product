<%@ page extends="Database.ConnectionManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link REL=StyleSheet HREF="style.css" TYPE="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Add jQuery library -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
<!-- Add fancyBox -->
<link rel="stylesheet"
	href="fancybox/source/jquery.fancybox.css?v=2.1.3" type="text/css"
	media="screen" />
<script type="text/javascript"
	src="fancybox/source/jquery.fancybox.pack.js?v=2.1.3"></script>
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBNopqBwFCC2CZdkpPVylS1mn5AQeHj9EM&sensor=false"></script>
<script type="text/javascript" src="GMapConfig.js"></script>
<title>RDSS Prototype_x01</title>
</head>
<body onload="initialize()">

<div id="main">  
 
<div id="head">  
<h>Food for Uranus</h><h_>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LOGIN / EAT / SHIT</h_>
</div> 


<!-- content-->
<div id="content">  
 <!-- menu--->
<div id="menu">  
	
	<div id="key"><ki>Meat</ki></div>
	<div id="key"><ki>Fish</ki></div>
	<div id="key"><ki>Beer</ki></div>
	 
</div>
<div id="ram">
<div id="map" style="width: 792px; height: 400px;"></div>
</div>

<!-- copyright --> 
<div id="ram">&copyGroup 14, Uranus</div>

<!--end of content-->
</div>
 
<!-- end of main-->
</div > 


	<%-- <div id="index">
		<div id="head">
			<h>Food Finder Lindholmen</h>
		</div>
		<!-- content-->
		<div id="content">
			<form action="DomXML" method="GET">
				Italien<input type="checkbox" name="pref1" value="Italien">
				Asian<input type="checkbox" name="pref2" value="Asian">
				Swedish<input type="checkbox" name="pref3" value="Swedish">
				Greek<input type="checkbox" name="pref4" value="Greek">
				Indian<input type="checkbox" name="pref5" value="Indian">
				<input type="submit" value="Set Preferences">
			</form>
			<%
				if (request.getSession(true).getAttribute("currentSessionUser") == null) {
			%>
			<form action="LoginServlet" method="GET">
				Username:<input type="text" name="uname" /> Password:<input
					type="password" name="password" /> <input type="submit"
					value="login">
			</form>
			<%
				} else {
					out.println("Logged in as: " + session.getAttribute("userName")
							+ " Permission: " + session.getAttribute("permission"));
			%>
			<form action="LogoutServlet">
				<input type="submit" value="Logout">
			</form>
			<%
				}
			%>
			<input type="text" size="88">
			<input type="button" value="Search">
			<div id="ram">
				<div id="map" style="width: 792px; height: 500px;"></div>
			</div>
		</div>
	</div> --%>
</body>
</html>