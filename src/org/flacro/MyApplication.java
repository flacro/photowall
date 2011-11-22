package org.flacro;

import org.flacro.resources.PhotoResource;
import org.flacro.resources.PhotosResource;
import org.flacro.resources.TagResource;
import org.flacro.resources.TagsResource;
import org.flacro.resources.UsersAllResource;
import org.flacro.resources.UsersResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.freemarker.ContextTemplateLoader;
import org.restlet.routing.Router;

import com.google.inject.Inject;
import com.google.inject.Injector;

import freemarker.template.Configuration;

public class MyApplication extends Application {
	private Injector injector;
	/** The Freemarker's configuration. */
    private Configuration configuration;
    
    MyApplication(Injector injector){
    	super();
    	this.injector = injector;
    }
    
    @Override
    public Restlet createInboundRoot() {
        // initialize the Freemarker's configuration
    	// Freemarker暂时无法使用，搁置
        configuration = new Configuration();        
        configuration.setTemplateLoader(new ContextTemplateLoader(getContext(),
                "/templates/"));

        Router router = new GuiceRouter(injector, getContext()) {
			@Override
			protected void attachRoutes() {
				attach("/users", UsersAllResource.class);
				attach("/users/{userid}", UsersResource.class);
				attach("/users/{userid}/tags", TagsResource.class);
				attach("/users/{userid}/tags/{tagid}", TagResource.class);
				attach("/photos/{photoname}", PhotoResource.class);
				attach("/photos", PhotosResource.class);
				
			}
		};        
        return router;
    }

	public Configuration getConfiguration() {
		return configuration;
	}
    
    
}
