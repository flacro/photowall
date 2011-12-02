package org.flacro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.flacro.resources.PhotoResource;
import org.flacro.resources.PhotosResource;
import org.flacro.resources.TagResource;
import org.flacro.resources.TagsResource;
import org.flacro.resources.UsersAllResource;
import org.flacro.resources.UsersResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.ext.servlet.ServletAdapter;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;


@Singleton
public class RestletServlet extends HttpServlet {
	@Inject
	private Injector injector;
	private Context context;
	private ServletAdapter adapter;
	
	@Override
	public void init() throws ServletException {
		context = new Context();
		//Application application = new MyApplication(injector);
		Application application = new Application();
		application.setContext(context);
		application.setInboundRoot(new GuiceRouter(injector,context) {
			@Override
			protected void attachRoutes() {
				attach("/users", UsersAllResource.class);
				attach("/users/{userid}", UsersResource.class);
				attach("/users/{userid}/tags", TagsResource.class);
				attach("/users/{userid}/tags/{tagid}", TagResource.class);
				attach("/photos/{photoname}", PhotoResource.class);
				attach("/photos", PhotosResource.class);
			}
		});
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
