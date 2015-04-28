package com.ipuppyp.minimalvideodj.service;

import java.nio.file.Path;

public interface VideoService {

	Process startVideo(Path fileName);
	
	void destroyVideo(Process process);
}
