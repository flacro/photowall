package org.flacro.resources;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.ibatis.logging.LogException;
import org.apache.log4j.Logger;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import util.LogDetail;

public class PhotoResource extends ServerResource {

	@Get
	public Representation get() {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("config");
			String path = rb.getString("albumpath");
			Map<String, Object> map = getRequest().getAttributes();
			String name = (String) map.get("photoname");
			File file = new File(path + name);
			if (!file.exists())
				throw new IOException();
			Representation r = new FileRepresentation(file,
					MediaType.IMAGE_JPEG);
			return r;
		} catch (Exception e) {
			LogDetail.logexception(e);
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return null;
		}
	}

}
