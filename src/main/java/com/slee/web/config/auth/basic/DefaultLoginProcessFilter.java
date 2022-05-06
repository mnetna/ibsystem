package com.slee.web.config.auth.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slee.web.common.model.auth.LoginRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class DefaultLoginProcessFilter extends BasicAuthenticationFilter {

    public DefaultLoginProcessFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        LoginRequestDto loginRequestDto = new ObjectMapper().readValue(request.getReader(), LoginRequestDto.class);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = loginRequestDto.toAuthentication();
        Authentication authentication = getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        super.doFilterInternal(request, response, chain);
    }
}
