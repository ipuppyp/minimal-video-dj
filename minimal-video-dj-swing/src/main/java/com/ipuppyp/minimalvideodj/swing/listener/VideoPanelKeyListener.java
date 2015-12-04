package com.ipuppyp.minimalvideodj.swing.listener;

import static com.ipuppyp.minimalvideodj.swing.panel.HotKeyList.HOTKEY_LIST;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.ipuppyp.minimalvideodj.service.VideoService;
import com.ipuppyp.minimalvideodj.swing.panel.HotKeyList;
import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

public class VideoPanelKeyListener extends AbstractVideoPanelListener implements
		KeyListener {

	public VideoPanelKeyListener(VideoPanel videoPanel,
			VideoService videoService) {
		super(videoPanel, videoService);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing

	}

	@Override
	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == HotKeyList.HOTKEY_EXIT) {
			videoService.stopVideo();
		} else {
			int i = HOTKEY_LIST.indexOf(e.getKeyChar());
			if (i >= 0 && i < videoPanel.getFileList().size()) {
				videoService.startVideo(videoPanel.getFileList().get(i));
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

}
