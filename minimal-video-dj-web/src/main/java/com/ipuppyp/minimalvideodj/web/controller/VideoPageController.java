package com.ipuppyp.minimalvideodj.web.controller;

import org.minimal.video.dj.facade.MinimalVideoDjFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VideoPageController {
	
	@Autowired
	private MinimalVideoDjFacade facade;
	
	@RequestMapping("/")
	public ModelAndView index(@RequestParam(value = "file", required = false) String file) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("fileList", facade.getVideoFileList());
		return mv;
	}
}
