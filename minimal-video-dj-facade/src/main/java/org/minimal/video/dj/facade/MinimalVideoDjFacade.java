package org.minimal.video.dj.facade;

import org.minimal.video.dj.domain.SimpleMessageResponse;
import org.minimal.video.dj.domain.VideoFileListResponse;

import com.ipuppyp.minimalvideodj.service.tutorial.Adjustment;

public interface MinimalVideoDjFacade {

	VideoFileListResponse getVideoFileList();

	SimpleMessageResponse startVideo(String file);
	
	SimpleMessageResponse stopVideo();
	
	SimpleMessageResponse adjust(Adjustment adjustment);
	
}