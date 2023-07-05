package com.conciliacion.wsconciliacion.middleware;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conciliacion.wsconciliacion.service.CognitoService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class TokenValidationFilter implements Filter {
	@Autowired
	public CognitoService service;
	
	  @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;

	        String header = httpRequest.getHeader("Authorization");		        
	        if (header != null && isValidToken(header)) {
	            chain.doFilter(request, response);
	        } else {
	            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        }
	  }
	  
	  private boolean isValidToken(String header) {	
	        String[] partsHeader = header.split(" ");
	        if(partsHeader.length == 2) {
	        	   String accessToken = partsHeader[1];
	       	    return service.verifyAccessToken(accessToken); 
	        }
	     return false;
	 }
}
