<%-- <%@ page import="main.*" %> --%>

<HTML>

<HEAD>

<TITLE>RDSS</TITLE>
<LINK REL=StyleSheet HREF="style.css" TYPE="text/css" MEDIA=screen>
</HEAD>

<body>
	<%@page import="base.*"%>
	<!-- Google Maps API - Java Script Source -->
	<script type="text/javascript"
		src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBNopqBwFCC2CZdkpPVylS1mn5AQeHj9EM&sensor=false"></script>
	<script type="text/javascript" src="GMapConfig.js"></script>
</HEAD>

<body onload="initializeMap()">
	<%!urUserBeans user;%>
	<FORM name="input" action="ReadBoxes.jsp" method="get"> </FORM>

		<div id="main">

			<div id="head">
				<h>Food Finder Lindholmen</h>
			</div>

			<!-- content-->
			<div id="content">
				<FORM name="input" action="ReadBoxes.jsp" method="get">
					<INPUT TYPE="CHECKBOX" NAME="check1" VALUE="check1" CHECKED>
					Checkbox 1 <INPUT TYPE="CHECKBOX" NAME="check2" VALUE="check2">
					Checkbox 2 <INPUT TYPE="CHECKBOX" NAME="check3" VALUE="check3">
					Checkbox 3 <INPUT TYPE="SUBMIT" VALUE="Submit">
				</FORM>

				<div id="ram">
					<div id="map" style="width: 625px; height: 440px;"></div>

					<!-- end of main-->
				</div>

				<%
					if (request.getSession(true).getAttribute("currentSessionUser") == null) {
				%>
				<form action="LoginServlet">
					Username: <input type="text" name="username" /> Password: <input
						type="password" name="password" /> <input type="submit"
						value="Login">
				</form>

				<a href="Registrate.jsp"> Registrate </a>




				<%
					} else {
				%>
				Logged in as:
				<%
					user = (urUserBeans) request.getSession(true).getAttribute(
								"currentSessionUser");
						out.print(user.getFirstName());
				%>
				<form action="LogoutServlet">
					<input type="submit" value="Logout">
				</form>
				<%
					}
				%>




				<%
					System.out.println("oo");
				%>
				</div>
				</div>
				
			
</BODY>
</HTML>