package com.angelhackathon.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.angelhackathon.repository.CallingSampleAPI;

@Component
public class ProcessAudioServiceImpl implements ProcessAudioService {
	@Autowired
	private CallingSampleAPI callingSampleAPI;
	@Override
	public String ProcessAudioDetails() throws IOException {
		String convertAudioClip = callingSampleAPI.getAudioDetails();
		return null;
	}

}
