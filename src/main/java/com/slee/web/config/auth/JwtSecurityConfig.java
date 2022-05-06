package com.slee.web.config.auth;

import com.slee.web.config.auth.jwt.JwtAccessDeniedHandler;
import com.slee.web.config.auth.jwt.JwtAuthenticationEntryPoint;
import com.slee.web.config.auth.jwt.JwtAuthenticationFilter;
import com.slee.web.config.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Order(10)
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        return new JwtAuthenticationFilter(authenticationManager, jwtTokenProvider);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(SecurityConstants.SECURITY_IGNORE_URL);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter(authenticationManagerBean(), jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

}