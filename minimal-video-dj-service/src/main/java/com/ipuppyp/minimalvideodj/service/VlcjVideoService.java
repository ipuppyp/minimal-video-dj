package com.ipuppyp.minimalvideodj.service;

import java.nio.file.Path;

public class VlcjVideoService implements VideoService {

	
	
	@Override
	public Process startVideo(Path fileName) {
		VlcjFrame.playMedia(fileName.toString());
		return null;
	}

	@Override
	public void destroyVideo(Process process) {
		// TODO Auto-generated method stub
		
	}

}
