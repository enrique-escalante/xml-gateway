package com.enrique.xmlgateway.operations;

import com.enrique.xmlgateway.model.dto.HttpRequestParams;
import com.enrique.xmlgateway.model.dto.RequestDTO;
import com.enrique.xmlgateway.model.dto.ResponseDTO;
import com.enrique.xmlgateway.client.RestInvocation;
import com.enrique.xmlgateway.service.MainProcessorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;

/**
 * Account service implementation for account-related operations.
 * Handles account data processing and transformation.
 */
@Service("account")
@RequiredArgsConstructor
public class AccountService implements TypeService{

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);


    /**
     * REST client for external API calls.
     */
    private final RestInvocation restInvocation;

    /**
     * Maps internal field names to external API field names.
     * Used for request parameter transformation.
     */
    private static final Map<String, String> nameConversionMap = Map.ofEntries(
            entry("id", "accountId"),
            entry("name","fullName"),
            entry("phoneNumber", "phone"),
            entry("address", "fullAddress"),
            entry("active", "activation"),
            entry("balance", "accountBalance"),
            entry("restrictions", "accountLimit")
    );


    /**
     * Executes account operation with transformed parameters.
     *
     * @param requestDTO Account request with parameters
     * @return Account operation response
     */
    @Override
    public ResponseDTO execute(RequestDTO requestDTO) throws JsonProcessingException {
        logger.info("Entering in AccountService");
        String action = requestDTO.getAction().toUpperCase();
        String name = requestDTO.getName().toLowerCase();
        String user = requestDTO.getUser();
        Map<String, String> inputParams = requestDTO.getParameters();
        logger.info("Name: "+name+ " Action: "+action);
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
                httpRequestParams.setPostData(conversionJson(inputParams,user));
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
                httpRequestParams.setPostData(conversionJson(inputParams,user));
                restInvocation.webfluxRequest(httpRequestParams);
                break;
            case "UPDATE":
                endpoint = "/account/{accountId}/"+name;
                httpRequestParams.setSistema(endpoint);
                httpRequestParams.setRequestMethod(HttpMethod.PATCH);
                httpRequestParams.setPostData(conversionJson(inputParams, user));
                restInvocation.webfluxRequest(httpRequestParams);
                break;
        }
        return new ResponseDTO("200","Configuration OK.");
    }


    /**
     * Converts input parameters using name mapping and adds user field.
     * Transforms map to JSON string.
     *
     * @param inputParams Original parameter map
     * @param user Username to add to parameters
     * @return JSON string with transformed parameters
     */
    private String conversionJson(Map<String, String> inputParams, String user) throws JsonProcessingException {

        Map<String, String> inputParamsCorrect = inputParams.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> nameConversionMap.getOrDefault(entry.getKey(), entry.getKey()),
                        Map.Entry::getValue
                ));


        ObjectMapper objectMapper = new ObjectMapper();
        inputParams.put("user",user);
        logger.info("Correct params: "+inputParamsCorrect);
        return objectMapper.writeValueAsString(inputParamsCorrect);
    }
}
