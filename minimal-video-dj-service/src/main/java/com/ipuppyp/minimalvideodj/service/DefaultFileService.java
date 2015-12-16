package com.ipuppyp.minimalvideodj.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipuppyp.minimalvideodj.service.exception.CannotReadFolderException;

public class DefaultFileService implements FileService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFileService.class);
	private final Path path;
	private final static String ALLOWED_TYPES = "^.*\\.(mp4|avi)$";

	public DefaultFileService(Path path) {
		super();
		this.path = path;
		LOGGER.info("Video path is set to {}", path);
	}

	@Override
	public List<String> getVideoFileList() {
		try {
			List<String> videoFileList = new ArrayList<>();
			Files.list(path)
				.filter(t -> t.toString().matches(ALLOWED_TYPES))
					.forEach(filePath -> videoFileList.add(filePath.getFileName().toString()));
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
