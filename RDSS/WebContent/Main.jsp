<%-- <%@ page import="main.*" %> --%>

<HTML>
<HEAD>
<TITLE>RDSS</TITLE>
</HEAD>
<BODY>
 	
<%-- <%@ include file="d.jsp" %> --%>
	
	<%!

// 		urMain m = new urMain();
	%>

	<FORM name="input" action="ReadBoxes.jsp" method="get">
		<INPUT TYPE="CHECKBOX" NAME="check1" VALUE="check1" CHECKED>
		Checkbox 1 <BR> <INPUT TYPE="CHECKBOX" NAME="check2"
			VALUE="check2"> Checkbox 2 <BR> <INPUT TYPE="CHECKBOX"
			NAME="check3" VALUE="check3"> Checkbox 3 <BR> <INPUT
			TYPE="SUBMIT" VALUE="Submit">
	</FORM>

</BODY>
</HTML>