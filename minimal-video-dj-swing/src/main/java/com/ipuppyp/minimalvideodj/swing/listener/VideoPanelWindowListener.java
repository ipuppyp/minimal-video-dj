package com.ipuppyp.minimalvideodj.swing.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.minimal.video.dj.facade.MinimalVideoDjFacade;

import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

public class VideoPanelWindowListener extends AbstractVideoPanelListener implements WindowListener {

	public VideoPanelWindowListener(VideoPanel videoPanel, MinimalVideoDjFacade facade) {
		super(videoPanel,facade);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// no nothing
	}

	@Override
	public void windowClosing(WindowEvent e) {		
		facade.stopVideo();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// no nothing
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// no nothing
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// do nothing
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// do nothing
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// do nothing
	}

}
