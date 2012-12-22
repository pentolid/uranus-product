package Login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.LoginDAO;

/**
 * creates new userBean for each session, if user valid
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
        {
            //System.out.println("In the Login Servlet");
			/**
			 * create a new userBean
			 */
            UserBean user = new UserBean();
            user.setUserName(request.getParameter("uname"));
            user.setPassword(request.getParameter("password"));
            /**
    		 * lookup database if username and pwd are valid
    		 */
            user = LoginDAO.login(user);
            /**
    		 * if user valid create new session
    		 */
            if(user.isValid())
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser",user);
                session.setAttribute("userName",user.getUserName());
                session.setAttribute("permission",user.getPermission());
                response.sendRedirect("Index.jsp");
                
            }/**
    		 * else redirect to index.jsp
    		 */
            else
                response.sendRedirect("Index.jsp");
        } catch (Throwable e)
        {
            System.out.println(e);
        } 
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
