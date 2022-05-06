package com.slee.web.util;

import com.slee.web.api.exception.ApiException;
import com.slee.web.api.exception.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtil {

    private final static String SEPARATOR = "#";

    public static boolean isAuthentication() {
        return SecurityUtil.getAuthentication() != null;
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getCurrentUserid() {
        Authentication authentication = getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new ApiException(ErrorCode.AUTHENTICATION_NOTEXIST_ERROR);
        }
        return ((User) authentication.getPrincipal()).getUsername();
    }

    public static String makeUserid(String userid, String bankCd) {
        return userid.concat(SEPARATOR).concat(bankCd);
    }

    public static String getUserid(String userid) {
        return userid.split(SEPARATOR)[0];
    }

    public static String getBankCd(String userid) {
        return userid.split(SEPARATOR)[1];
    }

    public static String getUseridByContext() {
        return getCurrentUserid().split(SEPARATOR)[0];
    }

    public static String getBankCdByContext() {
        return getCurrentUserid().split(SEPARATOR)[1];
    }
}
