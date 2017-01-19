package com.ipuppyp.minimalvideodj.web.controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.StringJoiner;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.TEXT_PLAIN_VALUE)
@CrossOrigin("*")
public class InfoController {

	@RequestMapping("/get-info")
	public @ResponseBody String getInfo() throws SocketException {
		StringJoiner joiner = new StringJoiner("; ");
		for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
			NetworkInterface intf = en.nextElement();
			for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
				joiner.add(
							enumIpAddr.nextElement().getHostAddress());
			}
		}

		return joiner.toString();
	}

}
