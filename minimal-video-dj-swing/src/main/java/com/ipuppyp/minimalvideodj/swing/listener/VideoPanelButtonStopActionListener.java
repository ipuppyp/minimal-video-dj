package com.ipuppyp.minimalvideodj.swing.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

public class VideoPanelButtonStopActionListener extends AbstractVideoPanelListener implements ActionListener {

	public VideoPanelButtonStopActionListener(VideoPanel videoPanel) {
		super(videoPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getVideoPanel().destroyVideo();
	}
	
	

	

}
