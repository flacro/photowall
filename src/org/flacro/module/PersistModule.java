package org.flacro.module;

import org.flacro.service.UserService;
import org.flacro.service.UserServiceImpl;


import com.google.inject.AbstractModule;

public class PersistModule extends AbstractModule {

	@Override
	protected void configure() {
				
		bind(UserService.class).to(UserServiceImpl.class);
				
	}

}
