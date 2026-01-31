package com.example.demo.service;

import com.example.demo.entities.dto.RequestDTO;
import com.example.demo.entities.dto.ResponseDTO;
import com.example.demo.operations.TypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServiceRouter {

    private final Map<String, TypeService> services;

    public ResponseDTO router(RequestDTO requestDTO) throws JsonProcessingException {
        TypeService typeService = services.get(requestDTO.getType().toLowerCase());

        if(typeService == null) throw new IllegalArgumentException("Service not found: "+requestDTO.getType());

        return typeService.execute(requestDTO);
    }

}
