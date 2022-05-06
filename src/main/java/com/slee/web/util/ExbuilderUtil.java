package com.slee.web.util;

import java.util.HashMap;
import java.util.Map;

public class ExbuilderUtil {

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getInputFromRequestMap(Map<String, Object> param) {
        if (param == null || param.isEmpty()) {
            return new HashMap<>();
        }
        return (Map<String, Object>)((Map<String, Object>) param.get("data")).get("input");
    }
}
