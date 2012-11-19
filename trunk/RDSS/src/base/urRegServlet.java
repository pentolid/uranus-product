package base;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/RegServlet")
public class urRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		urDatabase udb = new urDatabase();

		String fn = "'" + request.getParameter("First Name") + "',";
		String ln = "'" + request.getParameter("Last Name") + "',";
		String pw = "'" + request.getParameter("password") + "'";
		String us = "'" + request.getParameter("username") + "',";
		String regQuery = "INSERT INTO users VALUES(" + fn + ln + us + pw + ")";
		try {
			udb.connect();
			udb.createStatement(regQuery);

		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}
	}
}
