package com.ipuppyp.minimalvideodj.swing.panel;

import static com.ipuppyp.minimalvideodj.swing.panel.HotKeyList.HOTKEY_LIST;
import static com.ipuppyp.minimalvideodj.swing.panel.VideoPanelImages.FRAME_ICON;
import static com.ipuppyp.minimalvideodj.swing.panel.VideoPanelImages.LOGO;
import static java.awt.Toolkit.getDefaultToolkit;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

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

import org.springframework.context.MessageSource;

import com.ipuppyp.minimalvideodj.service.FileService;
import com.ipuppyp.minimalvideodj.service.VideoService;
import com.ipuppyp.minimalvideodj.swing.listener.VideoPanelButtonStartActionListener;
import com.ipuppyp.minimalvideodj.swing.listener.VideoPanelButtonStopActionListener;
import com.ipuppyp.minimalvideodj.swing.listener.VideoPanelKeyListener;
import com.ipuppyp.minimalvideodj.swing.listener.VideoPanelWindowListener;

/**
 * Hello world!
 *
 */
public class VideoPanel extends JPanel {
	private final VideoService videoService;
	private final List<Path> fileList;
	private final MessageSource messageSource;


	private Process actualProcess;
	
	public VideoPanel(VideoService videoService, FileService fileService, MessageSource messageSource) throws UnsupportedLookAndFeelException, IOException {
		super();
		this.videoService = videoService;
		fileList = fileService.getVideoFileList();
		this.messageSource = messageSource;
		initUI();
	}
	
	public void run() {
		
		JFrame f = new JFrame();
        f.getContentPane().add(this);
        f.setTitle(messageSource.getMessage("video_panel.title", null, null));
        f.setIconImage(getDefaultToolkit().getImage(getClass().getResource(FRAME_ICON)));
        
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new VideoPanelWindowListener(this));
		
        f.pack();
        f.setVisible(true);
	}
	
	private void initUI() throws UnsupportedLookAndFeelException, IOException {
		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
        setPreferredSize(new Dimension(700, 700));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));
                
        addKeyListener(new VideoPanelKeyListener(this));
        
        BufferedImage wPic = ImageIO.read(getClass().getResource(LOGO));
        JLabel wIcon = new JLabel(new ImageIcon(wPic));
        panel.add(wIcon);
                
        
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        for (int i = 0; i < fileList.size(); i ++) {
        	addVideoButton(panel, fileList.get(i), i);
        }

        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        JButton stopButton = new JButton(messageSource.getMessage("video_panel.stop", null, null) + " (X)");
        stopButton.addActionListener(new VideoPanelButtonStopActionListener(this));
        panel.add(stopButton);
        add(panel);
	}

	private void addVideoButton(JPanel panel, Path path, int index) {
		JPanel videoPanel = new JPanel();
		String label = path.getFileName().toString() + " (" + HOTKEY_LIST.get(index) + ")";
		videoPanel.add(new JLabel(label.toUpperCase()));
		JButton videoButton = new JButton(messageSource.getMessage("video_panel.start", null, null));
		videoButton.addActionListener(new VideoPanelButtonStartActionListener(this, path));
		videoPanel.add(videoButton);	
		panel.add(videoPanel);
	}
	
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

	public Process getActualProcess() {
		return actualProcess;
	}

	public List<Path> getFileList() {
		return Collections.unmodifiableList(fileList);
	}
	
	public void destroyVideo() {
		if (actualProcess !=  null) {
			videoService.destroyVideo(actualProcess);
			actualProcess = null;
		}	
		requestFocus();
	}
	
	public void startVideo(Path path) {
		destroyVideo();
		actualProcess = videoService.startVideo(path);
		requestFocus();
	}
}
