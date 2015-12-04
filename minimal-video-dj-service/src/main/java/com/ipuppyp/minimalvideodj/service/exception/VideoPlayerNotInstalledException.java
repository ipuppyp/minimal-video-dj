package com.ipuppyp.minimalvideodj.service.exception;

public class VideoPlayerNotInstalledException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VideoPlayerNotInstalledException(String msg) {
		super(msg);
	}

}
