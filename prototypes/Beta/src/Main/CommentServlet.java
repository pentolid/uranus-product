package Main;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.ConnectionManager;

/**
 * saves a new comment in the database
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String comment = request.getParameter("comments");
		String name = request.getParameter("name");
		String rating = request.getParameter("rating");
		String restaurantID = request.getParameter("rID");
		Statement st;
		try {
			st = ConnectionManager.getCon().createStatement();
			st.executeUpdate("INSERT INTO tblratings (rtPoints, rtComment, rtDate, uID, rID) VALUES("
			+ rating 
			+ ",'" + comment
			+ "','" + dateFormat.format(date)
			+ "','"+ name
			+ "'," + restaurantID
			+ ");");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		/**
		 * redirect to comment.jsp page with rID as parameter to keep showing data of the selected restaurant
		 */
		response.sendRedirect("Comments.jsp?rID=" + restaurantID);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
