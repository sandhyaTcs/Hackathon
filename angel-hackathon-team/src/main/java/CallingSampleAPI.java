
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class CallingSampleAPI {


	private String apikey = "4e18bf8d-36f2-4435-a05e-36a730dc7191";
	private String url = "https://api.idolondemand.com/1/api/async/recognizespeech/v1";
	private String job_url = "https://api.idolondemand.com//1/job/status/";
	public void post1() throws IOException{
		try {
			String filesrc="C:/Users/Public/Music/Sample Music/hpnext.mp4";
			File f = new File(filesrc);
			HttpResponse httpResponse = Unirest.post(url)
				  .field("apikey", apikey)
				  .field("file", f)
				  .asString();
			System.out.println(httpResponse.getBody());
			
			String jobId = (String)httpResponse.getBody();
			
			job_url = job_url+jobId;
			httpResponse = Unirest.post(job_url)
					  .field("apikey", apikey)
					  .asString();
			System.out.println(httpResponse.getBody());
		}catch(UnirestException ue){
			ue.printStackTrace();
		}
	}	
	public static void main(String[] args) {
		CallingSampleAPI cl1 = new CallingSampleAPI();
		try {
			cl1.post1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
