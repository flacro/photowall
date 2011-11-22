package org.flacro.module;

import org.mybatis.guice.XMLMyBatisModule;

public class MyBatisModule extends XMLMyBatisModule {

	@Override
	protected void initialize() {
		setEnvironmentId("development");
		setClassPathResource("mybatis.xml");
	}

}
