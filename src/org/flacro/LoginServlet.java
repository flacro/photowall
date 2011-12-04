package org.flacro;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.flacro.service.UserService;

import com.google.inject.Inject;
import com.google.inject.Singleton;
@Singleton
public class LoginServlet extends HttpServlet {
	@Inject
	private UserService userservice;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = (String)req.getParameter("username");
		String password = (String)req.getParameter("password");
		BufferedWriter writer = new BufferedWriter(resp.getWriter());
		if(userservice.login(username, password)){
			req.getSession().setAttribute("password", username);
			writer.write("1");
		}else{
			writer.write("0");
		}
		writer.flush();
		writer.close();
	}
	
	
	
	
}
