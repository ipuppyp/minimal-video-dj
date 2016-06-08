package org.minimal.video.dj.facade;

import java.io.File;
import java.nio.file.Paths;

import org.minimal.video.dj.domain.SimpleMessageResponse;
import org.minimal.video.dj.domain.VideoFileListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipuppyp.minimalvideodj.service.FileService;
import com.ipuppyp.minimalvideodj.service.VideoService;
import com.ipuppyp.minimalvideodj.service.tutorial.Adjustment;

public class MinimalVideoDjServiceFacade implements MinimalVideoDjFacade {
	private static final Logger LOGGER = LoggerFactory.getLogger(MinimalVideoDjServiceFacade.class);
	
	private final FileService fileService;
	private final VideoService videoService;
	
	
	public MinimalVideoDjServiceFacade(FileService fileService, VideoService videoService) {
		super();
		this.fileService = fileService;
		this.videoService = videoService;
	}

	public VideoFileListResponse getVideoFileList() {
		return new VideoFileListResponse(fileService.getVideoFileList());
	}
	
	public SimpleMessageResponse startVideo(String file) {
		SimpleMessageResponse response = new SimpleMessageResponse(); 
		try {
			videoService.startVideo(Paths.get(fileService.getVideoFolderName() + File.separator + file));
			response.setMessage("Started.");
		}
		catch (Exception ex) {
			response.setMessage("Error: " + ex.getMessage());
			LOGGER.error("Exception on starting video: ", ex);
		}
		return response;
	}

	@Override
	public SimpleMessageResponse stopVideo() {
		SimpleMessageResponse response = new SimpleMessageResponse(); 
		try {
			videoService.stopVideo();
			response.setMessage("Stopped.");
		}
		catch (Exception ex) {
			response.setMessage("Error: " + ex.getMessage());
			LOGGER.error("Exception on stopping video: ", ex);
		}
		return response;
	}
	
	@Override
	public SimpleMessageResponse adjust(Adjustment adjustment) {
		SimpleMessageResponse response = new SimpleMessageResponse(); 
		try {
			videoService.adjust(adjustment);
			response.setMessage(adjustment + " adjustment OK.");
		}
		catch (Exception ex) {
			response.setMessage("Error: " + ex.getMessage());
			LOGGER.error("Exception on adjusting: ", ex);
		}
		return response;
	}
}
