package com.ipuppyp.minimal_video_dj.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FileService {

	Stream<Path> getVideoFileList();
}
