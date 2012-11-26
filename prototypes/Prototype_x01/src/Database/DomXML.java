package Database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Servlet implementation class DomXML
 */
@WebServlet("/DomXML")
public class DomXML extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection currentCon = null;
	private ResultSet rs = null;
	private Statement stmt = null;
	private ResultSetMetaData rsMetaData = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sqlQuery = "SELECT * FROM tblRestaurants;";
		try {
			currentCon = ConnectionManager.getCon();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			rsMetaData = rs.getMetaData();
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("markers");
			doc.appendChild(rootElement);

			// staff elements
			while (rs.next()) {
				Element staff = doc.createElement("marker");
				rootElement.appendChild(staff);
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					staff.setAttribute(rsMetaData.getColumnName(i),
							rs.getString(i));
				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(
					new File(
							"D:\\Dropbox\\Eclipse\\JSP_Tutorial\\Prototype_x01\\WebContent\\restaurants.xml"));
			transformer.transform(source, result);

			System.out.println("XML Done");
			response.sendRedirect("Index.jsp");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
