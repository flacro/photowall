package org.flacro.module;

import org.flacro.LoginServlet;
import org.flacro.RestletServlet;

public class ServletModule extends com.google.inject.servlet.ServletModule {
		
	@Override
	protected void configureServlets() {
		serve("/login").with(LoginServlet.class);
		serve("/photowall/*").with(RestletServlet.class);
	}
	

}
