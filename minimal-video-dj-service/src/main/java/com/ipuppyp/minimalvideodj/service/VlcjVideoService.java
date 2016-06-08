package com.ipuppyp.minimalvideodj.service;

import java.nio.file.Path;

import com.ipuppyp.minimalvideodj.service.tutorial.Adjustment;


public class VlcjVideoService implements VideoService {

	private final VlcjFrame vlcjFrame;
	
	public VlcjVideoService(PreferencesService preferencesService) {
		vlcjFrame = new VlcjFrame(preferencesService);
	}
	
	@Override
	public void startVideo(Path fileName) {
		vlcjFrame.start(fileName);
	}

	@Override
	public void stopVideo() {
		vlcjFrame.stop();
	}

	public void adjust(Adjustment adjustment) {
		vlcjFrame.adjust(adjustment);
	}

}
