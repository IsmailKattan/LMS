package com.maids.LMS.exception.Custom;

import com.maids.LMS.enums.ErrorCode;
import lombok.Getter;

import java.util.Map;

@Getter
public class ExternalServiceException extends BaseException {

    private final String serviceName;

    public ExternalServiceException(String serviceName, String massage) {
        super(
                ErrorCode.EXTERNAL_SERVICE_ERROR,
                massage != null ? massage : "External service error",
                Map.of("serviceName", serviceName)
        );
        this.serviceName = serviceName;
    }
}
