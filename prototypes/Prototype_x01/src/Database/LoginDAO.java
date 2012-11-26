package Database;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import Login.UserBean;

/**
 * Servlet implementation class LoginDAO
 */
@WebServlet("/LoginDAO")
public class LoginDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static UserBean login(UserBean bean) throws SQLException {
		Statement stmt = null;
		Date date = new Date();
		String username = bean.getUserName();
		String password = bean.getPassword();
		String searchQuery = "SELECT * FROM tblUsers WHERE BINARY uID='" + username
				+ "' AND BINARY uPassword='" + password + "'";
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
				stmt.executeUpdate("INSERT INTO tblLog VALUES('" + username + "','" + dateFormat.format(date) + "');");
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
