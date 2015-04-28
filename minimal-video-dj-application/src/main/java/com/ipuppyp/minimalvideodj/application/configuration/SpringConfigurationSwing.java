package com.ipuppyp.minimalvideodj.application.configuration;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.ipuppyp.minimalvideodj.service.FileService;
import com.ipuppyp.minimalvideodj.service.VideoService;
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
	public VideoPanel videoPanel(VideoService videoService, FileService fileService) throws UnsupportedLookAndFeelException, IOException {
		
		return new VideoPanel(videoService, fileService, messageSource());
	}
	
}
