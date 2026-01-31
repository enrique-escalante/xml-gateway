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


@Endpoint
@RequiredArgsConstructor
public class GatewayEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/xmlservice";
    private final ObjectFactory objectFactory = new ObjectFactory();

    private final MainProcessorService mainProcessorService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "doAction")
    @ResponsePayload
    public JAXBElement<XmlResponse> handleRequest(@RequestPayload XmlRequest request) throws Exception {

        System.out.println("User: " + request.getUser());
        System.out.println("Action: " + request.getAction());

        XmlResponse response = mainProcessorService.processRequest(request);
        //TODO method to translate new codes to legacy/soap codes
        response.setCode("0");
        response.setResult("Configuration OK");
        response.setDate(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
        response.setTime(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));

        return objectFactory.createDoActionResponse(response);
    }
}
