package org.flacro.resources;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.servlet.ServletUtils;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import util.LogDetail;

public class PhotosResource extends ServerResource {
	@Post
	public Representation create(Representation entity) {
		DomRepresentation r = null;
		try {
			// upload picture
			ResourceBundle rb = ResourceBundle
					.getBundle("config");
			String path = rb.getString("albumpath");
			String fileName = "";
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Configure the factory here, if desired.
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Configure the uploader here, if desired.
			List fileItems = upload.parseRequest(ServletUtils
					.getRequest(getRequest()));
			Iterator iter = fileItems.iterator();
			for (; iter.hasNext();) {
				FileItem fileItem = (FileItem) iter.next();
				if (fileItem.isFormField()) { // 当前是一个表单项
					System.out.println("form field : "
							+ fileItem.getFieldName() + ", "
							+ fileItem.getString());
				} else {
					// 当前是一个上传的文件
					fileName = fileItem.getName();
					String extension = fileName.substring(fileName
							.lastIndexOf("."));
					Random random = new Random(10);
					int n = random.nextInt(10000);
					fileName = new Date().getTime()+ "-" + n + extension;
					fileItem.write(new File(path + fileName));
				}
				// 只处理第一张图片
				break;
			}

			// 生成XML表示
			r = new DomRepresentation(MediaType.TEXT_XML);
			Document doc = r.getDocument();
			Element root = doc.createElement("image");
			root.setAttribute("name", fileName);
			doc.appendChild(root);
			return r;
		} catch (Exception e) {
			LogDetail.logexception(e);
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return null;
		}
	}
	
}
