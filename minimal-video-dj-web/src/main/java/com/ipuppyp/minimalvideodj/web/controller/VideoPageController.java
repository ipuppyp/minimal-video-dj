package com.ipuppyp.minimalvideodj.web.controller;

import static com.google.common.io.Files.getNameWithoutExtension;
import static java.io.File.pathSeparator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
			videoService.startVideo(Paths.get(fileService.getVideoFolderName() + "/" + file + ".mp4"));
		}
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("fileList", getVideoFilenameList());
		return mv;
	}

	private List<String> getVideoFilenameList() {
		List<String> result = new ArrayList<>();
		for (Path path : fileService.getVideoFileList()) {
			result.add(getNameWithoutExtension(path.toString()).toUpperCase());
		}
		return result;
	}
}
