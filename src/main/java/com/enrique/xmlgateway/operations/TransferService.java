package com.enrique.xmlgateway.operations;

import com.enrique.xmlgateway.model.dto.RequestDTO;
import com.enrique.xmlgateway.model.dto.ResponseDTO;
import org.springframework.stereotype.Service;

@Service("transfer")
public class TransferService implements TypeService{
    @Override
    public ResponseDTO execute(RequestDTO requestDTO) {
        //todo
        return null;
    }
}
