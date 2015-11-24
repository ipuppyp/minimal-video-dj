package com.ipuppyp.minimalvideodj.service;

import java.nio.file.Path;
import java.util.List;

public interface FileService {

	List<Path> getVideoFileList();
	
	String getVideoFolderName();
}
