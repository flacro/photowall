package org.flacro.resources;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.flacro.po.Users;
import org.flacro.service.UserService;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.servlet.ServletUtils;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import util.LogDetail;

import com.google.inject.Inject;

public class UsersAllResource extends ServerResource {
	@Inject
	private UserService userservice;

	@Get
	public Representation get() {
		try {
			List<Users> ulist = userservice.getUsersAll();

			// 生成XML表示
			DomRepresentation r = new DomRepresentation(MediaType.TEXT_XML);
			Document doc = r.getDocument();
			Element base = doc.createElement("member");
			for (Users u : ulist) {
				Element root = doc.createElement("varkrs");
				root.setAttribute("id", "" + u.getId());
				root.setAttribute("name", u.getUsername());
				root.setAttribute("gender", "" + u.getGender());
				root.setAttribute("grade", "" + u.getGrade());
				root.setAttribute("logo", u.getLogo());
				base.appendChild(root);
			}
			doc.appendChild(base);
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
			// create users
			Map map = getRequest().getAttributes();
			Form form = new Form(entity);
			String username = form.getFirstValue("username");
			String gender = form.getFirstValue("gender");
			String grade = form.getFirstValue("grade");
			String logo = form.getFirstValue("filename");
			Users u = new Users();
			u.setUsername(username);
			Integer gen = Integer.parseInt(gender);
			u.setGender(gen);
			Integer gra = Integer.parseInt(grade);
			u.setGrade(gra);
			u.setLogo(logo);
			userservice.createUser(u);

			// 生成XML表示
			r = new DomRepresentation(MediaType.TEXT_XML);
			Document doc = r.getDocument();
			Element root = doc.createElement("varkrs");
			root.setAttribute("id", "" + u.getId());
			root.setAttribute("name", u.getUsername());
			root.setAttribute("gender", "" + u.getGender());
			root.setAttribute("grade", "" + u.getGrade());
			root.setAttribute("logo", u.getLogo());
			doc.appendChild(root);
			return r;
		} catch (Exception e) {
			LogDetail.logexception(e);
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return null;
		}
	}

}
