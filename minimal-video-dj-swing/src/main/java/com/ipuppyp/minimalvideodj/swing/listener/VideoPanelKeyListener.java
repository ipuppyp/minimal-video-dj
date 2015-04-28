package com.ipuppyp.minimalvideodj.swing.listener;

import static com.ipuppyp.minimalvideodj.swing.panel.HotKeyList.HOTKEY_LIST;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.Path;

import com.ipuppyp.minimalvideodj.swing.panel.VideoPanel;

public class VideoPanelKeyListener extends AbstractVideoPanelListener implements KeyListener {

	public VideoPanelKeyListener(VideoPanel videoPanel) {
		super(videoPanel);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == 'x') {
			getVideoPanel().destroyVideo();
		}
		else {
			int i = HOTKEY_LIST.indexOf(e.getKeyChar() );
			if (i >= 0) {			
				Path path = getVideoPanel().getFileList().get(i);
				if (path != null) {            		            	
					getVideoPanel().startVideo(path);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing		
	}

}
