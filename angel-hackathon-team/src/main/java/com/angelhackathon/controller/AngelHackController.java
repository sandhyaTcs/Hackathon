package com.angelhackathon.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.angelhackathon.services.ClusterPointCRUD;
import com.angelhackathon.services.Detect;
import com.angelhackathon.services.ProcessAudioService;
import com.angelhackathon.services.SMSClient;
import com.angelhackathon.services.SampleRecorder;
import com.angelhackathon.services.Translate;

@Controller
@ComponentScan("com.angelhackathon.services")
public class AngelHackController {
	
	@Autowired
	private ProcessAudioService processAudioService;
	
	@Autowired
	private Detect detectLanguage;
	
	@Autowired
	private Translate translateLanguage;
	
	@Autowired
	private SampleRecorder sampleRecorder;
	 
	
	@RequestMapping(value= "/",method=RequestMethod.GET)
	public ModelAndView getIndexPage() {
		ModelAndView view = new ModelAndView("homePage");
		return view;
	}
	
	
	@RequestMapping(value= "recordd/home")
	public ModelAndView getHomePage() throws Exception {
		
		sampleRecorder.recordAudio();
		String selectedLanguage = "sp";
		String trainNo="12378";
		String convertedAudioClip = processAudioService.ProcessAudioDetails();
		String detectedLanguage = detectLanguage.detectLanguage(convertedAudioClip);
		String lang=translateLanguage.translateLanguage(convertedAudioClip, detectedLanguage, selectedLanguage);
		SMSClient client=new SMSClient(0);
		ClusterPointCRUD clusterPointCRUD = new ClusterPointCRUD();
		String phoneNo=clusterPointCRUD.lookup(trainNo);
		phoneNo="+91"+phoneNo;
		client.sendMessage(phoneNo,lang);
		ModelAndView view = new ModelAndView("homePage");
		return view;
	}
	
	@RequestMapping(value= "/newPassenger", method=RequestMethod.GET)
	public String addNewPassenger() throws Exception {
		
		return "newPassenger";
	}
	
	@RequestMapping(value = "Login/submitt", method = RequestMethod.POST)
	public String insertDbValue(@RequestParam("pnr")
    String pnr, @RequestParam("trainNo")
    String trainNo, @RequestParam("phoneNumber")
    String phoneNumber) {
		ClusterPointCRUD clusterPointCRUD = new ClusterPointCRUD();
		clusterPointCRUD.insertOrSve(pnr, trainNo, phoneNumber);
		return "homePage";
	}
	
	@RequestMapping(value = "/record")
	public String recordAudio() {
		return "FirstRecord";
	}	
}
