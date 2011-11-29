package org.flacro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		Object login = session.getAttribute("password");
		if (login == null) {
			if(request.getRequestURI().indexOf("login") >0 ){
				System.out.println("login");
				chain.doFilter(req, resp);
			}else{
				System.out.println("Redirect Login");
				response.sendRedirect("/index.html");
			}
		} else {
			System.out.println("Normal");
			chain.doFilter(req, resp);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
