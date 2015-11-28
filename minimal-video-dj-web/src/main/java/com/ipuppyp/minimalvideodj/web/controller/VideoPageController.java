package com.ipuppyp.minimalvideodj.web.controller;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ipuppyp.minimalvideodj.service.FileService;
import com.ipuppyp.minimalvideodj.service.VideoService;

@Controller
public class VideoPageController {	 
	private final FileService fileService;
	private final VideoService videoService;
	
	@Autowired
	public VideoPageController( VideoService videoService, FileService fileService) {
		super();
		this.fileService = fileService;
		this.videoService = videoService;
	}
	
	@RequestMapping("/")
	public ModelAndView index(@RequestParam(value = "file", required = false) String file) {
		if (file != null) {			
			videoService.startVideo(Paths.get(fileService.getVideoFolderName() + File.separator + file));
		}
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("fileList", fileService.getVideoFileList());
		return mv;
	}

}
