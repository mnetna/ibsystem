package com.slee.web.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<ExbuilderFilter> exbuilderFilter() {
        FilterRegistrationBean<ExbuilderFilter> bean = new FilterRegistrationBean<ExbuilderFilter>();
        bean.setFilter(new ExbuilderFilter());
        bean.addUrlPatterns("/ex/*");
        return bean;
    }
}
