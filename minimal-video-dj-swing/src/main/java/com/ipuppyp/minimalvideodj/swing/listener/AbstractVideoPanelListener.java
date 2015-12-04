package com.ipuppyp.minimalvideodj.swing.listener;

import java.nio.file.Path;

import com.ipuppyp.minimalvideodj.service.VideoService;
import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

public abstract class AbstractVideoPanelListener {

	protected final VideoPanel videoPanel;
	protected final VideoService videoService;

	public AbstractVideoPanelListener(VideoPanel videoPanel,
			VideoService videoService) {
		super();
		this.videoPanel = videoPanel;
		this.videoService = videoService;
	}

	public void stopVideo() {
		videoService.stopVideo();
		videoPanel.requestFocus();
	}

	public void startVideo(Path path) {
		stopVideo();
		videoPanel.requestFocus();
	}

}
