package org.minimal.video.dj.facade;

import org.minimal.video.dj.domain.SimpleMessageResponse;
import org.minimal.video.dj.domain.VideoFileListResponse;

public interface MinimalVideoDjFacade {

	VideoFileListResponse getVideoFileList();

	SimpleMessageResponse startVideo(String file);
	
	SimpleMessageResponse stopVideo();
}