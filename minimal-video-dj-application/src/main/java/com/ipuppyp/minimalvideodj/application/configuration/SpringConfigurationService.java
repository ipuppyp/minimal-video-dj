package com.ipuppyp.minimalvideodj.application.configuration;

import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.ipuppyp.minimalvideodj.service.DefaultFileService;
import com.ipuppyp.minimalvideodj.service.FileService;
import com.ipuppyp.minimalvideodj.service.VideoService;
import com.ipuppyp.minimalvideodj.service.VlcVideoService;
import com.ipuppyp.minimalvideodj.service.VlcjVideoService;

@Configuration
@PropertySource("classpath:minimal-video-dj.properties")
public class SpringConfigurationService {

	@Value("${video_files_path}")
	private String videoFilesPath;

	@Value("${video_allowed_types}")
	private String videoAllowedTypes;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

//	@Bean
//	public VideoService videoService() {
//		return new VlcVideoService(Arrays.asList("-L", "-f",
//				"--no-video-title-show", "--one-instance",
//				"--no-keyboard-events", "--disable-screensaver", "--no-mouse-events", "--directx-volume=-1"));
//	}

	@Bean
	public VideoService videoService() {
		return new VlcjVideoService();
	}
	
	@Bean
	public FileService fileService() {
		return new DefaultFileService(Paths.get(videoFilesPath),
				videoAllowedTypes);
	}
}
