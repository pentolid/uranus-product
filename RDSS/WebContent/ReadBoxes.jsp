

<!--         NOTE: Right now im just testing this, but...

	The User will briefly be taken to this page where the checkboxes will be read and the java class
	usSelections selections will be updated, as well as the result set . Then the users will be 
	redirected to the main view again. /Kristoffer
 -->
<%--     <%@ page import="main.urSelection" %> --%>
    <%!
//     	urSelection selection=urSelection.getSelection();
    %>    
        <%
            if(request.getParameter("check1") != null) {
            	//selection.foods.Fish.select();
            	out.println("Checkbox 1 was checked.<BR>");
            }
            else {
                out.println("Checkbox 1 was not checked.<BR>");
            }

            if(request.getParameter("check2") != null) {
                out.println("Checkbox 2 was checked.<BR>");
            }
            else {
                out.println("Checkbox 2 was not checked.<BR>");
            }

            if(request.getParameter("check3") != null) {
                out.println("Checkbox 3 was checked.<BR>");
            }
            else {
                out.println("Checkbox 3 was not checked.<BR>");
            }
        %>
