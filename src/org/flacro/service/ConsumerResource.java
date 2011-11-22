package org.flacro.service;

import java.util.ListResourceBundle;

public class ConsumerResource extends ListResourceBundle {

	static final Object[][] contents = {
			{"albumpath","e:\\album\\"},
			{"temppath","e:\\album\\temp"}
			
	};

	@Override
	protected Object[][] getContents() {
		return contents;
	}

}
