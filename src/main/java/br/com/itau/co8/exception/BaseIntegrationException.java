package br.com.itau.co8.exception;

import java.util.Date;

import feign.Request;
import feign.RetryableException;

public abstract class BaseIntegrationException extends RetryableException {

    private final String responseBody;

    public BaseIntegrationException(int statusCode, String message, String responseBody, Request.HttpMethod httpMethod,
            Request request) {
        super(statusCode, message, httpMethod, new Date(), request);
        this.responseBody = responseBody;
    }

    public final String getResponseBody() {
        return this.responseBody;
    }
}
