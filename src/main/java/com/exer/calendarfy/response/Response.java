package com.exer.calendarfy.response;

import java.util.HashMap;

public class Response implements BaseResponse {

    private HashMap<String, String> headers = new HashMap<>();
    private Boolean isSuccessful;

    @Override
    public void setResponseHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public void addResponseHeader(String key, String value) {
        headers.put(key, value);
    }

    @Override
    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    @Override
    public HashMap<String, String> getResponse() {
        HashMap<String, String> response = new HashMap<>();

        response.put("success", String.valueOf(isSuccessful));
        response.putAll(headers);

        return response;
    }
}
