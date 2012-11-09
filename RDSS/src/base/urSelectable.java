package base;
import java.util.ArrayList;

/*
 * 
 * For all checkable types of food/drink whatevs. The list of thise should be generated dynamically by asking DB for all "checkbox" options. 
 * /KG'
 */

public class urSelectable{
	private boolean bSelected;
	private  final String strDbName;
	public static ArrayList<urSelectable>arlSelectables= new ArrayList<urSelectable>();
	
	public urSelectable(String DBN){
		bSelected=false;
		strDbName=DBN;
		arlSelectables.add(this);
	}
	public boolean isSelected(){return bSelected;}
	public void doSelect(){bSelected=true;}
	public void unSelect(){bSelected=false;}
	public String nameInDataBase(){return strDbName;}
};

