package com.rhwayfun;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;

@SpringBootApplication
public class BootDemoApplication implements EmbeddedServletContainerCustomizer {

	public static void main(String[] args) {
		SpringApplication.run(BootDemoApplication.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		if (configurableEmbeddedServletContainer instanceof JettyEmbeddedServletContainerFactory) {
			final JettyEmbeddedServletContainerFactory jetty = (JettyEmbeddedServletContainerFactory) configurableEmbeddedServletContainer;

			final JettyServerCustomizer customizer = server -> {
				Handler handler = server.getHandler();
				WebAppContext webAppContext = (WebAppContext) handler;
				webAppContext.setBaseResource(Resource.newClassPathResource("webapp"));
			};
			jetty.addServerCustomizers(customizer);
		}
	}
}
