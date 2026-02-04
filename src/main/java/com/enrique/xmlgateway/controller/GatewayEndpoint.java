package com.enrique.xmlgateway.controller;


import com.enrique.xmlgateway.service.MainProcessorService;
import com.enrique.xmlservice.ObjectFactory;
import com.enrique.xmlservice.XmlRequest;
import com.enrique.xmlservice.XmlResponse;

import javax.xml.bind.JAXBElement;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.GregorianCalendar;

/**
 * SOAP endpoint for processing XML service requests.
 * Receives SOAP requests and delegates processing to MainProcessorService.
 */
@Endpoint
@RequiredArgsConstructor
public class GatewayEndpoint {

    /**
     * XML namespace for SOAP
     */
    private static final String NAMESPACE_URI = "http://example.com/xmlservice";

    /**
     * Factory for XML Elements
     */
    private final ObjectFactory objectFactory = new ObjectFactory();

    /**
     * Main service for processing business logic
     */
    private final MainProcessorService mainProcessorService;


    /**
     * Handles SOAP requests and returns XML responses.
     * Processes the request through the main service and formats the SOAP response.
     * @param request incoming SOAP request
     * @return SOAP response
     * @throws Exception if fails
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "doAction")
    @ResponsePayload
    public JAXBElement<XmlResponse> handleRequest(@RequestPayload XmlRequest request) throws Exception {


        XmlResponse response = mainProcessorService.processRequest(request);
        //TODO method to translate new codes to legacy/soap codes
        //TODO Soap interceptor to know exactly what is sent
        //Mock response
        response.setCode("0");
        response.setResult("Configuration OK");
        response.setDate(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
        response.setTime(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));

        return objectFactory.createDoActionResponse(response);
    }
}
