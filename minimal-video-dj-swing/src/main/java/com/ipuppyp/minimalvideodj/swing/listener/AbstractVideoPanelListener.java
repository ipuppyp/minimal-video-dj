package com.ipuppyp.minimalvideodj.swing.listener;

import java.nio.file.Path;

import org.minimal.video.dj.facade.MinimalVideoDjFacade;

import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

public abstract class AbstractVideoPanelListener {

	protected final VideoPanel videoPanel;
	protected final MinimalVideoDjFacade facade;

	public AbstractVideoPanelListener(VideoPanel videoPanel, MinimalVideoDjFacade facade) {
		super();
		this.videoPanel = videoPanel;
		this.facade = facade;
	}
	public void stopVideo() {
		facade.stopVideo();
		videoPanel.requestFocus();
	}

	public void startVideo(Path path) {
		stopVideo();
		videoPanel.requestFocus();
	}

}
