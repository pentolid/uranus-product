package base;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** * Servlet implementation class LoginServlet */
@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
/*
 * ##########################################################################
 * +++urLogServlet+++
 * Description: This class will handle all login sessions.
 * 		- faulty logins,
 * 		- correct logins
 *
 * Created by: 		Joakim Bajoul Kakaei
 * Creation date: 	2012-11-14
 * ##########################################################################
 */
public class urLogServlet extends HttpServlet {

	/*
	 * Takes the login information submited by the user, where "username" and "password are 
	 * the 'id':s gathered from the form submission form,  and processes it.
	 * If the login-credentials are valid, the user will be marked as logged in.
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		try {
			
			urUserBeans user = new urUserBeans();
			/*
			 *  Takes the login id submitted by the user in the .jsp file where the login is located.
			 *  These id's are: "username" and "password".
			 */
			user.setUserName(request.getParameter("username"));	
			user.setPassword(request.getParameter("password"));
			
			user = urUserDAO.login(user);
			if (user.isValid()) { // Correct credentials will be processed here:
				 HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", user);
				response.sendRedirect("userLogged.jsp");  
			} else //invalid credentials will be processed here:
				
				response.sendRedirect("invalidLogin.jsp");
			
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

}
