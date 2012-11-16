package base;

/*
 * ##########################################################################
 * +++urUserDAO+++
 * Description:This class provides the communication from urLogServlet to the database.
 * 		- Communicates with the database.
 * 		- Manipulate user's data.
 * 		-
 * The class shares all limitations with urDatabase, since all communcation with the databasefile is 
 * sent through it. 
 * 
 * Created by: 		Joakim Bajoul Kakaei
 * Creation date: 	2012-11-14
 * ##########################################################################
 */
public class urUserDAO {
	static urDatabase udb = new urDatabase();

	/*
	 * ######################################################################
	 * 
	 * Description: Invoked by the LogServlet with the information submited by
	 * the user.
	 * 
	 * --
	 * 
	 * The submited data is sent as a query to the database to confirm that the
	 * user is created.
	 * 
	 * --
	 * 
	 * Created by: Joakim Bajoul Kakaei Creation date: 2012-11-14
	 * ######################################################################
	 */
	public static urUserBeans login(urUserBeans bean) {
		// preparing some objects for connection
		String usr = bean.getUsername();
		String pw = bean.getPassword();
		String searchQuery = "select * from users where username='" + usr
				+ "' AND password='" + pw + "'";
		System.out.println("Your user name is " + usr); // delete in final
														// version

		System.out.println("Your password is " + pw); // delete in final version
		System.out.println("Query: " + searchQuery); // delete in final version

		try {
			// connect to DB

			udb.connect();

			udb.createStatement(searchQuery);
			boolean more = udb.getRs().next(); // if user does not exist set the
												// isValid
			// variable to false
			if (!more) {
				System.out
						.println("Sorry, you are not a registered user! Please sign up first");
				bean.setValid(false);
			} // if user exists set the isValid variable to true
			else if (more) {
				String firstName = udb.getRs().getString("firstName");
				String lastName = udb.getRs().getString("lastName");
				
				bean.setFirstName(firstName);
				bean.setLastName(lastName);
				System.out.println("Welcome " + firstName + " " + lastName);
				bean.setValid(true);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}
		{
			if (udb.getRs() != null) {
				try {
					udb.getRs().close();
				} catch (Exception e) {
				}

			}
		}
		return bean;
	}
}
