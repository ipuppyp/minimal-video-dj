package com.ipuppyp.minimal_video_dj.service;

import java.nio.file.Path;

public interface VideoService {

	Process startVideo(Path fileName);
	
	void destroyVideo(Process process);
}
