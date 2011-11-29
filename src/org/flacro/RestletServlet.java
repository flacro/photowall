package org.flacro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.ext.freemarker.ContextTemplateLoader;
import org.restlet.ext.servlet.ServletAdapter;
import org.restlet.routing.Filter;
import org.restlet.routing.Validator;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import freemarker.template.Configuration;

@Singleton
public class RestletServlet extends HttpServlet {
	@Inject
	private Injector injector;
	private Context context;
	private ServletAdapter adapter;
	
	private Configuration configuration;
	@Override
	public void init() throws ServletException {
		context = new Context();
		Application application = new MyApplication(injector);
		application.setContext(context);		        
		adapter = new ServletAdapter(getServletContext());
		//adapter.setTarget(application);
		//adapter.setNext(application);
		RestletSessionFilter rsf = new RestletSessionFilter();
		rsf.setContext(context);
		rsf.setNext(application);
		adapter.setNext(rsf);
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			adapter.service(request, response);
	}
}
