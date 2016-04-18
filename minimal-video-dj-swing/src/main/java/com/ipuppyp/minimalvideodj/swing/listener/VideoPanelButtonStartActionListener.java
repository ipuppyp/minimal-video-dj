package com.ipuppyp.minimalvideodj.swing.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.minimal.video.dj.facade.MinimalVideoDjFacade;

import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

public class VideoPanelButtonStartActionListener extends AbstractVideoPanelListener implements ActionListener {

	public VideoPanelButtonStartActionListener(VideoPanel videoPanel, MinimalVideoDjFacade facade) {
		super(videoPanel, facade);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		videoPanel.requestFocus();
	}
	
	

	

}
