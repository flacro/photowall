package util;

import java.util.List;

import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.restlet.data.MediaType;
import org.restlet.ext.xml.DomRepresentation;
import org.w3c.dom.Document;

public class BuildPhoto {
	public static DomRepresentation buildxml(String source) {
		DomRepresentation dr = null;
		try {
			if (1 > 0) {
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.newDocument();
				Element root = doc.createElement("varkrs");
				root.setAttribute("name", "flacro");
				root.setAttribute("logo", source);
				doc.appendChild(root);
				// String name = objectlist.get(0).getClass().
				// Element list = doc.createElement()
				dr = new DomRepresentation(MediaType.TEXT_XML, doc);
			}
		} catch (Exception e) {

		}
		return dr;
	}
}
