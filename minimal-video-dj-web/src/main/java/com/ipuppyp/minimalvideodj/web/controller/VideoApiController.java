package com.ipuppyp.minimalvideodj.web.controller;

import org.minimal.video.dj.domain.SimpleMessageResponse;
import org.minimal.video.dj.domain.VideoFileListResponse;
import org.minimal.video.dj.facade.MinimalVideoDjFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoApiController {
	Logger LOGGER = LoggerFactory.getLogger(VideoApiController.class);
	
	@Autowired
	private MinimalVideoDjFacade facade;

	@RequestMapping("/get-video-file-list")
	public @ResponseBody VideoFileListResponse getVideoFileList() {
		return facade.getVideoFileList();
	}

	
	@RequestMapping("/start-video")
	public @ResponseBody SimpleMessageResponse startVideo(@RequestParam(value = "file") String file) {
		return facade.startVideo(file);
	}


	@RequestMapping("/stop-video")
	public @ResponseBody SimpleMessageResponse stopVideo() {
		return facade.stopVideo();
	}

	
}
