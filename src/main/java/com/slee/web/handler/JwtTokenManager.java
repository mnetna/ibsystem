package com.slee.web.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JwtTokenManager {

    private static Map<String, JwtTokenInfo> tokenMap = new ConcurrentHashMap<>();

    public static Map<String, JwtTokenInfo> getTokenMap() {
        return tokenMap;
    }

    public static void setTokenMap(Map<String, JwtTokenInfo> tokenMap) {
        JwtTokenManager.tokenMap = tokenMap;
    }

    public static JwtTokenInfo getToken(String key) {
        return tokenMap.get(key);
    }

    public static void setToken(String key, JwtTokenInfo token) {
        tokenMap.put(key, token);
    }

    public static boolean hasToken(String key) {
        return tokenMap.get(key) != null;
    }

}
