import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * ##########################################################################
 * +++urEditServlet+++
 * Description:This class acts like a middlehand between the jsp-file and the database to create or edit restaurants.
 * 		- Create or edit restaurants in the databse.
 * 
 * The class can create any kind of restaurant, as long as valid data is inserted into the textfields.
 * 
 * Created by: 		Joakim Bajoul Kakaei
 * Creation date: 	2012-11-26
 * ##########################################################################
 */
@WebServlet("/editServlet")
public class editServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String streetName = request.getParameter("StreetName");
		String streetNo = request.getParameter("Streetnumber");
		String tele = request.getParameter("TelephoneNo");
		String mail = request.getParameter("Email");
		String web = request.getParameter("Homepage");
		String info = request.getParameter("Description");
		String op = request.getParameter("Opening");
		String close = request.getParameter("Closing");
		HttpSession session = request.getSession();
		if (!request.getParameter("name").equals("")
				|| !request.getParameter("StreetName").equals("")
				|| !request.getParameter("Streetnumber").equals("")
				|| !request.getParameter("TelephoneNo").equals("")
				|| !request.getParameter("Email").equals("")
				|| !request.getParameter("Homepage").equals("")
				|| !request.getParameter("Description").equals("")
				|| !request.getParameter("Opening").equals("")
				|| !request.getParameter("Closing").equals("")) {
			String editQuery = "INSERT INTO users VALUES(" + "'" + name + "',"
					+ "'" + streetName + "'," + "'" + streetNo + "'," + "'"
					+ tele + "'," + "'" + mail + "'," + "'" + web + "'," + "'"
					+ info + "'," + "'" + op + "'," + "'" + close + "'," + ")";
			session.removeAttribute("wrong");
			response.sendRedirect("edit.jsp");
			System.out.println("HEJ");
		} else {
			session.setAttribute("wrong", 1);
			response.sendRedirect("edit.jsp");

		}
	}
}
