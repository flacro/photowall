package org.flacro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.servlet.internal.ServletCall;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.routing.Filter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RestletSessionFilter extends Filter {

	@Override
	protected int beforeHandle(Request request, Response response) {
		HttpServletRequest hsr = ServletCall.getRequest(request);
		HttpSession session = hsr.getSession();
		Object login = session.getAttribute("password");
		if (login == null) {
			String requri = hsr.getRequestURI();
			String res = requri.substring(requri.length() - 5, requri.length());
			String res2 = requri.substring(requri.lastIndexOf("/")-6, requri.lastIndexOf("/"));
			if (!(res.equals("users") || res2.equals("photos"))) {
				try {
					DomRepresentation r = new DomRepresentation(
							MediaType.TEXT_XML);
					Document doc = r.getDocument();
					Element root = doc.createElement("error");
					root.setAttribute("name", "need authorization");
					root.setAttribute("response", "403");
					doc.appendChild(root);
					response.setEntity(r);
					response.setStatus(Status.CLIENT_ERROR_FORBIDDEN);
				} catch (Exception e) {
					response.setStatus(Status.CLIENT_ERROR_FORBIDDEN);
				}
				return STOP;
			}
		}
		return CONTINUE;
	}

}
