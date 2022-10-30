package com.hgl.config;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        initializeLog4jConfig(servletContext);
    }

    private void initializeLog4jConfig(ServletContext container) {
        // Log4jConfigListener
        container.setInitParameter("log4jConfigLocation", "classpath:log4j.xml");
    }
}
