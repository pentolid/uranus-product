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
 * Verifies user login
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
		/**
		 * create query to look up user in db
		 */
		String searchQuery = "SELECT * FROM tblUsers WHERE BINARY uID='" + username
				+ "' AND BINARY uPassword='" + password + "'";
		try {
			currentCon = ConnectionManager.getCon();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean userExists = rs.next();
			if (!userExists) {
				bean.setValid(false);
			} else if (userExists) {
				String permission = rs.getString("uPermission");
				//System.out.println(username);
				bean.setPermission(permission);
				bean.setValid(true);
			}
		} catch (Exception e) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ e);
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
