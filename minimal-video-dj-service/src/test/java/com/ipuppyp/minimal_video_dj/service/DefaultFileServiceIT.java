package com.ipuppyp.minimal_video_dj.service;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class DefaultFileServiceIT {

	private DefaultFileService underTest;
	
	@Before
	public void setUp() throws URISyntaxException {
		
		URL fileURL = getClass().getResource("/video_samples");
		String allowedTypes = "^.*\\.(mp4|avi)$";
		underTest = new DefaultFileService(Paths.get(fileURL.toURI()), allowedTypes);
	}
	
	@Test
	public void filteredFileListShouldBeResturnedFromTheFolder() {
		underTest.getVideoFileList().forEach(e -> System.out.println(e.toString()));
	}
}
