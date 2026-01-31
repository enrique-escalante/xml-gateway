package com.example.demo.operations;

import com.example.demo.entities.dto.HttpRequestParams;
import com.example.demo.entities.dto.RequestDTO;
import com.example.demo.entities.dto.ResponseDTO;
import com.example.demo.utils.RestInvocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;

@Service("account")
@RequiredArgsConstructor
public class AccountService implements TypeService{

    private final RestInvocation restInvocation;
    private static final Map<String, String> nameConversionMap = Map.ofEntries(
            entry("id", "accountId"),
            entry("name","fullName"),
            entry("phoneNumber", "phone"),
            entry("address", "fullAddress"),
            entry("active", "activation"),
            entry("balance", "accountBalance"),
            entry("restrictions", "accountLimit")
    );
    @Override
    public ResponseDTO execute(RequestDTO requestDTO) throws JsonProcessingException {
        String action = requestDTO.getAction().toUpperCase();
        String name = requestDTO.getName().toLowerCase();
        Map<String, String> inputParams = requestDTO.getParameters();

        String endpoint = "";
        HttpRequestParams httpRequestParams = new HttpRequestParams();
        //details, status, balance,
        switch (action){
            case "GET":
                //TODO recover endpoint from DB and replace id
                endpoint = "/account/{accountId}/"+name;
                httpRequestParams.setSistema(endpoint);
                httpRequestParams.setRequestMethod(HttpMethod.GET);
                restInvocation.webfluxRequest(httpRequestParams);
                break;
            case "CREATE":
                endpoint = "/account/"+name+"/new";
                httpRequestParams.setSistema(endpoint);
                httpRequestParams.setRequestMethod(HttpMethod.POST);
                httpRequestParams.setPostData(conversionJson(inputParams));
                restInvocation.webfluxRequest(httpRequestParams);
                break;
            case "DELETE":
                endpoint = "/account/{accountId}/"+name;
                httpRequestParams.setSistema(endpoint);
                httpRequestParams.setRequestMethod(HttpMethod.DELETE);
                restInvocation.webfluxRequest(httpRequestParams);
                break;
            case "REPLACE":
                endpoint = "/account/{accountId}/"+name;
                httpRequestParams.setSistema(endpoint);
                httpRequestParams.setRequestMethod(HttpMethod.PUT);
                httpRequestParams.setPostData(conversionJson(inputParams));
                restInvocation.webfluxRequest(httpRequestParams);
                break;
            case "UPDATE":
                endpoint = "/account/{accountId}/"+name;
                httpRequestParams.setSistema(endpoint);
                httpRequestParams.setRequestMethod(HttpMethod.PATCH);
                httpRequestParams.setPostData(conversionJson(inputParams));
                restInvocation.webfluxRequest(httpRequestParams);
                break;
        }
        return new ResponseDTO("200","Configuration OK.");
    }

    private String conversionJson(Map<String, String> inputParams) throws JsonProcessingException {

        Map<String, String> inputParamsCorrect = inputParams.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> nameConversionMap.getOrDefault(entry.getKey(), entry.getKey()),
                        Map.Entry::getValue
                ));
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(inputParamsCorrect);
    }
}
