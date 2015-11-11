package com.ipuppyp.minimalvideodj.swing.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ipuppyp.minimalvideodj.service.VideoService;
import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

public class VideoPanelButtonStopActionListener extends AbstractVideoPanelListener implements ActionListener {

	public VideoPanelButtonStopActionListener(VideoPanel videoPanel, VideoService videoService) {
		super(videoPanel, videoService);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		videoService.destroyVideo(videoPanel.getActualProcess());
	}
	
	

	

}
