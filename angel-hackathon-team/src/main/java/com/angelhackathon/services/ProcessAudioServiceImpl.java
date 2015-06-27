package com.angelhackathon.services;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.angelhackathon.domain.SpeechAPI;
import com.angelhackathon.repository.CallingSampleAPI;


@Component
public class ProcessAudioServiceImpl implements ProcessAudioService {
	@Autowired
	private CallingSampleAPI callingSampleAPI;
	@Override
	public String ProcessAudioDetails() throws IOException {
		int i = 0;
		JSONObject jsonObject = null;
		String convertAudioClip = null;
		SpeechAPI speechApi = new SpeechAPI();
		JSONArray jsonArray = null;
		while (i<5) {
			if (null != jsonObject && jsonObject.has("result")) {
				convertAudioClip = jsonObject.get("result").toString();
				
				//convertAudioClip = jsonArray.getJSONObject(0).getString("result");
				jsonObject = new JSONObject(convertAudioClip);
				jsonArray = (JSONArray) jsonObject.get("document");
				
				jsonObject = new JSONObject(jsonArray.get(0).toString());
				convertAudioClip = jsonObject.getString("content");
				break;
			} else {
				convertAudioClip = callingSampleAPI.getAudioDetails(speechApi);
				jsonObject = new JSONObject(convertAudioClip);
				jsonArray = (JSONArray) jsonObject.get("actions");
				jsonObject = new JSONObject(jsonArray.get(0).toString());
				try {
					java.lang.Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
		}
		
		return convertAudioClip;
	}

}
