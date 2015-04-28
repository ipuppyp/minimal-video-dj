package com.ipuppyp.minimalvideodj.application;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

/**
 * Hello world!
 *
 */
public class Starter {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("com.ipuppyp.minimalvideodj.application.configuration");
		VideoPanel videoPanel = ctx.getBean(VideoPanel.class);
		videoPanel.run();
		ctx.close();
	}
}
