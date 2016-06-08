package org.minimal.video.dj.facade;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.minimal.video.dj.domain.SimpleMessageResponse;
import org.minimal.video.dj.domain.VideoFileListResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.ipuppyp.minimalvideodj.service.tutorial.Adjustment;

public class MinimalVideoDjRestFacade implements MinimalVideoDjFacade {
	private final String getVideoFileListUrl;
	private final String startVideoUrl;
	private final String stopVideoUrl;
	private final String adjustUrl;

	public MinimalVideoDjRestFacade(String getVideoFileListUrl, String startVideoUrl, String stopVideoUrl, String adjustUrl) {
		super();
		this.getVideoFileListUrl = getVideoFileListUrl;
		this.startVideoUrl = startVideoUrl;
		this.stopVideoUrl = stopVideoUrl;
		this.adjustUrl = adjustUrl;
	}

	public VideoFileListResponse getVideoFileList() {
		return callRestApi(getVideoFileListUrl, VideoFileListResponse.class);
	}

	public SimpleMessageResponse startVideo(String file) {
		return callRestApi(startVideoUrl + "?file=" + file, SimpleMessageResponse.class);
	}

	@Override
	public SimpleMessageResponse stopVideo() {
		return callRestApi(stopVideoUrl, SimpleMessageResponse.class);
	}

	@Override
	public SimpleMessageResponse adjust(Adjustment adjustment) {
		return callRestApi(adjustUrl + "?adjustment=" + adjustment, SimpleMessageResponse.class);
	}


	private <T> T callRestApi(String url, Class<T> resultType) {
		try {
			Gson gson = new Gson();
			return gson.fromJson(downloadJson(url), resultType);
		} catch (JsonSyntaxException | IOException e) {
			throw new RestException(e);
		}
	}

	private JsonObject downloadJson(String urlParameter) throws MalformedURLException, IOException {
		return new JsonParser().parse(new InputStreamReader(new URL(urlParameter).openStream())).getAsJsonObject();
	}




}
