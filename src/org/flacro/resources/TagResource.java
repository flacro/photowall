package org.flacro.resources;

import java.util.Map;

import org.apache.log4j.Logger;
import org.flacro.po.Tags;
import org.flacro.service.UserService;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import util.LogDetail;

import com.google.inject.Inject;

public class TagResource extends ServerResource {
	@Inject
	private UserService userservice;
	
	@Post
	public Representation create(Representation entity) {
		DomRepresentation r = null;
		try {
			Map<String, Object> map = getRequest().getAttributes();
			Form form = new Form(entity);
			int tagid = Integer.parseInt(form.getFirstValue("tagid"));
			String tag = form.getFirstValue("tag");
			Tags t = userservice.getTag(tagid);
			t.setTag(tag);
			userservice.updateTag(t);

			// 生成XML表示
			r = new DomRepresentation(MediaType.TEXT_XML);
			Document doc = r.getDocument();
			Element root = doc.createElement("tag");
			root.setAttribute("id", "" + t.getId());
			root.setAttribute("tag", t.getTag());
			root.setAttribute("userid", "" + t.getUserid());
			doc.appendChild(root);
			return r;
		} catch (Exception e) {
			LogDetail.logexception(e);
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return null;
		}
	}
	
	@Delete
	public Representation delete(Representation entity) {
		DomRepresentation r = null;
		try {
			Map<String,Object> map = getRequest().getAttributes();
			int tagid = Integer.parseInt((String) map.get("tagid"));
			userservice.deleteTag(tagid);

			// 生成XML表示
			r = new DomRepresentation(MediaType.TEXT_XML);
			Document doc = r.getDocument();
			Element root = doc.createElement("tag");
			root.setAttribute("delete", "" + 1);
			doc.appendChild(root);
			return r;
		} catch (Exception e) {
			LogDetail.logexception(e);
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return null;
		}
	}
	
}
