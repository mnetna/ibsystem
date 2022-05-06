package com.slee.web.config.auth.jwt;

import com.slee.web.constant.TokenConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final JwtTokenProvider tokenProvider;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        super(authenticationManager);
        this.tokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (shouldExclude(request))  {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = resolveToken(request);

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }


    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TokenConstants.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TokenConstants.BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean shouldExclude(HttpServletRequest request) {
        return (request.getRequestURI().endsWith(".css") ||
                request.getRequestURI().endsWith(".js") ||
                request.getRequestURI().endsWith(".map") ||
                request.getRequestURI().endsWith(".woff2") ||
                request.getRequestURI().endsWith(".png") ||
                request.getRequestURI().endsWith(".clx") ||
                request.getRequestURI().endsWith(".svg"));
    }
}
