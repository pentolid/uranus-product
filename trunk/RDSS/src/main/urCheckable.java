package main;


/*
 * 
 * Enum for all checkable types of food/drink whatevs. 
 */
public enum urCheckable{
	Meat("meat"), Fish("fish"), Veg("veg"),Last("__LAST");
	private boolean bSelected;
	private  final String strDbName;
	private urCheckable(String nameInDataBase){
		bSelected=false;
		strDbName=nameInDataBase; 
		}
	public boolean isSelected(){return bSelected;}
	public void select(){bSelected=true;}
	public void unselect(){bSelected=false;}
	public String dataBaseName(){return strDbName;}
};

