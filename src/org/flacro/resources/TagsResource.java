package org.flacro.resources;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.flacro.po.Tags;
import org.flacro.service.UserService;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import util.LogDetail;

import com.google.inject.Inject;

public class TagsResource extends ServerResource {
	@Inject
	private UserService userservice;

	@Get
	public Representation get() {
		try {
			int userid = Integer.parseInt(getRequest().getAttributes()
					.get("userid").toString());
			List<Tags> tlist = userservice.getTags(userid);

			// 生成XML表示
			DomRepresentation r = new DomRepresentation(MediaType.TEXT_XML);
			Document doc = r.getDocument();
			Element root = doc.createElement("tags");
			root.setAttribute("userid", "" + userid);
			for (Tags t : tlist) {
				Element e = doc.createElement("tag");
				e.setAttribute("id", "" + t.getId());
				e.setAttribute("tag", t.getTag());
				root.appendChild(e);
			}
			doc.appendChild(root);
			return r;
		} catch (Exception e) {
			LogDetail.logexception(e);
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return null;
		}
	}

	@Post
	public Representation create(Representation entity) {
		DomRepresentation r = null;
		try {
			Map<String, Object> map = getRequest().getAttributes();
			int userid = Integer.parseInt((String) map.get("userid"));
			Form form = new Form(entity);
			String tag = form.getFirstValue("tag");
			Tags t = new Tags();
			t.setTag(tag);
			t.setUserid(userid);
			userservice.createTag(t);

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
}
