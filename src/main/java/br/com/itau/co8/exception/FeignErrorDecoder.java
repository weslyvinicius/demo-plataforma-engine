package br.com.itau.co8.exception;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import feign.Request;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    public Exception decode(String methodKey, Response response) {

        if (response != null) {
            String responseBody = null;

            log.info("Status code " + response.status() + ", methodKey = " + methodKey);

            if (response.body() != null) {
                try {
                    responseBody = IOUtils.toString(response.body().asInputStream());
                } catch (IOException e) {
                    responseBody = null;
                }
            }

            if (response.status() == HttpStatus.NOT_FOUND.value()) {
                return new ResourceNotFoundException(response.status(), response.reason(), responseBody,
                        response.request().httpMethod()
                        , response.request());
            }

            return new IntegrationErrorException(response.status(), response.reason(), responseBody,
                    response.request().httpMethod(), response.request());
        }

        return new IntegrationErrorException(response.status(), "Unknow error", StringUtils.EMPTY,
                Request.HttpMethod.OPTIONS, response.request());
    }
}