package com.ipuppyp.minimalvideodj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ipuppyp.minimalvideodj.service.FileService;

@Controller
public class VideoPageController {	 
	private final FileService fileService;
	
	@Autowired
	public VideoPageController(FileService fileService) {
		super();
		this.fileService = fileService;
	}
	
	@RequestMapping("/")
	public ModelAndView index(@RequestParam(value = "file", required = false) String file) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("fileList", fileService.getVideoFileList());
		return mv;
	}
}
