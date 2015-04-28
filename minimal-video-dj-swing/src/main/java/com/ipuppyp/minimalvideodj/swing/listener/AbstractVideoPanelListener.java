package com.ipuppyp.minimalvideodj.swing.listener;

import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

public abstract class AbstractVideoPanelListener {

	private final VideoPanel videoPanel;
	
	public AbstractVideoPanelListener(VideoPanel videoPanel) {
		super();
		this.videoPanel = videoPanel;
	}

	public VideoPanel getVideoPanel() {
		return videoPanel;
	}

	
	
}
