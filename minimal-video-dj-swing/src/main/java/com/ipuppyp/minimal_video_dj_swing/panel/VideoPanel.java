package com.ipuppyp.minimal_video_dj_swing.panel;

import static com.ipuppyp.minimal_video_dj_swing.panel.HotKeyList.HOTKEY_LIST;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.ipuppyp.minimal_video_dj.service.FileService;
import com.ipuppyp.minimal_video_dj.service.VideoService;
import com.ipuppyp.minimal_video_dj_swing.StartVideoKeyListener;

/**
 * Hello world!
 *
 */
public class VideoPanel extends JPanel {
	private final VideoService videoService;
	private final Stream<Path> fileList;
	private Process actualProcess;
	

	public VideoPanel(VideoService videoService, FileService fileService) throws UnsupportedLookAndFeelException, IOException {
		super();
		this.videoService = videoService;
		fileList = fileService.getVideoFileList();
		initUI();
	}
	
	public void run() {
		
		JFrame f = new JFrame();
        f.getContentPane().add(this);
        f.setTitle("Minimal Video DJ");
        f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/frame_icon.png")));
        
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				if (actualProcess != null) {
					videoService.destroyVideo(actualProcess);
					actualProcess = null; 
				}		
			}
			
		});
		
        f.pack();
        f.setVisible(true);
	}
	
	private void initUI() throws UnsupportedLookAndFeelException, IOException {
		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
        setPreferredSize(new Dimension(700, 700));
        addKeyListener(new StartVideoKeyListener());
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));
        
        
        addKeyListener(new KeyListener() {
			
            public void keyPressed(KeyEvent e) { }
            public void keyReleased(KeyEvent e) { }
            public void keyTyped(KeyEvent e) {
            	if (actualProcess != null) {
					videoService.destroyVideo(actualProcess);
				}
            	System.out.println();
            	System.out.println(HOTKEY_LIST[0]);
            	int i = Arrays.asList(HOTKEY_LIST).indexOf(Character.valueOf(e.getKeyChar()));
            	if (i >= 0) {            		            	
            		actualProcess = videoService.startVideo((Path)fileList.toArray()[i]);
            	}
				
                System.out.print(e.getKeyChar());
            }
		});
        
        BufferedImage wPic = ImageIO.read(this.getClass().getResource("/icons/logo.png"));
        JLabel wIcon = new JLabel(new ImageIcon(wPic));
        panel.add(wIcon);
                
        
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        int[] idx = { 0 };
        fileList.forEach(p -> {
        	addVideoButton(panel, p, idx[0]++);
        }); 
        
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (actualProcess != null) {
					videoService.destroyVideo(actualProcess);
					actualProcess = null; 
				}
				requestFocus();
			}
		});
        panel.add(stopButton);
        add(panel);
	}

	private void addVideoButton(JPanel panel, Path path, int index) {
		JPanel videoPanel = new JPanel();
		String label = path.getFileName().toString() + " (" + HOTKEY_LIST[index] + ")";
		videoPanel.add(new JLabel(label.toUpperCase()));
		JButton videoButton = new JButton("Start");
		videoButton.addActionListener(new ActionListener() {    			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (actualProcess != null) {
					videoService.destroyVideo(actualProcess);
				}
				actualProcess = videoService.startVideo(path);
				requestFocus();
			}
		});
		videoPanel.add(videoButton);	
		panel.add(videoPanel);
	}
	
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    
}
