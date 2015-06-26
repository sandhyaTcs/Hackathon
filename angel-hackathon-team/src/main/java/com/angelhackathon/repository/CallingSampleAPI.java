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
import com.mashape.unirest.http.exceptions.UnirestException;

@Repository
public class CallingSampleAPI {


	private String apikey = "4e18bf8d-36f2-4435-a05e-36a730dc7191";
	private String url = "https://api.idolondemand.com/1/api/async/recognizespeech/v1";
	private String job_url = "https://api.idolondemand.com//1/job/status/";
	public String getAudioDetails() throws IOException{
		HttpResponse httpResponse = null;
		
		try {
			String filesrc = APIConstants.FILE_PATH;
			File file = new File(filesrc);
			httpResponse = Unirest.post(APIConstants.SPEECH_API_URL).field("apikey", APIConstants.API_KEY).field("file", file).asString();
			System.out.println(httpResponse.getBody());

			JSONObject jsonObject = new JSONObject(httpResponse.getBody().toString());
			String jobId = jsonObject.getString("jobID");
			
			String job_url = APIConstants.JOB_URL + jobId;
			httpResponse = Unirest.post(job_url)
					  .field("apikey", APIConstants.SPEECH_API_URL)
					  .asString();
			System.out.println(httpResponse.getBody());
		}catch(UnirestException ue){
			ue.printStackTrace();
		}
		return (String)httpResponse.getBody();
	}	
	
}
