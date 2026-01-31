package com.example.demo.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpRequestParams {
    /**
     * Nombre del sistema a consultar en la tabla LUNA_PROVISION_URLS
     */
    private String sistema = "";

    /**
     * Parámetros de la uri
     */
    private String uriParams = "";

    /**
     * Método HTTP
     */
    private HttpMethod requestMethod = HttpMethod.GET;

    /**
     * Headers de la petición
     */
    private HashMap<String,String> headers = new HashMap<>();

    /**
     * Cuerpo de la petición
     */
    private String postData = "";

    /**
     * Clase en la que se mapea la respuesta de la petición
     */
    private Class<? extends Object> mappedClass = String.class;

}