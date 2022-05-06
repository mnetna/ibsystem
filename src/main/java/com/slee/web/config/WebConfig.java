package com.slee.web.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = { "com.hanafn.web.controller" })
public class WebConfig implements WebMvcConfigurer, WebMvcRegistrations {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resource/**/*").addResourceLocations("classpath:/static/resource/");
		registry.addResourceHandler("/ui/data/**/*").addResourceLocations("classpath:/static/ui/data/");
		registry.addResourceHandler("/ui/app/**/*.js").addResourceLocations("classpath:/static/ui/app/");
		registry.addResourceHandler("/ui/cpr-lib/**/*").addResourceLocations("classpath:/static/ui/cpr-lib/");
		registry.addResourceHandler("/ui/theme/**/*").addResourceLocations("classpath:/static/ui/theme/");
		registry.addResourceHandler("/ui/theme-Hyper/**/*").addResourceLocations("classpath:/static/ui/theme-Hyper/");
		registry.addResourceHandler("/ui/lib/**/*").addResourceLocations("classpath:/static/ui/lib/");
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/favicon.ico");
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer
		.favorParameter(false)
		.ignoreAcceptHeader(true)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML)
		.mediaType("json", MediaType.APPLICATION_JSON);
	}
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("/ui/app/index/index");
    }
    
    @Bean
    public ViewResolver viewResolver() {
       InternalResourceViewResolver bean = new InternalResourceViewResolver();
//       bean.setPrefix("/ui/app/index/");
       bean.setSuffix(".clx");
       bean.setOrder(2);
       return bean;
    }
}
