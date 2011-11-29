package org.flacro;

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
		if(userservice.login(username, password)){
			req.getSession().setAttribute("password", username);
			resp.sendRedirect(req.getContextPath()+"/index.html");
		}else{
			//resp.sendRedirect("/index.html");
			resp.sendError(401);
		}
	}
	
	
	
	
}
