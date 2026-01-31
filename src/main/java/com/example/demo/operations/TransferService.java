package com.example.demo.operations;

import com.example.demo.entities.dto.RequestDTO;
import com.example.demo.entities.dto.ResponseDTO;
import org.springframework.stereotype.Service;

@Service("transfer")
public class TransferService implements TypeService{
    @Override
    public ResponseDTO execute(RequestDTO requestDTO) {
        //todo
        return null;
    }
}
