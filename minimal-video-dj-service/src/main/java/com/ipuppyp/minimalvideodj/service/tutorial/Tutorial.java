package com.ipuppyp.minimalvideodj.service.tutorial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class Tutorial {

	String[] args;
	private JFrame frame;

	private EmbeddedMediaPlayerComponent mediaPlayerComponent;

	private JPanel north;
	private JPanel south;
	private JPanel east;
	private JPanel west;
	
	public static void main(final String[] args) throws InterruptedException {
		Tutorial t = new Tutorial(args);

		new NativeDiscovery().discover();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				t.init();
			}
		});

//		for (int i = 0; i < 20; i ++) {
//			t.resize(t.east, false, i * 10);
//		}
//
//		for (int i = 20; i >= 0; i--) {
//			t.resize(t.east, false, i * 10);
//		}
//		
		for (int i = 0; i < 5; i ++) {
			t.resize(t.north, true, i * 5);
			t.resize(t.south, true, i * 5);
		}
		
		for (int i = 5; i >= 0; i --) {
			t.resize(t.north, true, i * 5);
			t.resize(t.south, true, i * 5);
		}

	}

	private void resize(JPanel holder, boolean vertical, int size) throws InterruptedException {
		Thread.sleep(500);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public synchronized void run() {
				holder.removeAll();
				initHolder(holder, size, vertical);
				frame.repaint();
				frame.revalidate();
			}
		});

	}

	public Tutorial(String[] args) {
		this.args = args;
	}

	public void init() {
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

		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setLayout(new BorderLayout());

		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

		north = initHolder(new JPanel(), 0, true);
		south = initHolder(new JPanel(), 0, true);
		east = initHolder(new JPanel(), 0, false);
		west = initHolder(new JPanel(), 0, false);
		
		contentPane.add(north, BorderLayout.NORTH);
		contentPane.add(south, BorderLayout.SOUTH);
		contentPane.add(east, BorderLayout.EAST);
		contentPane.add(west, BorderLayout.WEST);

		contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

		frame.setContentPane(contentPane);
		frame.setVisible(true);

		mediaPlayerComponent.getMediaPlayer().setRepeat(true);
		mediaPlayerComponent.getMediaPlayer().playMedia(args[0]);

	}

	private JPanel initHolder(JPanel holder, int size, boolean vertical) {
		StringBuilder builder = new StringBuilder();
		if (size > 0) {
			builder.append("<html>");
			for (int i = 0; i < size; i++) {
				builder.append("X");
				builder.append(vertical ? "<br>" : "");
			}
			builder.append("</html>");

			JLabel l = new JLabel(builder.toString());
			// l.setForeground(Color.BLACK);
			holder.add(l);
			holder.setBackground(Color.BLACK);
		}
		return holder;
	}
}