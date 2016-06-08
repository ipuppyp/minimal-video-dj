package com.ipuppyp.minimalvideodj.service.tutorial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class Tutorial {

	private JFrame frame;

	private EmbeddedMediaPlayerComponent mediaPlayerComponent;
	
	private JPanel top;
	private JPanel bottom;
	private JPanel right;
	private JPanel left;
	
	public static void main(final String[] args) throws InterruptedException {
		Tutorial t = new Tutorial();

		new NativeDiscovery().discover();
		t.init();
		t.ply("C:\\video\\vj\\teknosbeka.mp4");
		
		doIt5Time(t, Adjustment.ZOOM_IN);
		doIt5Time(t, Adjustment.MOVE_LEFT);
		
		t.ply("C:\\video\\vj\\_borito.mp4");
		t.ply("C:\\video\\vj\\teknosbeka.mp4");
		t.ply("C:\\video\\vj\\_borito.mp4");
//			doIt5Time(t, Adjustment.MOVE_RIGHT);
//			doIt5Time(t, Adjustment.MOVE_UP);
//			doIt5Time(t, Adjustment.MOVE_DOWN);
//			doIt5Time(t, Adjustment.ZOOM_OUT);

	}

	
	private static void doIt5Time(Tutorial t, Adjustment manipulate) throws InterruptedException {
		t.adjust(manipulate);
		t.adjust(manipulate);
		t.adjust(manipulate);
		t.adjust(manipulate);
		t.adjust(manipulate);
	}
	private void adjust(Adjustment adjustment) throws InterruptedException {
		Thread.sleep(500);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				synchronized (Tutorial.this) {					
					Dimension actualEast = right.getPreferredSize();
					Dimension actualWest = left.getPreferredSize();
					Dimension actualNorth = top.getPreferredSize();
					Dimension actualSouth = bottom.getPreferredSize();
					right.setPreferredSize(new Dimension(actualEast.width + adjustment.getRight(), 0));
					left.setPreferredSize(new Dimension(actualWest.width + adjustment.getLeft(), 0));
					top.setPreferredSize(new Dimension(0, actualNorth.height + adjustment.getTop()));
					bottom.setPreferredSize(new Dimension(0, actualSouth.height + adjustment.getBottom()));
					right.removeAll();
					frame.revalidate();
					frame.repaint();
				}
			}
		});

	}

	private void ply(String s) throws InterruptedException {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				synchronized (Tutorial.this) {					
					mediaPlayerComponent.getMediaPlayer().playMedia(s);
				}
			}
		});
	}

	public void init() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				synchronized (Tutorial.this) {
					frame = new JFrame("My First Media Player");
					frame.setUndecorated(true);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setBounds(0, 0, dimension.width, dimension.height);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					frame.setBackground(Color.BLACK);
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							mediaPlayerComponent.release();
							System.exit(0);
						}
					});
					frame.setContentPane(createContentPane());
					frame.setVisible(true);
				}
			}
		});

	}

	private JPanel createContentPane() {
		JPanel contentPane = new JPanel();		
		contentPane.setBackground(Color.BLACK);
		contentPane.setLayout(new BorderLayout());

		
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		mediaPlayerComponent.getMediaPlayer().setRepeat(true);

		top = createBorderPanel();
		bottom = createBorderPanel();
		right = createBorderPanel();
		left = createBorderPanel();

		contentPane.add(top, BorderLayout.NORTH);
		contentPane.add(bottom, BorderLayout.SOUTH);
		contentPane.add(right, BorderLayout.EAST);
		contentPane.add(left, BorderLayout.WEST);
		contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);
		

		return contentPane;
	}

	private JPanel createBorderPanel() {
		JPanel holder = new JPanel(new FlowLayout());
		holder.setBackground(Color.BLACK);
		holder.setPreferredSize(new Dimension(0, 0));
		return holder;
	}
}