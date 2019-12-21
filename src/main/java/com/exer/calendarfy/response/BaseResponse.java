package com.exer.calendarfy.response;

import java.util.HashMap;

public interface BaseResponse {
    void setResponseHeaders(HashMap<String, String> headers);
    void addResponseHeader(String key, String value);
    void setIsSuccessful(Boolean isSuccessful);
    HashMap<String, String> getResponse();
}
