package com.ipuppyp.minimalvideodj.web.configuration;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class SpringConfigurationTomcatCustomization implements EmbeddedServletContainerCustomizer {

	@Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(80);
    }
}
