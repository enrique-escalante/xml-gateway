package com.enrique.xmlgateway.operations;

import com.enrique.xmlgateway.model.dto.RequestDTO;
import com.enrique.xmlgateway.model.dto.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TypeService {

    ResponseDTO execute(RequestDTO requestDTO) throws JsonProcessingException;
}
