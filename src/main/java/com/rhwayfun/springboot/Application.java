package com.rhwayfun.springboot;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;

@SpringBootApplication
public class Application implements EmbeddedServletContainerCustomizer {

	/** Logger */
	private static Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		try {
			System.setProperty("server.port", String.valueOf(9999));
			SpringApplication.run(Application.class, args);
			log.info("Application start success.");
		} catch (Exception e) {
			log.error("Application start failed.", e);
		}
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
