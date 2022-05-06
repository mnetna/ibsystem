package com.slee.web.config.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class ExbuilderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ExbuilderRequestWrapper wrapper = new ExbuilderRequestWrapper((HttpServletRequest) request);
        String requestURI = wrapper.getRequestURI();
        if(requestURI.startsWith("/ex")) {
            request.getRequestDispatcher(requestURI.replace("/ex", "")).forward(wrapper, response);
        } else {
            chain.doFilter(wrapper, response);
        }
    }
}
