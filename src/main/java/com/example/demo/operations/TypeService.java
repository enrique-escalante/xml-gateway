package com.example.demo.operations;

import com.example.demo.entities.dto.RequestDTO;
import com.example.demo.entities.dto.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TypeService {

    ResponseDTO execute(RequestDTO requestDTO) throws JsonProcessingException;
}
