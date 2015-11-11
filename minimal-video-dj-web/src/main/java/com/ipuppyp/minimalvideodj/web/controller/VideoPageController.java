package com.ipuppyp.minimalvideodj.web.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ipuppyp.minimalvideodj.service.FileService;
import com.ipuppyp.minimalvideodj.service.VideoService;

@Controller
public class VideoPageController {
	String message = "Welcome to Spring MVC!";
	 
	private final List<Path> fileList;
	private final MessageSource messageSource;
	private final VideoService videoService;
	
	@Autowired
	public VideoPageController( VideoService videoService, FileService fileService, MessageSource messageSource) {
		super();
		fileList = fileService.getVideoFileList();
		this.videoService = videoService;
		this.messageSource = messageSource;
	}
	
	
	@RequestMapping("/hello")
	public ModelAndView showMessage(			
		@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("hello-world");
		mv.addObject("message", message);
		mv.addObject("name", name);
		mv.addObject("fileList", fileList);
		return mv;
	}
	
	@RequestMapping("/start")
	public ModelAndView startVideo(			
		@RequestParam(value = "file", required = false, defaultValue = "XXXX") String file) {
		System.out.println("in controller 2");
		videoService.startVideo(Paths.get(file));
		ModelAndView mv = new ModelAndView("hello-world");
		
		mv.addObject("fileList", fileList);
		return mv;
	} 
	
}
