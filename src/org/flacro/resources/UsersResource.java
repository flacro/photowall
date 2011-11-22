package org.flacro.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.flacro.po.Users;
import org.flacro.service.UserService;
import org.restlet.data.Encoding;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.ext.fileupload.RepresentationContext;
import org.restlet.ext.servlet.ServletUtils;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.InputRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.inject.Inject;

import util.BuildPhoto;

public class UsersResource extends BaseResource {

	@Inject
	private UserService userservice;

	@Get
	public Representation get() {
		try {
			int id = Integer.parseInt(getRequest().getAttributes()
					.get("userid").toString());
			Users u = userservice.getUsers(id);

			// 生成XML表示
			DomRepresentation r = new DomRepresentation(MediaType.TEXT_XML);
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
			e.printStackTrace();
			return null;
		}
	}

	@Post
	public Representation update(Representation entity) {
		DomRepresentation r = null;
		try {
			int userid = Integer.parseInt((String) getRequest().getAttributes()
					.get("userid"));
			Users u = userservice.getUsers(userid);
			String fname = u.getLogo();
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
					if (fname == null || fname.equals("")) {
						Random random = new Random(10);
						int n = random.nextInt(10000);
						fileName = new Date().getTime() + "-" + n + extension;
					} else
						fileName = fname;
					fileItem.write(new File(path + fileName));
				}
				// 只处理第一张图片
				break;
			}

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
			e.printStackTrace();
			return null;
		}
	}

}
