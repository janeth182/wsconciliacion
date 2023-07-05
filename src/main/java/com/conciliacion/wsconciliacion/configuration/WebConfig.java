package com.conciliacion.wsconciliacion.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

import com.conciliacion.wsconciliacion.middleware.TokenValidationFilter;


@Configuration
public class WebConfig {
	public FilterRegistrationBean<TokenValidationFilter> tokenValidationFilterRegistration() {
        FilterRegistrationBean<TokenValidationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenValidationFilter());          
        registrationBean.addUrlPatterns("/ws");
        return registrationBean;
    }
}