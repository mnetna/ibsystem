package com.slee.web.config.auth;

import com.slee.web.config.auth.pin.PinLoginAccessDeniedHandler;
import com.slee.web.config.auth.pin.PinLoginAuthenticationEntryPoint;
import com.slee.web.config.auth.pin.PinLoginProcessFilter;
import com.slee.web.config.auth.userdetails.PinUserDetailService;
import com.slee.web.jpa.repository.auth.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Order(1)
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class PinAuthSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberRepository memberRepository;
    private final PinLoginAuthenticationEntryPoint pinLoginAuthenticationEntryPoint;
    private final PinLoginAccessDeniedHandler pinLoginAccessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new PinUserDetailService(memberRepository));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(SecurityConstants.SECURITY_IGNORE_URL);
    }

    public Filter buildPinLoginProcessFilter(AuthenticationManager authenticationManager) {
        return new PinLoginProcessFilter(authenticationManager);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/login/pin/**")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(pinLoginAuthenticationEntryPoint)
                .accessDeniedHandler(pinLoginAccessDeniedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(buildPinLoginProcessFilter(authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class);
    }
}