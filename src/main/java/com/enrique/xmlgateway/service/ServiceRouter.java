package com.enrique.xmlgateway.service;

import com.enrique.xmlgateway.model.dto.RequestDTO;
import com.enrique.xmlgateway.model.dto.ResponseDTO;
import com.enrique.xmlgateway.operations.TypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service router that dynamically routes requests to appropriate business service implementations.
 * <p>
 * Acts as a dispatcher that maps request types to corresponding {@link TypeService} implementations
 * using a registry pattern. This enables loose coupling between request handlers and business logic,
 * allowing easy addition of new service types without modifying the routing logic.
 * <p>
 * All available services are automatically injected as a map where keys are service type identifiers
 * (typically lowercase service names) and values are corresponding {@link TypeService} implementations.
 */
@Service
@RequiredArgsConstructor
public class ServiceRouter {

    /**
     * Registry of available service implementations keyed by service type identifier.
     * <p>
     * The map is automatically populated by Spring, where keys are typically the lowercase
     * service names (e.g., "account", "card", "payment") and values are corresponding
     * {@link TypeService} implementations annotated with {@link org.springframework.stereotype.Service}.
     * <p>
     * Example: {@code "account" -> AccountService, "card" -> CardService}
     */
    private final Map<String, TypeService> services;


    /**
     * Routes request to appropriate service based on request type.
     * Service lookup is case-insensitive.
     *
     * @param requestDTO Request containing service type and parameters
     * @return Response from the executed service
     * @throws JsonProcessingException If service execution fails during JSON processing
     * @throws IllegalArgumentException If no service found for the given type
     */
    public ResponseDTO router(RequestDTO requestDTO) throws JsonProcessingException {
        TypeService typeService = services.get(requestDTO.getType().toLowerCase());

        if(typeService == null) throw new IllegalArgumentException("Service not found: "+requestDTO.getType());

        return typeService.execute(requestDTO);
    }

}
