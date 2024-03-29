package org.flacro;

import org.restlet.Context;
import org.restlet.resource.Finder;
import org.restlet.routing.Router;

import com.google.inject.Inject;
import com.google.inject.Injector;

public abstract class GuiceRouter extends Router {
	
	private final Injector injector;

	public GuiceRouter(Injector injector, Context context) {
		super(context);
		this.injector = injector;
		attachRoutes();
	}

	@Override
	public Finder createFinder(Class targetClass) {
		return new GuiceFinder(injector, getContext(), targetClass);
	}

	protected abstract void attachRoutes();

	protected Injector getInjector() {
		return injector;
	}
}
