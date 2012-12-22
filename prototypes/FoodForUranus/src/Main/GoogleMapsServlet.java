package Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.ConnectionManager;

/**
 * Servlet implementation class GoogleMaps
 */
@WebServlet("/GoogleMapsServlet")
public class GoogleMapsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Connection currentCon = null;
	private ResultSet rs = null;
	private Statement stmt = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> Lng = new ArrayList<String>();
		ArrayList<String> Lat = new ArrayList<String>();
		ArrayList<String> Info = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		String searchQuery;
		HttpSession session = request.getSession();
		if(request.getParameter("submit").equals("Search")){
			String rID = request.getParameter("search");
			searchQuery = "SELECT * FROM tblRestaurants WHERE rName LIKE '%" + rID + "%';";
		}else
		{
			String rID = request.getParameter("pref1");
			searchQuery = "SELECT * FROM tblRestaurants WHERE rID = '" + rID + "';";
		}
		
		try {
			currentCon = ConnectionManager.getCon();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean markerExists = rs.next();
			rs.beforeFirst();
			if (!markerExists) {
				session.setAttribute("Lat", null);
				response.sendRedirect("Index.jsp");
				System.out.println("No restaurant found.");
			} else if (markerExists) {
				while (rs.next()) {
					//System.out.println("Servlet:" + rs.getString("rID"));
					Lat.add(rs.getString("rLat"));
					Lng.add(rs.getString("rLng"));
					Info.add(rs.getString("rInfo"));
					Name.add(rs.getString("rName"));
				}
				session.setAttribute("Lng", Lng);
				session.setAttribute("Lat", Lat);
				session.setAttribute("Info", Info);
				session.setAttribute("Name", Name);
				response.sendRedirect("Index.jsp");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
