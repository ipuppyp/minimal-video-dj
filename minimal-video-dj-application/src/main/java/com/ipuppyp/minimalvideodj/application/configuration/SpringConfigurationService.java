package com.ipuppyp.minimalvideodj.application.configuration;

import java.nio.file.Paths;
import java.util.prefs.Preferences;

import org.minimal.video.dj.facade.MinimalVideoDjFacade;
import org.minimal.video.dj.facade.MinimalVideoDjRestFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.ipuppyp.minimalvideodj.service.BuiltInPerferencesService;
import com.ipuppyp.minimalvideodj.service.DefaultFileService;
import com.ipuppyp.minimalvideodj.service.FileService;
import com.ipuppyp.minimalvideodj.service.VideoService;
import com.ipuppyp.minimalvideodj.service.VlcjVideoService;

@Configuration
@PropertySource("classpath:minimal-video-dj.properties")
public class SpringConfigurationService {

	@Value("${video_files_path}")
	private String videoFilesPath;

	@Value("${get-video-file-list-url}")
	private String getVideoFileListUrl;
	
	@Value("${start-video-url}")
	private String startVideoUrl;
	
	@Value("${stop-video-url}")
	private String stopVideoUrl;

	@Value("${adjust-url}")
	private String adjustUrl;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public VideoService videoService() {
		return new VlcjVideoService(new BuiltInPerferencesService
				(Preferences.userNodeForPackage(DefaultFileService.class)));
	}
	
	@Bean
	public FileService fileService() {
		return new DefaultFileService(Paths.get(videoFilesPath));
	}
	
	@Bean
	public MinimalVideoDjFacade facade() {
		return new MinimalVideoDjRestFacade(getVideoFileListUrl, startVideoUrl, stopVideoUrl, adjustUrl);
	}

}
