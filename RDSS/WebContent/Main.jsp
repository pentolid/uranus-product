<%-- <%@ page import="main.*" %> --%>

<HTML>

<HEAD>

<TITLE>RDSS</TITLE>
<LINK REL=StyleSheet HREF="style.css" TYPE="text/css" MEDIA=screen>
<!-- Google Maps API - Java Script Source -->
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBNopqBwFCC2CZdkpPVylS1mn5AQeHj9EM&sensor=false"></script>
<script type="text/javascript" src="GMapConfig.js"></script>

</HEAD>

<body onload="initializeMap()">

	<FORM name="input" action="ReadBoxes.jsp" method="get">

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
					<div id="map" style="width: 480px; height: 480px;"></div>

					<!-- end of main-->
				</div>
				<form action="LoginServlet">
					Please enter your username <input type="text" name="username" /><br>
					Please enter your password <input type="password" name="password" />
					<input type="submit" value="submit">
				</form>
				<%
					System.out.println("oo");
				%>
			
</BODY>
</HTML>