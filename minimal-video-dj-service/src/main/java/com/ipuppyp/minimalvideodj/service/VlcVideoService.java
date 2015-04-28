package com.ipuppyp.minimalvideodj.service;

import static java.io.File.separator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipuppyp.minimalvideodj.service.exception.CannotStartVideoException;
import com.ipuppyp.minimalvideodj.service.exception.VideoPlayerNotInstalledException;

public class VlcVideoService implements VideoService {
	Logger LOGGER = LoggerFactory.getLogger(VlcVideoService.class);
	
	private final String vlcPath;
	private final List<String> vlcOptions;

	/**
	 * 
	 * @param vlcOptions
	 *            example:
	 * 
	 *            <pre>
	 * Arrays.asList(&quot;-L&quot;, &quot;-f&quot;, &quot;--no-video-title-show&quot;, &quot;--one-instance&quot;, &quot;--directx-volume=-1&quot;);
	 * </pre>
	 */
	public VlcVideoService(List<String> vlcOptions) {
		this.vlcPath = guessVlcPath();
		this.vlcOptions = vlcOptions;
	}

	@Override
	public Process startVideo(Path fileName) {
		List<String> command = new ArrayList<>();
		command.add(vlcPath);
		command.addAll(vlcOptions);
		command.add(fileName.toString());
		ProcessBuilder pb = new ProcessBuilder(command);
		try {
			LOGGER.debug("Starting video...");
			Process process = pb.start();
			LOGGER.debug("Video player started succesfully");
			return process;
		} catch (IOException e) {
			throw new CannotStartVideoException(e.getMessage(), e);
		}

	}

	@Override
	public void destroyVideo(Process process) {
		LOGGER.debug("Destroying video...");
		process.destroy();
		LOGGER.debug("Video player destroyed succesfully");
	}

	private String guessVlcPath() {
		final String programFilesPath = System.getenv("ProgramFiles");
		final String vlcRelPath = "VideoLAN" + separator + "VLC" + separator + "vlc.exe";

		final String pathInProgramFiles = programFilesPath + separator + vlcRelPath;
		final String pathInProgramFiles32 = System.getenv("ProgramFiles") + " (x86)" + separator + vlcRelPath;

		String path;
		
		if (Files.exists(Paths.get(pathInProgramFiles))) {
			path = pathInProgramFiles;
		} else if (Files.exists(Paths.get(pathInProgramFiles32))) {
			path = pathInProgramFiles32;
		} else {
			throw new VideoPlayerNotInstalledException("vlc.exe not found in" + pathInProgramFiles + " and " + pathInProgramFiles + ", please install it");
		}
		LOGGER.info("Guessed VLC path: {}", path);
		return path;
	}

}
