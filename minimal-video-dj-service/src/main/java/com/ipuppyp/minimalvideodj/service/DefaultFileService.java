package com.ipuppyp.minimalvideodj.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.ipuppyp.minimalvideodj.service.exception.CannotReadFolderException;

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
	public List<Path> getVideoFileList() {
		try {
			List<Path> videoFileList = new ArrayList<>();
			Files.list(path).filter(t -> t.toString().matches(allowedTypes)).forEach(videoFileList::add);
			return videoFileList;
			
		} catch (IOException e) {
			throw new CannotReadFolderException(e.getMessage(), e);
		}
	}

	@Override
	public String getVideoFolderName() {
		return path.toString();
	}

	
	

}
