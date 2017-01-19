package com.ipuppyp.minimalvideodj.service;

import static com.ipuppyp.minimalvideodj.service.tutorial.Adjustment.RESET;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ipuppyp.minimalvideodj.service.tutorial.Adjustment;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class VlcjFrame {
	private JFrame frame;
	private EmbeddedMediaPlayer mediaPlayer;

	private JPanel top;
	private JPanel bottom;
	private JPanel right;
	private JPanel left;
	private final PreferencesService preferencesService;

	public VlcjFrame(PreferencesService preferencesService) {
		this.preferencesService = preferencesService;
		new NativeDiscovery().discover();
		
	}

	public void start(Path file) {
		init();
		mediaPlayer.startMedia(file.toString());
	}

	public void stop() {
		init();
		frame.setVisible(false);
	}

	public void init() {
		if (frame == null || !frame.isVisible()) {
			frame = new JFrame();
			frame.setUndecorated(true);
			setFullScreen(frame);
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.setBackground(Color.BLACK);
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					mediaPlayer.release();
					frame.setVisible(false);
				}
			});
			frame.setContentPane(createContentPane());
			frame.setVisible(true);
		}
	}

	
	public void adjust(Adjustment adjustment) {
		init();
		if (adjustment == RESET) {
			resetFrame();
		}	
		else if (adjustment == Adjustment.TOGGLE_FULL_SCREEN) {
			toggleFullscreenFrame(); 
		}
		else {
			moveOrZoomFrame(adjustment);
		}
		right.removeAll(); // TODO: find a better solution
		frame.revalidate();
		frame.repaint();
		preferencesService.storeRightBorder(right.getPreferredSize().width);
		preferencesService.storeLeftBorder(left.getPreferredSize().width);
		preferencesService.storeTopBorder(top.getPreferredSize().height);
		preferencesService.storeBottomBorder(bottom.getPreferredSize().height);
	}

	private void moveOrZoomFrame(Adjustment adjustment) {
		Dimension actualEast = right.getPreferredSize();
		Dimension actualWest = left.getPreferredSize();
		Dimension actualNorth = top.getPreferredSize();
		Dimension actualSouth = bottom.getPreferredSize();
		right.setPreferredSize(new Dimension(actualEast.width + adjustment.getRight(), 0));
		left.setPreferredSize(new Dimension(actualWest.width + adjustment.getLeft(), 0));
		top.setPreferredSize(new Dimension(0, actualNorth.height + adjustment.getTop()));
		bottom.setPreferredSize(new Dimension(0, actualSouth.height + adjustment.getBottom()));
	}

	private void toggleFullscreenFrame() {
		frame.dispose();
		boolean newUndecoratedState = !frame.isUndecorated();
		frame.setUndecorated(newUndecoratedState);
		if (!newUndecoratedState) {				
			setFullScreen(frame);
		}
		frame.setVisible(true);
	}

	private void resetFrame() {
		right.setPreferredSize(new Dimension());
		left.setPreferredSize(new Dimension());
		top.setPreferredSize(new Dimension());
		bottom.setPreferredSize(new Dimension());
	}

	private void setFullScreen(JFrame frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, dimension.width, dimension.height);
	}
	
	private JPanel createContentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setLayout(new BorderLayout());

		EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		mediaPlayer = mediaPlayerComponent.getMediaPlayer();
		mediaPlayer.setRepeat(true);
		mediaPlayer.setVolume(0);
		
		top = createBorderPanel(0, preferencesService.getTopBorder());
		bottom = createBorderPanel(0, preferencesService.getBottonBorder());
		right = createBorderPanel(preferencesService.getRightBorder(), 0);
		left = createBorderPanel(preferencesService.getLeftBorder(), 0);

		contentPane.add(top, BorderLayout.NORTH);
		contentPane.add(bottom, BorderLayout.SOUTH);
		contentPane.add(right, BorderLayout.EAST);
		contentPane.add(left, BorderLayout.WEST);
		contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

		return contentPane;
	}

	private JPanel createBorderPanel(int width, int  height) {
		JPanel holder = new JPanel(new FlowLayout());
		holder.setBackground(Color.BLACK);
		holder.setPreferredSize(new Dimension(width, height));
		return holder;
	}

}
