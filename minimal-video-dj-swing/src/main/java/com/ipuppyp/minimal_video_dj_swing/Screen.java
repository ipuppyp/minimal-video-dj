package com.ipuppyp.minimal_video_dj_swing;

import com.ipuppyp.minimal_video_dj.service.FileService;
import com.ipuppyp.minimal_video_dj.service.VideoService;

/**
 * Hello world!
 *
 */
public class Screen 
{
	private final VideoService videoService;
	private final FileService fileService;
	
	
    public Screen(VideoService videoService, FileService fileService) {
		super();
		this.videoService = videoService;
		this.fileService = fileService;
	}


    
	public void run(  )
    {
		System.out.println( "Hello World!" );
		fileService.getVideoFileList().forEach(e -> System.out.println(e));
    }
}
