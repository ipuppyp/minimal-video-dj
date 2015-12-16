package com.ipuppyp.minimalvideodj.swing.panel;

import static com.ipuppyp.minimalvideodj.swing.panel.HotKeyList.HOTKEY_LIST;
import static com.ipuppyp.minimalvideodj.swing.panel.VideoPanelImages.FRAME_ICON;
import static com.ipuppyp.minimalvideodj.swing.panel.VideoPanelImages.LOGO;
import static java.awt.Toolkit.getDefaultToolkit;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

import org.minimal.video.dj.facade.MinimalVideoDjFacade;
import org.springframework.context.MessageSource;

import com.ipuppyp.minimalvideodj.swing.listener.VideoPanelButtonStartActionListener;
import com.ipuppyp.minimalvideodj.swing.listener.VideoPanelButtonStopActionListener;
import com.ipuppyp.minimalvideodj.swing.listener.VideoPanelKeyListener;
import com.ipuppyp.minimalvideodj.swing.listener.VideoPanelWindowListener;

public class VideoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final List<String> fileList;
	private final MessageSource messageSource;

	JFrame frame;
	
	public VideoPanel(MinimalVideoDjFacade facade, MessageSource messageSource) throws UnsupportedLookAndFeelException, IOException {
		super();
		fileList = facade.getVideoFileList().getVideoFileList();
		this.messageSource = messageSource;
		initUI(facade);
	}
	
	public void run() {		
		frame.setVisible(true);
	}
	
	@SuppressWarnings("restriction")
	private void initUI(MinimalVideoDjFacade facade) throws UnsupportedLookAndFeelException, IOException {

        
		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
        setPreferredSize(new Dimension(700, 700));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));
                
        addKeyListener(new VideoPanelKeyListener(this, facade));
        
        BufferedImage wPic = ImageIO.read(getClass().getResource(LOGO));
        JLabel wIcon = new JLabel(new ImageIcon(wPic));
        panel.add(wIcon);
                
        
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        for (int i = 0; i < fileList.size(); i ++) {
        	addVideoButton(facade, panel, fileList.get(i), i);
        }

        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        JButton stopButton = new JButton(messageSource.getMessage("video_panel.stop", null, null) + " (X)");
        stopButton.addActionListener(new VideoPanelButtonStopActionListener(this, facade));
        panel.add(stopButton);
        add(panel);
        
		frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setTitle(messageSource.getMessage("video_panel.title", null, null));
        frame.setIconImage(getDefaultToolkit().getImage(getClass().getResource(FRAME_ICON)));
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new VideoPanelWindowListener(this, facade));
	
        frame.pack();

        
	}

	private void addVideoButton(MinimalVideoDjFacade facade, JPanel panel, String file, int index) {
		JPanel videoPanel = new JPanel();
		String label = file + " (" + HOTKEY_LIST.get(index) + ")";
		videoPanel.add(new JLabel(label.toUpperCase()));
		JButton videoButton = new JButton(messageSource.getMessage("video_panel.start", null, null));
		videoButton.addActionListener(new VideoPanelButtonStartActionListener(this, facade));
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

	public List<String> getFileList() {
		return Collections.unmodifiableList(fileList);
	}
	

}
