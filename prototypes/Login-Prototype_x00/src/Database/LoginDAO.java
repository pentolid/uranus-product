package Database;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Login.UserBean;

/**
 * Servlet implementation class LoginDAO
 */
@WebServlet("/LoginDAO")
public class LoginDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Connection currentCon = null;
	static ResultSet rs = null;

	public static UserBean login(UserBean bean) {
		Statement stmt = null;
		String username = bean.getUserName();
		String password = bean.getPassword();
		String searchQuery = "SELECT * FROM tblUsers WHERE uID='" + username
				+ "' AND uPassword='" + password + "'";
		try {
			// connecting to the DB
			currentCon = ConnectionManager.getCon();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean userExists = rs.next();
			if (!userExists) {
				System.out
						.println("Username/Password entered is Incorrect or User doesnot Exists.");
				bean.setValid(false);
			} else if (userExists) {
				String permission = rs.getString("uPermission");
				System.out.println("Welcome " + username);
				bean.setPermission(permission);
				bean.setValid(true);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
