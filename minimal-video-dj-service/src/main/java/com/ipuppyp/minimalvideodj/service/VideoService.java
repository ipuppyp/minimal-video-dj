package com.ipuppyp.minimalvideodj.service;

import java.nio.file.Path;

import com.ipuppyp.minimalvideodj.service.tutorial.Adjustment;

public interface VideoService {

	void startVideo(Path fileName);
	
	void stopVideo();
	
	void adjust(Adjustment adjustment);
	
}
