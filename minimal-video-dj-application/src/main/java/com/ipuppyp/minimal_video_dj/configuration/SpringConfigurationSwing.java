package com.ipuppyp.minimal_video_dj.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ipuppyp.minimal_video_dj.service.FileService;
import com.ipuppyp.minimal_video_dj.service.VideoService;
import com.ipuppyp.minimal_video_dj_swing.Screen;

@Configuration
public class SpringConfigurationSwing {
 
	
	@Bean
	public Screen app(VideoService videoService, FileService fileService) {
		return new Screen(videoService, fileService);
	}
	
}
