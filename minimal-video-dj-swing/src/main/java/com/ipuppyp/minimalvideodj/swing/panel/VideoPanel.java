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
	private final List<Path> fileList;
	private final MessageSource messageSource;

	JFrame frame;
	private Process actualProcess;
	
	public VideoPanel(VideoService videoService, FileService fileService, MessageSource messageSource) throws UnsupportedLookAndFeelException, IOException {
		super();
		fileList = fileService.getVideoFileList();
		this.messageSource = messageSource;
		initUI(videoService);
	}
	
	public void run() {		
		frame.setVisible(true);
	}
	
	private void initUI(VideoService videoService) throws UnsupportedLookAndFeelException, IOException {

        
		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
        setPreferredSize(new Dimension(700, 700));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));
                
        addKeyListener(new VideoPanelKeyListener(this, videoService));
        
        BufferedImage wPic = ImageIO.read(getClass().getResource(LOGO));
        JLabel wIcon = new JLabel(new ImageIcon(wPic));
        panel.add(wIcon);
                
        
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        for (int i = 0; i < fileList.size(); i ++) {
        	addVideoButton(videoService, panel, fileList.get(i), i);
        }

        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        JButton stopButton = new JButton(messageSource.getMessage("video_panel.stop", null, null) + " (X)");
        stopButton.addActionListener(new VideoPanelButtonStopActionListener(this, videoService));
        panel.add(stopButton);
        add(panel);
        
		frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setTitle(messageSource.getMessage("video_panel.title", null, null));
        frame.setIconImage(getDefaultToolkit().getImage(getClass().getResource(FRAME_ICON)));
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new VideoPanelWindowListener(this, videoService));
	
        frame.pack();

        
	}

	private void addVideoButton(VideoService videoService, JPanel panel, Path path, int index) {
		JPanel videoPanel = new JPanel();
		String label = path.getFileName().toString() + " (" + HOTKEY_LIST.get(index) + ")";
		videoPanel.add(new JLabel(label.toUpperCase()));
		JButton videoButton = new JButton(messageSource.getMessage("video_panel.start", null, null));
		videoButton.addActionListener(new VideoPanelButtonStartActionListener(this, videoService, path));
		videoPanel.add(videoButton);	
		panel.add(videoPanel);
	}
	
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    

	@Override
	public void requestFocus() {
		super.requestFocus();
		frame.toFront();
	}

	public Process getActualProcess() {
		return actualProcess;
	}

	public void setActualProcess(Process actualProcess) {
		this.actualProcess = actualProcess;
	}

	public List<Path> getFileList() {
		return Collections.unmodifiableList(fileList);
	}
	

}
