package com.ipuppyp.minimalvideodj.swing.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.minimal.video.dj.facade.MinimalVideoDjFacade;

import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

public class VideoPanelButtonStopActionListener extends AbstractVideoPanelListener implements ActionListener {

	public VideoPanelButtonStopActionListener(VideoPanel videoPanel, MinimalVideoDjFacade facade) {
		super(videoPanel, facade);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		facade.stopVideo();
	}
	
	

	

}
