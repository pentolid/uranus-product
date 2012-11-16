package base;

import java.io.File;
import java.sql.SQLException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class urDomXML {

	public void testXML() throws DOMException, SQLException {
		urDatabase myDatabase = new urDatabase();
		myDatabase.connect();
		myDatabase
				.createStatement("SELECT rName, rStreet, rStreetNumber, rInfo FROM tblRestaurants;");
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("markers");
			doc.appendChild(rootElement);

			// staff elements
				while(myDatabase.getRs().next()){
				Element staff = doc.createElement("marker");
				rootElement.appendChild(staff);
				for (int i = 1; i <= myDatabase.getRsMetaData().getColumnCount(); i++) {
					staff.setAttribute(myDatabase.getRsMetaData().getColumnName(i), myDatabase.getRs().getString(i));
				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(new File(
					"D:\\Dropbox\\Eclipse\\JavaJSP\\GoogleMapsAPI_Test\\WebContent\\restaurants.xml"));
			transformer.transform(source, result);

			System.out.println("Done");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}
