<%-- <%@ page import="main.*" %> --%>

<HTML>

<HEAD>

<TITLE>RDSS</TITLE>
<LINK REL=StyleSheet HREF="style.css" TYPE="text/css" MEDIA=screen>
</HEAD>

<body>







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
					<div id="map">MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP
						MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP
						MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP
						MAP MAP MAP MAP MAP MAP MAP MAP MAP MAP</div>

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