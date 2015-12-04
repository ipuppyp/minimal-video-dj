package com.ipuppyp.minimalvideodj.service;

import java.nio.file.Path;

public interface VideoService {

	void startVideo(Path fileName);
	
	void stopVideo();
}
