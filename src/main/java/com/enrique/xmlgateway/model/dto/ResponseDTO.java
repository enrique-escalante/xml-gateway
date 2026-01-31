package com.enrique.xmlgateway.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class ResponseDTO {

    private String statusCode;
    private String message;
    private Map<String, String> outputParameters;

    public ResponseDTO(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.outputParameters = new HashMap<>();
    }

    public ResponseDTO(String statusCode, String message,
                            Map<String, String> outputParameters) {
        this.statusCode = statusCode;
        this.message = message;
        this.outputParameters = outputParameters != null ? outputParameters : new HashMap<>();
    }
}
