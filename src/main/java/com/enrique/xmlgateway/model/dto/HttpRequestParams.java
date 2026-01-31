package com.enrique.xmlgateway.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpRequestParams {
    /**
     * Sistem/Endpoint
     */
    private String sistema = "";

    /**
     * Parámeters of the uri
     */
    private String uriParams = "";

    /**
     * Method HTTP
     */
    private HttpMethod requestMethod = HttpMethod.GET;

    /**
     * Headers
     */
    private HashMap<String,String> headers = new HashMap<>();

    /**
     * body
     */
    private String postData = "";

    /**
     * Class to map the response,unused
     */
    private Class<? extends Object> mappedClass = String.class;

}