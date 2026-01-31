package com.enrique.xmlgateway.model.dto;

import com.enrique.xmlservice.Params;
import com.enrique.xmlservice.XmlRequest;
import lombok.Getter;
import lombok.Setter;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RequestDTO {

    private String user;
    private String type;
    private String action;
    private String name;
    private Map<String, String> parameters;
    private XMLGregorianCalendar requestTime;

    public RequestDTO(XmlRequest xmlRequest) {
        this.user = xmlRequest.getUser();
        this.type = xmlRequest.getType();
        this.action = xmlRequest.getAction();
        this.name = xmlRequest.getName();
        this.parameters = extractParameters(xmlRequest);
        this.requestTime = getCurrentTimestamp();
    }
    private Map<String, String> extractParameters(XmlRequest request) {
        Map<String, String> params = new HashMap<>();

        if (request.getInputParams() != null &&
                request.getInputParams().getParam() != null) {

            for (Params.Param param : request.getInputParams().getParam()) {
                params.put(param.getName(), param.getValue());
            }
        }
        return params;
    }

    private XMLGregorianCalendar getCurrentTimestamp() {
        try {
            return DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(new GregorianCalendar());
        } catch (Exception e) {
            return null;
        }
    }
}
