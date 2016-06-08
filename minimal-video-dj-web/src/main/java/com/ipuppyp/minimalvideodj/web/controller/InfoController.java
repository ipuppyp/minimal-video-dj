package com.ipuppyp.minimalvideodj.web.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.TEXT_PLAIN_VALUE)
public class InfoController {

	@RequestMapping("/get-info")
	public @ResponseBody String getInfo() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}

}
