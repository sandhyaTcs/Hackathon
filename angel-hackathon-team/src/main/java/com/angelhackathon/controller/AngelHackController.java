package com.angelhackathon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AngelHackController {
	
	@RequestMapping(value= "/",method=RequestMethod.GET)
	public ModelAndView getIndexPage() {
		ModelAndView view = new ModelAndView("homePage");
		return view;
	}
	
	
	@RequestMapping(value= "/home")
	public ModelAndView getHomePage() {
		ModelAndView view = new ModelAndView("homePage");
		return view;
	}
	
	
}
