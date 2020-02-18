package br.com.itau.co8.exception;

import feign.Request;

public final class IntegrationErrorException extends BaseIntegrationException {
   
   private final String message;

   
   public String getMessage() {
      return this.message;
   }

   public IntegrationErrorException(int statusCode, String message, String responseBody, Request.HttpMethod httpMethod,
           Request request) {
      super(statusCode, message, responseBody, httpMethod, request);
      this.message = message;
   }
}
