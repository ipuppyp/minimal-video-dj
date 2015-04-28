package com.ipuppyp.minimal_video_dj.configuration;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ipuppyp.minimal_video_dj.service.FileService;
import com.ipuppyp.minimal_video_dj.service.VideoService;
import com.ipuppyp.minimal_video_dj_swing.panel.VideoPanel;

@Configuration
public class SpringConfigurationSwing {
 
	
	@Bean
	public VideoPanel videoPanel(VideoService videoService, FileService fileService) throws UnsupportedLookAndFeelException, IOException {
		return new VideoPanel(videoService, fileService);
	}
	
}
