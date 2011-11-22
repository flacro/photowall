package org.flacro.resources;

import java.util.Map;

import org.flacro.po.Tags;
import org.flacro.service.UserService;
import org.restlet.data.MediaType;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.inject.Inject;

public class TagResource extends BaseResource {

	@Inject
	private UserService userservice;
	
	@Post
	public Representation create(Representation entity) {
		DomRepresentation r = null;
		try {
			Map map = getRequest().getAttributes();
			int tagid = Integer.parseInt((String) map.get("tagid"));
			String tag = (String) map.get("tag");
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
			e.printStackTrace();
			return null;
		}
	}
	
	@Delete
	public Representation delete(Representation entity) {
		DomRepresentation r = null;
		try {
			Map map = getRequest().getAttributes();
			int tagid = Integer.parseInt((String) map.get("tagid"));
			int result = userservice.deleteTag(tagid);

			// 生成XML表示
			r = new DomRepresentation(MediaType.TEXT_XML);
			Document doc = r.getDocument();
			Element root = doc.createElement("tag");
			root.setAttribute("delete", "" + 1);
			doc.appendChild(root);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
