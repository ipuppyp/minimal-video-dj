package org.minimal.video.dj.domain;

import java.util.List;

public class VideoFileListResponse {

	private final List<String> videoFileList;

	public VideoFileListResponse(List<String> videoFileList) {
		super();
		this.videoFileList = videoFileList;
	}

	public List<String> getVideoFileList() {
		return videoFileList;
	}
	
}
