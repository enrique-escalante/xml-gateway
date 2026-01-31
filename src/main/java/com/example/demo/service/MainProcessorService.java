package com.example.demo.service;

import com.example.demo.entities.dto.RequestDTO;
import com.example.demo.entities.dto.ResponseDTO;
import com.example.xmlservice.XmlRequest;
import com.example.xmlservice.XmlResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainProcessorService {

    private final ServiceRouter serviceRouter;

    public XmlResponse processRequest(XmlRequest xmlRequest) throws JsonProcessingException {

        RequestDTO requestDTO = extractAndValidateRequest(xmlRequest);
        XmlResponse xmlResponse = new XmlResponse();
        //TODO Loop FILE TO KNOW if is a simulated service or not

        //TODO Loop FILE to know if the workflow goes to the new restful services or old client.
        if(requestDTO!= null) {
            ResponseDTO responseDTO = serviceRouter.router(requestDTO);
            return xmlResponse;
        }else{
            return xmlResponse;
        }
    }

    private RequestDTO extractAndValidateRequest(XmlRequest xmlRequest) {
        try {
            if (xmlRequest.getUser() == null || xmlRequest.getUser().trim().isEmpty()) {
                return null;
            }

            if (xmlRequest.getType() == null || xmlRequest.getType().trim().isEmpty()) {
                return null;
            }

            if (xmlRequest.getAction() == null || xmlRequest.getAction().trim().isEmpty()) {
                return null;
            }

            return new RequestDTO(xmlRequest);

        } catch (Exception e) {
            //todo Loggers
            return null;
        }
    }
}
