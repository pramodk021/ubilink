package com.ubilink.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class WebServletConfiguration implements WebApplicationInitializer {
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String DISPATCHER_SERVLET_MAPPING = "/*";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SpringConfig.class);

        //XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
        //rootContext.setConfigLocation("classpath:exampleApplicationContext.xml");

        servletContext.addListener(new ContextLoaderListener(rootContext));
       // servletContext.addFilter("SimpleCORSFilter", new SimpleCORSFilter());
        rootContext.setServletContext(servletContext);
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(rootContext));
        dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);
        dispatcher.setLoadOnStartup(1);
        
    }
}
