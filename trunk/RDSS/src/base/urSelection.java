package base;

/*
 * Keeps track of the options the user has choosen by checking boxes. 
 */
public class urSelection{
		private urSelection(urDatabase DB){
			/*
			 * Create all selectable types of foods/drinks.
			 * TODO: Create them from data from the DB instead of manually here. 
			 * /KG
			 */
			urSelectable Fish = new urSelectable("fish");
			urSelectable Ethanol = new urSelectable("ethanol");
			urSelectable Cocoa = new urSelectable("cocoa");
			
		}
		private static urSelection _instance;
		public static urSelection getSelection(urDatabase DB){
			if(_instance==null)_instance = new urSelection(DB);
			return _instance;
		}
		
		//FORM Sql query from selected data
}	

