package com.ipuppyp.minimalvideodj.web.configuration;

import java.nio.file.Paths;

import org.minimal.video.dj.facade.MinimalVideoDjServiceFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.ipuppyp.minimalvideodj.service.DefaultFileService;
import com.ipuppyp.minimalvideodj.service.VlcjVideoService;

@Configuration
@PropertySource("classpath:minimal-video-dj.properties")
public class SpringConfigurationService {

	@Value("${video_files_path}")
	private String videoFilesPath;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public MinimalVideoDjServiceFacade facade() {
		return new MinimalVideoDjServiceFacade(new DefaultFileService(Paths.get(videoFilesPath)), new VlcjVideoService());
	}

	
}
