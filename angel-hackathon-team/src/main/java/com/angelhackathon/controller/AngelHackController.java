package com.angelhackathon.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.angelhackathon.services.Detect;
import com.angelhackathon.services.ProcessAudioService;
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
	
	@RequestMapping(value= "/",method=RequestMethod.GET)
	public ModelAndView getIndexPage() {
		ModelAndView view = new ModelAndView("homePage");
		return view;
	}
	
	
	@RequestMapping(value= "/home")
	public ModelAndView getHomePage() throws Exception {
		String selectedLanguage = "sp";
		String convertedAudioClip = processAudioService.ProcessAudioDetails();
		String detectedLanguage = detectLanguage.detectLanguage(convertedAudioClip);
		translateLanguage.translateLanguage(convertedAudioClip, detectedLanguage, selectedLanguage);
		ModelAndView view = new ModelAndView("homePage");
		return view;
	}
	
	
}
