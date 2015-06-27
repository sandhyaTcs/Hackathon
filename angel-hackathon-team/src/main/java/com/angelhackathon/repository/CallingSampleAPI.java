package com.angelhackathon.repository;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.angelhackathon.util.APIConstants;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.angelhackathon.domain.SpeechAPI;
import com.mashape.unirest.http.exceptions.UnirestException;

@Repository
public class CallingSampleAPI {

	public String getAudioDetails(SpeechAPI speechApi) throws IOException{
		HttpResponse httpResponse = null;
		
		try {
			
			String jobId =null;
			if (speechApi.getJobId() == null) {
			
			String filesrc = APIConstants.FILE_PATH;
			
			File file = new File(filesrc);
			httpResponse = Unirest.post(APIConstants.SPEECH_API_URL).field("apikey", APIConstants.API_KEY).field("file", file).asString();
			System.out.println(httpResponse.getBody());
			JSONObject jsonObject = new JSONObject(httpResponse.getBody().toString());
			jobId = jsonObject.getString("jobID");
			speechApi.setJobId(jobId);
			}
			else {
				jobId = speechApi.getJobId();
			}
			String job_url = APIConstants.JOB_URL + jobId;
			httpResponse = Unirest.post(job_url)
					  .field("apikey", APIConstants.API_KEY)
					  .asString();
			System.out.println(httpResponse.getBody());

			
		}catch(UnirestException ue){
			ue.printStackTrace();
		}
		return (String)httpResponse.getBody();
	}	
	
}
