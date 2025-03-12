package com.maids.LMS.util;


import lombok.Builder;

import java.util.Map;


@Builder
public record ErrorResponse(String message, int httpcode, String httpstatus, String timestamp, String path,
                            String method, Map<String, Object> additionalDetails) {
    // toString method
    @Override
    public String toString() {
        // return in json format
        return "{" +
                "\"message\":\"" + message + '\"' +
                ", \"httpcode\":" + httpcode +
                ", \"httpstatus\":\"" + httpstatus + '\"' +
                ", \"timestamp\":\"" + timestamp + '\"' +
                ", \"path\":\"" + path + '\"' +
                ", \"method\":\"" + method + '\"' +
                ", \"additionalDetails\":" + additionalDetails +
                '}';
    }
}
