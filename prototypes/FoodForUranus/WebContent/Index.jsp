<%@ page extends="Database.ConnectionManager"%>
<%@page import="java.util.ArrayList"%>
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

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$(document).ready(function(){
$("#show").click(function(){
$(".dropdowndiv").slideDown(500);
});

$("#hide").click(function(){
$(".dropdowndiv").slideUp(500);
});

});
</script>
<script type="text/javascript">
var tggle='off';

function toggle(name){
if(tggle=='on'){
document.getElementById(name).style.background="url(http://dl.dropbox.com/u/74051124/restaurant.png)";
tggle='off';
}
else {
document.getElementById(name).style.background="url(http://dl.dropbox.com/u/74051124/restaurant%23.png)";
tggle='on';
}
}
</script>
<!-- libraries needed for Google Maps API -->
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBNopqBwFCC2CZdkpPVylS1mn5AQeHj9EM&sensor=false"></script>
<script type="text/javascript" src="GMapConfig.js"></script>
<title>Food for Uranus - Beta</title>
</head>
<!-- initialize map when document gets loaded -->
<body>

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
			<form action="GoogleMapsServlet">
				<input type="text" size="115" name="search"> <input
					type="submit" name="submit" value="Search">
			</form>
			<!-- DROPDOWN -->
			<div class="dropdowndiv">
				<!-- menu--->
				<div id="menu">
					<!-- create checkboxes to set preferences -->
					<!-- <### Not functional ### -->
					<form action="GoogleMapsServlet">
						<div id="key">
							<input type="checkbox" name="pref1" value="1">
							<ki>Meat</ki>
						</div>
						<div id="key">
							<input type="checkbox" name="pref1" value="2">
							<ki>Fish</ki>
						</div>
						<div id="key">
							<input type="checkbox" name="pref1" value="3">
							<ki>Beer</ki>
						</div>
						<div id="key">
							<input type="checkbox" name="pref1" value="4">
							<ki>BliBla</ki>
						</div>
						<div id="key">
							<input type="checkbox" name="pref1" value="5">
							<ki>Unhealthy</ki>
						</div>
						<div id="key">
							<input type="checkbox" name="pref1" value="6">
							<ki>Bar</ki>
						</div>
						<input type="submit" value="Set Preferences" name="submit">
						<input type="button" onclick="initialize()">
					</form>
					<!-- ### Not functional ###> -->
				</div>
				<a href="#" id="hide" title="Hide Div"><mi>less...</mi></a>
			</div>
			<a href="#" id="show" title="drop the div down"><mi>more...</mi></a>
			<!-- implement Google map -->
			<div id="ram">
				<div
					style="width: 100px; height: 400px; background: grey; float: left;">
					<div
						id="toggle1" onclick="toggle('toggle1')" style="background: url(http://dl.dropbox.com/u/74051124/restaurant.png); width: 32px; height: 37px;"></div>
					<div
						id="toggle2" onclick="toggle('toggle2')" style="background: url(http://dl.dropbox.com/u/74051124/restaurant.png); width: 32px; height: 37px;"></div>
					<div
						id="toggle3" onclick="toggle('toggle3')" style="background: url(http://dl.dropbox.com/u/74051124/restaurant.png); width: 32px; height: 37px;"></div>
					<div
						id="toggle4" onclick="toggle('toggle4')" style="background: url(http://dl.dropbox.com/u/74051124/restaurant.png); width: 32px; height: 37px;"></div>
				</div>
				<div id="map" style="width: 692px; height: 400px;"></div>
				<%
					if(null != session.getAttribute("Lat")){
															ArrayList <String> Lat = (ArrayList <String>) request.getSession().getAttribute("Lat");
															ArrayList <String> Lng = (ArrayList <String>) request.getSession().getAttribute("Lng");
															ArrayList <String> Info = (ArrayList <String>) request.getSession().getAttribute("Info");
															ArrayList <String> Name = (ArrayList <String>) request.getSession().getAttribute("Name");
				%>
				<script type="text/javascript">
					var Lat = [];
					var Lng = [];
					var Info = [];
					var Name = [];
				<%for(int i=0;i<Lat.size();i++){%>
					Lat.push("<%=Lat.get(i)%>");
				    Lng.push("<%=Lng.get(i)%>");
				    Info.push("<%=Info.get(i)%>");
				    Name.push("<%=Name.get(i)%>");
				<%}%>
					readXML(Lat, Lng, Info, Name, function() {
					});
				</script>
				<%
					}else{
				%>
				<script type="text/javascript">
					initialize();
				</script>
				<%
					}
				%>
			</div>

			<!-- copyright -->
			<div id="ram">
				<c>&copyGroup 14, Uranus</c>
			</div>

			<!--end of content-->
		</div>

		<!-- end of main-->
	</div>

</body>
</html>