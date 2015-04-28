package com.ipuppyp.minimal_video_dj.service;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class VlcVideoServiceIT {

	private VlcVideoService underTest;

	@Before
	public void setUp() {
		underTest = new VlcVideoService(Arrays.asList("-L", "-f", "--no-video-title-show", "--one-instance", "--directx-volume=-1"));
	}

	@Test
	public void videoShouldStartAndExit() throws InterruptedException, URISyntaxException {
		URL fileURL = getClass().getResource("/video_samples/littlebird.mp4");
		Process p = underTest.startVideo(Paths.get(fileURL.toURI()));
		Thread.sleep(5000);
		p.destroy();
	}
}
