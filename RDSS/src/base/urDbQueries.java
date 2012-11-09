package base;
import java.sql.*;
import java.util.ArrayList;

public class urDbQueries {
public ArrayList <urSelectable>trueArl = new ArrayList <urSelectable>();
public urDatabase db;

public urDbQueries (urDatabase dbs) {
	db = dbs;
}
	
void check () {	
	for (urSelectable s : urSelectable.arlSelectables) {
		if (s.isSelected()) {
			trueArl.add(s);
		}
		
	}
}
}
