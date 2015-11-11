package com.ipuppyp.minimalvideodj.swing.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

import com.ipuppyp.minimalvideodj.service.VideoService;
import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

public class VideoPanelButtonStartActionListener extends AbstractVideoPanelListener implements ActionListener {

	private final Path path;
	
	public VideoPanelButtonStartActionListener(VideoPanel videoPanel, VideoService videoService, Path path) {
		super(videoPanel, videoService);
		this.path = path;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		videoPanel.setActualProcess(videoService.startVideo(path));
		videoPanel.requestFocus();

	}
	
	

	

}
