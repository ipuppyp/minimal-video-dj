package com.ipuppyp.minimalvideodj.service;

import java.nio.file.Path;

public class VlcjVideoService implements VideoService {

	VlcjFrame vlcjFrame;
	
	public VlcjVideoService() {
		vlcjFrame = new VlcjFrame();
	}
	
	@Override
	public Process startVideo(Path fileName) {
		vlcjFrame.startMedia(fileName);
		return null;
	}

	@Override
	public void destroyVideo(Process process) {
		vlcjFrame.stop();
	}

}
