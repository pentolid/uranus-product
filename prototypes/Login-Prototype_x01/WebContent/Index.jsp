<%@ page extends="Database.ConnectionManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script>
function failLogin()
{
alert ("You have entered wrong login information. Please try again!");
}
</script>
</head>
<body>
	<%
		if (request.getSession(true).getAttribute("currentSessionUser") == null) {
	%>
	<form name="actionForm" action="LoginServlet" method="GET">
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="uname" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="submit"></td>
			</tr>
		</table>
	</form>
	<% if (request.getSession(true).getAttribute("failLogger") != null) {%>
<script> failLogin() </script>
<% System.out.println ("popup"); 
session.setAttribute("failLogger", null);
} %>
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
</body>
</html>