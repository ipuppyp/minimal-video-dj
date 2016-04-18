package com.ipuppyp.minimalvideodj.application.configuration;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

import org.minimal.video.dj.facade.MinimalVideoDjFacade;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

@Configuration
public class SpringConfigurationSwing {
 
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	@Bean
	public VideoPanel videoPanel(MinimalVideoDjFacade facade) throws UnsupportedLookAndFeelException, IOException {
		
		return new VideoPanel(facade, messageSource());
	}
	
}
