package base;

class urUser {
	urSelectable lastSelection;

}

public class urMain {

	private urDatabase dataBase;// =new urDatabase();
	private urSelection selection;// =new urSelection();

	public urMain() {
		dataBase = new urDatabase();
		dataBase.connect();
		urSelection.getSelection(dataBase);

		// urDatabase.connect()

	}

	public void reform() {

	}

	public String htmlBody() {
		String html;
		return null;
	}

	public String htmlTitle() {
		return null;
	}
}
