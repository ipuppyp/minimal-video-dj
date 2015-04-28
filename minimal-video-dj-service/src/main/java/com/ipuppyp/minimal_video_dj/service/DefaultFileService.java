package com.ipuppyp.minimal_video_dj.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.ipuppyp.minimal_video_dj.exception.CannotReadFolderException;

public class DefaultFileService implements FileService {
	private final Path path;
	private final String allowedTypes;
	
	/**
	 * 
	 * @param path
	 * @param allowedTypes example: <pre>^.*\\.(mp4|avi)$</pre>
	 */
	public DefaultFileService(Path path, String allowedTypes) {
		super();
		this.path = path;
		this.allowedTypes = allowedTypes;
	}

	@Override
	public Stream<Path> getVideoFileList() {
		try {
			return Files.list(path).filter(t -> t.toString().matches(allowedTypes));
		} catch (IOException e) {
			throw new CannotReadFolderException(e.getMessage(), e);
		}
	}

	
	

}
