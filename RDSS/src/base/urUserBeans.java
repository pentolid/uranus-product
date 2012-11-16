package base;
/*
 * ##########################################################################
 * +++urUserBeans+++
 * Description:This class will create a digital user while supplying the data to other classes.
 * 		
 * -Create user
 * 		
 * The class can create as many users as needed. The class will then invoke another class to 
 * store all the users as data in a database.
 * 
 * Created by: 		Joakim Bajoul Kakaei
 * Creation date: 	2012-11-14
 * ##########################################################################
 */
public class urUserBeans {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	public boolean valid;
	public urSelection selection;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String newLastName) {
		lastName = newLastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String newPassword) {
		password = newPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String newUsername) {
		username = newUsername;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean newValid) {

		valid = newValid;
	}

}
