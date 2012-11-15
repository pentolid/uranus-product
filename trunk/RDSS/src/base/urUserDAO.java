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
	 * Description: Invoked by the LogServlet with the information submited by the user. 
	 * 
	 * --
	 * 
	 * The submited data is sent as a query to the database to confirm that the user is created. 
	 * 
	 * --
	 * 
	 * Created by: Joakim Bajoul Kakaei Creation date: 2012-11-14
	 * ######################################################################
	 */
	public static urUserBeans login(urUserBeans bean) {
		// preparing some objects for connection
		String username = bean.getUsername();
		String password = bean.getPassword();
		String searchQuery = "select * from users where username='" + username
				+ "' AND password='" + password + "'"; // "System.out.println"
														// prints in the
														// console; Normally
														// used to trace the
														// process
		System.out.println("Your user name is " + username);
		System.out.println("Your password is " + password);
		System.out.println("Query: " + searchQuery);

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
				String firstName = udb.getRs().getString("FirstName");
				String lastName = udb.getRs().getString("LastName");
				System.out.println("Welcome " + firstName);
				bean.setFirstName(firstName);
				bean.setLastName(lastName);
				bean.setValid(true);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		} // some exception handling finally
		{
			if (udb.getRs() != null) {
				try {
					udb.getRs().close();
				} catch (Exception e) {
				}

			}
//			if (urDatabase.conn != null) {
//				try {
//					urDatabase.conn.close();
//				} catch (Exception e) {
//				}
//				urDatabase.conn = null;
//			}
		}
		return bean;
	}
}
