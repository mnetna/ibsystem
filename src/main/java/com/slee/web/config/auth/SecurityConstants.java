package com.slee.web.config.auth;

public class SecurityConstants {

    public static final String[] SECURITY_IGNORE_URL = {
            "/"
            , "/h2-console/**"
            , "/actuator/refresh"
            , "/jwt/**"
            , "/ex/common/**"
            , "/ex/auth/**"
            , "/common/**"
            , "/auth/**"
            , "/test/**"
            , "/resource/**"
            , "/ui/**"
            , "/v2/api-docs"
            , "/webjars/**"
            , "/swagger/**"
            , "/swagger-resources/**"
            , "/swagger-ui.html"
            , "/favicon.ico"};
}
