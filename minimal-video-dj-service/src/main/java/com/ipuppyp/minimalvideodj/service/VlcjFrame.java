package com.ipuppyp.minimalvideodj.service;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.embedded.DefaultAdaptiveRuntimeFullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class VlcjFrame {
	private JFrame frame;
	private EmbeddedMediaPlayer mediaPlayer;

	static {
		new NativeDiscovery().discover();
	}

	public void startMedia(Path file) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (frame == null || !frame.isVisible()) {
					init();
				}
				mediaPlayer.startMedia(file.toString());
			}
		});
	}

	public void stop() {
		frame.setVisible(false);
	}

	private void init() {
		
		frame = new JFrame("Minimal Video DJ");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mediaPlayer.release();
				frame.setVisible(false);
			}
		});
		EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		mediaPlayer = mediaPlayerComponent.getMediaPlayer();
		mediaPlayer.setFullScreenStrategy(new DefaultAdaptiveRuntimeFullScreenStrategy(frame));
		mediaPlayer.setRepeat(true);
		frame.setContentPane(mediaPlayerComponent);
		frame.setVisible(true);
		mediaPlayer.setFullScreen(true);

	}
}
