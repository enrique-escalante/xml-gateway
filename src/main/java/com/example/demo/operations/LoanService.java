package com.example.demo.operations;

import com.example.demo.entities.dto.RequestDTO;
import com.example.demo.entities.dto.ResponseDTO;
import org.springframework.stereotype.Service;

@Service("loan")
public class LoanService implements TypeService{
    @Override
    public ResponseDTO execute(RequestDTO requestDTO) {
        //todo
        return null;
    }
}
