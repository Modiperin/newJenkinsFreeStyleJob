package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class UserFilter implements Filter {

	@Override
	public void doFilter(ServletRequest reqX, ServletResponse respX, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)reqX;
		
		String url=request.getRequestURI().toString();
		System.out.println(url);
		
	}

}
