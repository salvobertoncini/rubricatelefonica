package xmlparser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlWriter 
{
	public XmlWriter()
	{

	}
	
	public static void writeXML() throws Exception
	{
		int id=0;
		//DocumentBuilderFactory
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		
		//DocumentBuilder
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		//Document
		Document xmlDoc = docBuilder.newDocument();
		
		//Build XML Elements and Text Nodes
		Element root = xmlDoc.createElement("Rubrica");
		xmlDoc.appendChild(root);
		
		Element contatto = xmlDoc.createElement("Contatto");
		contatto.setAttribute("id", "");
		
		Element name = xmlDoc.createElement("Nome");
		name.appendChild(xmlDoc.createTextNode("Salvo"));
		contatto.appendChild(name);
		Element surname = xmlDoc.createElement("Cognome");
		surname.appendChild(xmlDoc.createTextNode("Bertoncini"));
		contatto.appendChild(surname);
		Element telnumber = xmlDoc.createElement("Telefono");
		telnumber.appendChild(xmlDoc.createTextNode("3477129666"));
		contatto.appendChild(telnumber);
		
		root.appendChild(contatto);
		
		TransformerFactory transformerfactory = TransformerFactory.newInstance();
		Transformer transformer = transformerfactory.newTransformer();
		DOMSource source = new DOMSource(xmlDoc);
		
		StreamResult streamResult = new StreamResult(new File("EsportazioniRubriche/rubricaXML"+id+".xml"));
		transformer.transform(source, streamResult);
		
	}
	
}
