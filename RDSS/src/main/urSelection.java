package main;


/*
 * Keeps track of the options the user has choosen by checking boxes. 
 */
public class urSelection{

		private urSelection(){}
		private static urSelection _instance=new urSelection();
		public static urSelection getSelection(){return _instance;}
}

