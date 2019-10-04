package com.eventz.io.eventz.config;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by Michael.Akobundu on 4/2/2019.
 */
public class JsonContentTypeInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set("Accept", MediaType.APPLICATION_JSON_VALUE);
        request.getHeaders().set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return execution.execute(request, body);

    }
}
