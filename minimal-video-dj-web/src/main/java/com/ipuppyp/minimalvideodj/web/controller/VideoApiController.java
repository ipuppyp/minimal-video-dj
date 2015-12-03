package com.ipuppyp.minimalvideodj.web.controller;

import java.io.File;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ipuppyp.minimalvideodj.service.FileService;
import com.ipuppyp.minimalvideodj.service.VideoService;
import com.ipuppyp.minimalvideodj.web.domain.GenericResponse;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoApiController {
	Logger LOGGER = LoggerFactory.getLogger(VideoApiController.class);
	
	private final FileService fileService;
	private final VideoService videoService;
	
	@Autowired
	public VideoApiController( VideoService videoService, FileService fileService) {
		this.videoService = videoService;
		this.fileService = fileService;
		
	}

	@RequestMapping("/start-video")
	public @ResponseBody GenericResponse startVideo(@RequestParam(value = "file") String file) {
		GenericResponse response = new GenericResponse(); 
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

	
}
