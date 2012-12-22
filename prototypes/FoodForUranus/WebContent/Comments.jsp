<%@page import="Database.ConnectionManager"%>
<%@page import="org.apache.catalina.connector.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="sorttable.js"></script>
<title>Comments</title>
</head>
<body>

	<form id="comment" method="GET" action="CommentServlet">
		Enter your name: <input type="text" name="name">Rating: <select
			name="rating">
			<%
				for (int i = 0; i <= 5; i++) {
			%>
			<option value="<%=i%>"><%=i%></option>
			<%
				}
			%>
		</select> <br>
		<textarea name="comments" cols="45" rows="5">Enter your comments here...</textarea>
		<br> <input type="submit" value="Submit" />
		<input type="hidden" name="rID" value="<%= request.getParameter("rID") %>" >
		<hr>
	</form>

	<table class="sortable" border="1" cellpadding="3" cellspacing="0">
		<tr>
			<th>Comment</th>
			<th>Rating</th>
			<th>Date</th>
		</tr>
		<%
			String rID = request.getParameter("rID");
			//System.out.println("JSP: " + rID);
			try {
				Statement st = ConnectionManager.getCon().createStatement();
				ResultSet rs = st
						.executeQuery("SELECT rtComment, rtPoints, rtDate FROM tblRatings, tblRestaurants WHERE tblRestaurants.rID = tblRatings.rID AND tblRestaurants.rID = "
								+ rID + " ORDER BY rtPoints DESC;");

				while (rs.next()) {
					String desc = rs.getString(1);
					String desc1 = rs.getString(2);
					String desc2 = rs.getString(3);
		%>
		<tr>
			<td><%=desc%></td>
			<td><%=desc1%></td>
			<td><%=desc2%></td>
		</tr>
		<%
			}

				// Close the database objects

				rs.close();
				st.close();
		%>
	</table>
	<%
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	%>
</body>
</html>