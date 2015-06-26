package com.angelhackathon.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.angelhackathon.util.StaticConstants;

public class WebInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(Config.class);
        rootContext.setServletContext(servletContext);
        Dynamic servlet = servletContext.addServlet(StaticConstants.WEB_INITIALIZER_SERVLET, new DispatcherServlet(rootContext));
        servlet.addMapping(StaticConstants.WEB_INITIALIZER_SERVLET_MAPPING);
        servlet.setLoadOnStartup(1);
		
	}

}
