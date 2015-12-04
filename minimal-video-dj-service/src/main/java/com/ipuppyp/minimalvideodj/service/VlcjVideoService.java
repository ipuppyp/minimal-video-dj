package com.ipuppyp.minimalvideodj.service;

import java.nio.file.Path;

public class VlcjVideoService implements VideoService {

	VlcjFrame vlcjFrame;
	
	public VlcjVideoService() {
		vlcjFrame = new VlcjFrame();
	}
	
	@Override
	public void startVideo(Path fileName) {
		vlcjFrame.startMedia(fileName);
	}

	@Override
	public void stopVideo() {
		vlcjFrame.stop();
	}

}
