package com.slee.web.config.exbuilder;

import com.cleopatra.XBInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Exbuilder6Listener {
	
    @Bean
    public ServletListenerRegistrationBean<XBInitializer> getServletListenerRegistrationBean() {
        ServletListenerRegistrationBean<XBInitializer> listener = new ServletListenerRegistrationBean<XBInitializer>(new XBInitializer());
        return listener;
    }
}