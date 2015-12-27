package com.ipuppyp.minimalvideodj.web.configuration;

import org.apache.catalina.Context;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfigurationTomcatCustomization {

	@Bean
	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.setPort(80);
			}
		};
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {

	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();

	    TomcatContextCustomizer contextCustomizer = new TomcatContextCustomizer() {
	        @Override
	        public void customize(Context context) {
	            context.addWelcomeFile("/index.html");
	        }
	    };
	    factory.addContextCustomizers(contextCustomizer);

	    return factory;
	}	
	
}
