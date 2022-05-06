package com.slee.web.config.auth;

import com.slee.web.config.auth.basic.DefaultLoginAccessDeniedHandler;
import com.slee.web.config.auth.basic.DefaultLoginAuthenticationEntryPoint;
import com.slee.web.config.auth.basic.DefaultLoginProcessFilter;
import com.slee.web.config.auth.userdetails.BasicUserDetailService;
import com.slee.web.jpa.repository.auth.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberRepository memberRepository;
    private final DefaultLoginAuthenticationEntryPoint defaultLoginAuthenticationEntryPoint;
    private final DefaultLoginAccessDeniedHandler defaultLoginAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new BasicUserDetailService(memberRepository));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(SecurityConstants.SECURITY_IGNORE_URL);
    }

    public Filter buildDefaultLoginProcessFilter(AuthenticationManager authenticationManager) {
        return new DefaultLoginProcessFilter(authenticationManager);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/login/basic/**")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(defaultLoginAuthenticationEntryPoint)
                .accessDeniedHandler(defaultLoginAccessDeniedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(buildDefaultLoginProcessFilter(authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class);
    }
}