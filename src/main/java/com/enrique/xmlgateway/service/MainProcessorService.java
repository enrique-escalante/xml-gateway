package com.enrique.xmlgateway.service;

import com.enrique.xmlgateway.model.dto.RequestDTO;
import com.enrique.xmlgateway.model.dto.ResponseDTO;
import com.enrique.xmlservice.XmlRequest;
import com.enrique.xmlservice.XmlResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Main service orchestrator that processes incoming XML requests.
 * This service acts as the central coordinator between the SOAP endpoint
 * and the business operations, handling request validation, routing,
 * execution, and response preparation.
 */
@Service
@RequiredArgsConstructor
public class MainProcessorService {

    private static final Logger logger = LoggerFactory.getLogger(MainProcessorService.class);

    /**
     * Router service responsible for delegating requests to appropriate
     * business operation handlers based on request type or parameters.
     */
    private final ServiceRouter serviceRouter;

    /**
     * Processes an incoming XML request and returns the appropriate response.
     * This method serves as the main entry point for business logic execution,
     * coordinating the complete request lifecycle.
     *
     * @param xmlRequest The XML request object containing all input parameters
     * @return Processed response ready for SOAP serialization
     * @throws JsonProcessingException If JSON serialization/deserialization fails
     */
    public XmlResponse processRequest(XmlRequest xmlRequest) throws JsonProcessingException {
        logger.info("Executing the main service");
        RequestDTO requestDTO = extractAndValidateRequest(xmlRequest);
        XmlResponse xmlResponse = new XmlResponse();
        //TODO Loop FILE TO check if is a simulated service or not
        //TODO Loop FILE to check if the workflow goes to the new restful services or to an old client.(only usefull to open and close services during the migration)
        //TODO That FILE could be in DB, MACHINE or VM.
        if(requestDTO!= null) {
            ResponseDTO responseDTO = serviceRouter.router(requestDTO);
            xmlResponse.setCode(responseDTO.getStatusCode());//TODO conversion codes from rest to soap
            xmlResponse.setResult(responseDTO.getMessage());
            return xmlResponse;
        }else{
            xmlResponse.setCode("-1");//TODO conversion codes from rest to soap
            xmlResponse.setResult("xml request without mandatory params");
            return xmlResponse;
        }
    }

    /**
     * Method to convert and XML to a DTO
     * @param xmlRequest provided by the user
     * @return an DTO with the equivalent parameters.
     */
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
