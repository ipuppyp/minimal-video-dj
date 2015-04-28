package com.ipuppyp.minimal_video_dj.service;

import static java.io.File.separator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.ipuppyp.minimal_video_dj.exception.CannotStartVideoException;
import com.ipuppyp.minimal_video_dj.exception.VideoPlayerNotInstalledException;

public class VlcVideoService implements VideoService {

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
			return pb.start();
		} catch (IOException e) {
			throw new CannotStartVideoException(e.getMessage(), e);
		}

	}

	@Override
	public void destroyVideo(Process process) {
		process.destroy();

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
		return path;
	}

}
