<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit / create restaurants</title>
<style>
.left {
	width: 30%;
	float: left;
	text-align: left;
	
}

.right {
	width: 50%;
	margin-left: 10px;
	float: right;
	

}
</style>
</head>
<script>
	function fail() {
		alert("You have entered wrong login information. Please try again!");
	}
</script>
<body>
	<%
		if (request.getSession(true).getAttribute("wrong") != null) {
	%>
	<script>
		fail()
	</script>
	<%
		System.out.println("popup");
			session.removeAttribute("wrong");
		}
	%>


	<form action="editServlet">
		
		<div style="float: left;">
			<div>
				<div class="left">Name</div>
				<div class="right">
					<input type="text" name="name">
				</div>
			</div>



			<div>
				<div class="left">Streetname</div>
				<div class="right">
					<input type="text" name="StreetName">
				</div>
			</div>
			<div>
				<div class="left">Streetnumber</div>
				<div class="right">
					<input type="text" name="Streetnumber">
				</div>
			</div>
			<div>
				<div class="left">Telephone</div>
				<div class="right">
					<input type="text" name="TelephoneNo">
				</div>
			</div>
			<div>
				<div class="left">Email</div>
				<div class="right">
					<input type="text" name="Email">
				</div>
			</div>
			<div>
				<div class="left">Homepage</div>
				<div class="right">
					<input type="text" name="Homepage">
				</div>
			</div>
			<div>
				<div class="left">Description</div>
				<div class="right">
					<input type="text" name="Description">
				</div>
			</div>
			<div>
				<div class="left">Opening</div>
				<div class="right">
					<input type="text" name="Opening">
				</div>
			</div>
			<div>
				<div class="left">Closing</div>
				<div class="right">
					<input type="text" name="Closing">
				</div>
			</div>
			
		</div>
	<br><br><br><br><br><br><br><br><br><br>
	<div class="left"><input type="submit" name= "submit" value ="Create / Edit"></div>




	</form>



</body>
</html>