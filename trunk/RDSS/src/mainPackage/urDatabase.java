package mainPackage;
import java.sql.*;

	/*
	 * ##########################################################################
	 * +++urDatabase+++
	 * Description:This class provides all communication to a database.
	 * 		- database connection
	 * 		- select statements
	 * 		- update statements
	 * 		- result set and meta data
	 * The class can connect to every kind of database, if the database driver 
	 * is available. It works with only one Result Set and one Meta Data, more 
	 * can be added if needed
	 * 
	 * Created by: 		Nico Boh
	 * Creation date: 	2012-11-06
	 * ##########################################################################
	 */

public class urDatabase {
	private Connection conn = null; // Connection to the database
	private Statement stmt = null; // Statement from database (executes SQL Queries)
	private ResultSet rs = null; // Result set of executed statement (handles data from database)
	private ResultSetMetaData rsMetaData = null; // Meta data of result set (gives information about Result Set i.e: CoulumnCount, ColoumnName etc.)

	public urDatabase(){}
	/*
	 * ######################################################################
	 * 
	 * Description: This method connects is selecting a database driver and
	 * connects to a database. Errors will be reported in the console.
	 * 
	 * Created by: Nico Boh Creation date: 2012-11-06
	 * ######################################################################
	 */
	public void connect() {

		// find database driver
		try {
			// Class.forName("com.mysql.jdbc.Driver"); // MySQL Driver
			Class.forName("org.sqlite.JDBC"); // SQLite Driver
			System.out.println("Database driver found...");
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't find driver class: " + e.getMessage());
		}
		// connect to database
		try {
			// MySQL Connection
			//conn = DriverManager.getConnection("jdbc:mysql://localhost/database name", "Username","Password");
			// SQLite Connection
			conn = DriverManager.getConnection("jdbc:sqlite:D:\\dbTest.db");
		} catch (SQLException se) {
			System.out.println("Couldn't connect to database: ");
			System.out.println(se.getMessage());
			// System.exit(1);
		}
		// check if database is connected (conn cannot be null)
		if (conn != null)
			System.out.println("Database connected...");
		else
			System.out.println("We should never get here.");
	}

	/*
	 * #######################################################################
	 * Description: This method creates a statement from the database connection
	 * and executes a query, given by an argument. This will be parsed to a
	 * resultSet object which parses it's meta data to a metaData object. Errors
	 * will be reported in the console.
	 * 
	 * Created by: Nico Boh Creation date: 2012-11-06
	 * #######################################################################
	 */
	public void createStatement(String SQLQuery) {
		try {
			// initialize statement object
			stmt = conn.createStatement();
		} catch (SQLException se) {
			System.out.println("Error: ");
			System.out.println(se.getMessage());
			System.exit(1);
		}
		try {
			// execute query and initialize result set object
			setRs(stmt.executeQuery(SQLQuery));
			// initialize meta data object
			setRsMetaData(getRs().getMetaData());
			System.out.println("SQL statement successfully executed...");
			System.out.println("Meta data successfully created...");
		} catch (SQLException e) {
			System.out.println("Exception while executing query: "
					+ e.getMessage());
			//System.exit(1);
		}
	}

	/*
	 * #######################################################################
	 * Description: This method creates a statement from the database connection
	 * and executes an update query, given by an argument. The method returns
	 * the number of affected rows.
	 * 
	 * Created by: Nico Boh Creation date: 2012-11-06
	 * #######################################################################
	 */
	public int updateTable(String SQLQuery) {
		int intNumber = 0;
		try {
			// initialize statement object
			stmt = conn.createStatement();
			// execute update query
			intNumber = stmt.executeUpdate(SQLQuery);
			return intNumber;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	/*
	 * #######################################################################
	 * getters and setters
	 * #######################################################################
	 */
	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public ResultSetMetaData getRsMetaData() {
		return rsMetaData;
	}

	public void setRsMetaData(ResultSetMetaData rsMetaData) {
		this.rsMetaData = rsMetaData;
	}
}
