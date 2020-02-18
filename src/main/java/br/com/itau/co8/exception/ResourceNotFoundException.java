package br.com.itau.co8.exception;

import feign.Request;

public final class ResourceNotFoundException extends BaseIntegrationException {
   public ResourceNotFoundException(int statusCode, String message, String responseBody, Request.HttpMethod httpMethod,
           Request request) {
      super(statusCode, message, responseBody, httpMethod, request);
   }
}
