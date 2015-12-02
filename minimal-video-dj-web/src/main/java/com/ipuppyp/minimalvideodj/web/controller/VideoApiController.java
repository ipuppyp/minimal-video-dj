package com.ipuppyp.minimalvideodj.web.controller;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ipuppyp.minimalvideodj.service.FileService;
import com.ipuppyp.minimalvideodj.service.VideoService;
import com.ipuppyp.minimalvideodj.web.domain.StartVideoResponse;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoApiController {

	private final FileService fileService;
	private final VideoService videoService;
	
	@Autowired
	public VideoApiController( VideoService videoService, FileService fileService) {
		this.fileService = fileService;
		this.videoService = videoService;
	}

	
	
	@RequestMapping(value = "/start-video")
    public String getAllEmployeesJSON(@RequestParam(value = "file", required = false) String file, Model model)
    {
		StartVideoResponse response = new StartVideoResponse();
		response.setMessage("Video started"); 
        model.addAttribute("employees", response);
        return "jsonTemplate";
    }
	
	@RequestMapping("/start-video2")
	public StartVideoResponse startVideo(@RequestParam(value = "file", required = false) String file) {
		StartVideoResponse response = new StartVideoResponse(); 
		if (file != null) {			
				//videoService.startVideo(Paths.get(fileService.getVideoFolderName() + File.separator + file));				
				response.setMessage("Video started"); 
		}
		else {
			response.setMessage("Please add a video name");
		}
		return response;
	}

	
}