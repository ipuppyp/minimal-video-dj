package com.ipuppyp.minimalvideodj.service;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.ipuppyp.minimalvideodj.service.DefaultFileService;

public class DefaultFileServiceIT {

	private DefaultFileService underTest;
	
	@Before
	public void setUp() throws URISyntaxException {
		
		URL fileURL = getClass().getResource("/video_samples");
		underTest = new DefaultFileService(Paths.get(fileURL.toURI()));
	}
	
	@Test
	public void filteredFileListShouldBeResturnedFromTheFolder() {
		underTest.getVideoFileList().forEach(e -> System.out.println(e.toString()));
	}
}
