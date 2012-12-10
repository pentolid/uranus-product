package Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Connects to the database
 */
public abstract class ConnectionManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static protected Connection con;

	public static Connection getCon() {
		return con;
	}
	/**
	 * init() function only called when servlet is initialized/loaded
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver"); // MySQL Driver
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/dbRdssLindholmen", "root",
					"K0k0hand");
		} catch (Exception e) {
			throw new UnavailableException(e.getMessage());
		}
		//System.out.println("database connencted!");
		jspInit();
	}

	/**
	 * Closes the database connection when the servlet is unloaded.
	 */
	public void destroy() {
		try {
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (Exception ignore) {
		}

		jspDestroy();
		super.destroy();
	}

	/**
	 * Called when the JSP is loaded. By default does nothing.
	 */
	public void jspInit() {
	}

	/**
	 * Called when the JSP is unloaded. By default does nothing.
	 */
	public void jspDestroy() {
	}

	/**
	 * Invokes the JSP's _jspService method.
	 */
	public final void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		_jspService(request, response);
	}

	/**
	 * Handles a service request.
	 */
	public abstract void _jspService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
}
