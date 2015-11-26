package com.ipuppyp.minimalvideodj.service;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.embedded.DefaultAdaptiveRuntimeFullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class VlcjFrame {
	private final JFrame frame;
	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	
	
	public static void playMedia(String media) {
		//new NativeDiscovery().discover();
		NativeLibrary.addSearchPath
			(RuntimeUtil.getLibVlcLibraryName(), 
					"C:/Program Files (x86)/VideoLAN/VLC");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VlcjFrame(media);
            }
        });
	}
    
	
	public VlcjFrame(String media) {
		frame = new JFrame("My First Media Player");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayerComponent.getMediaPlayer().setFullScreenStrategy(
        	    new DefaultAdaptiveRuntimeFullScreenStrategy(frame)
        	);
        frame.setContentPane(mediaPlayerComponent);
        frame.setVisible(true);
        mediaPlayerComponent.getMediaPlayer().toggleFullScreen();
        mediaPlayerComponent.getMediaPlayer().playMedia(media);
    }
}
