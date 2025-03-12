package com.maids.LMS.exception.Custom;

import com.maids.LMS.enums.ErrorCode;
import lombok.Getter;

import java.util.Map;

@Getter
public class RateLimitExceededException extends BaseException{
    public RateLimitExceededException(String errorDescription, Map<String, Object> additionalDetails) {
        super(
                ErrorCode.RATE_LIMIT_EXCEEDED,
                errorDescription,
                additionalDetails
        );
    }
}
