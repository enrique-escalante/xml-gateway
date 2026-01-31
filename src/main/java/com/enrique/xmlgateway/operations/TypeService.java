package com.enrique.xmlgateway.operations;

import com.enrique.xmlgateway.model.dto.RequestDTO;
import com.enrique.xmlgateway.model.dto.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;


/**
 * Service contract for processing business operations.
 * All service implementations must adhere to this interface.
 *
 * <p>Implementations handle specific business domains (accounts, cards, payments, etc.)
 * and are responsible for executing operations with proper error handling
 * and response formatting.
 */
public interface TypeService {

    /**
     * Executes the business operation with the given request.
     *
     * @param requestDTO Request containing operation type and parameters
     * @return Response with operation results and status
     * @throws JsonProcessingException If JSON serialization/deserialization fails
     */
    ResponseDTO execute(RequestDTO requestDTO) throws JsonProcessingException;
}
