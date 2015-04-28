package com.ipuppyp.minimal_video_dj_application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ipuppyp.minimal_video_dj_swing.panel.VideoPanel;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext("com.ipuppyp.minimal_video_dj.configuration");
		VideoPanel videoPanel = ctx.getBean(VideoPanel.class);
		videoPanel.run();
	}
}
