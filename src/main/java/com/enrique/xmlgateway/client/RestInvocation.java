package com.enrique.xmlgateway.client;

import com.enrique.xmlgateway.model.dto.HttpRequestParams;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class RestInvocation {

    private final WebClient webClient;

    public RestInvocation() {
        this.webClient = WebClient.builder().build();
    }


    public ResponseEntity<String> webfluxRequest(HttpRequestParams uriParams) {

        String url = uriParams.getSistema();

        Map<String, String> headers = uriParams.getHeaders();

        ResponseEntity<String> responseEntity;

        try {
            responseEntity = webClient.method(uriParams.getRequestMethod())
                    .uri(url)
                    .headers(httpHeaders -> {
                        headers.forEach(httpHeaders::add);
                        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                        httpHeaders.set(HttpHeaders.ACCEPT, "/");
                    })
                    .body(Mono.just(uriParams.getPostData()), String.class)
                    .retrieve()
                    .toEntity(String.class)
                    .block();

        } catch (WebClientResponseException ex) {
            // En caso de un error HTTP, capturamos el código de estado y el cuerpo de error
            responseEntity = ResponseEntity
                    .status(ex.getRawStatusCode())
                    .body(ex.getResponseBodyAsString());
        } catch (Exception e) {
            // En caso de un error inesperado
            responseEntity = ResponseEntity
                    .status(500)
                    .body("Error inesperado: " + e.getMessage());
        }

        return responseEntity;


    }
}
