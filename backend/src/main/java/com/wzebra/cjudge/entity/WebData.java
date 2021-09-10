package com.wzebra.cjudge.entity;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class WebData extends HashMap<String,Object> {

    public static WebData newInstance() {
        return new WebData();
    }

    public static WebData resultMessageInstance(Object result, String message) {
        return newInstance().put("result",result).put("message",message);
    }

    @NonNull
    public WebData put(String key, Object value) {
        super.put(key,value);
        return this;
    }

}
