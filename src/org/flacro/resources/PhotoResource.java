package org.flacro.resources;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.flacro.po.Users;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.ext.servlet.ServletUtils;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PhotoResource extends BaseResource {

	@Get
	public Representation get() {
		try {
			ResourceBundle rb = ResourceBundle
					.getBundle("config");
			String path = rb.getString("albumpath");
			Map map = getRequest().getAttributes();
			String name = (String) map.get("photoname");
			File file = new File(path + name);
			Representation r = new FileRepresentation(file,
					MediaType.IMAGE_JPEG);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
