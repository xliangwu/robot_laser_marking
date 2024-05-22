package com.caveup.lasr.util;


import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xueliang.wu
 */
public final class QueryUtil {

    public static Map<String, String> parse(String query) {
        Map<String, String> map = new HashMap<>();
        try {
            final String charset = "utf-8";
            String decodeQuery = URLDecoder.decode(query, charset);
            String[] keyValues = decodeQuery.split("&");
            for (int i = 0; i < keyValues.length; i++) {
                String key = keyValues[i].substring(0, keyValues[i].indexOf("="));
                String value = keyValues[i].substring(keyValues[i].indexOf("=") + 1);
                map.put(key, value);
            }
        } catch (Exception e) {
        }
        return map;
    }
}
