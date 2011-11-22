package org.flacro;

import org.flacro.module.MyBatisModule;
import org.flacro.module.PersistModule;
import org.flacro.module.ServletModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class ServletConfigListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		// Further modules are omitted...
		return Guice.createInjector(new ServletModule(), new PersistModule(), new MyBatisModule());
	}

}
