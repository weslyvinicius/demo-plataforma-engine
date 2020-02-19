package br.com.itau.co8.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.itau.co8.dto.DeployBpmnEngineDto;
import feign.Headers;


@FeignClient(url = "${server.url.rest.camunda}", name = "${spring.application.name}")
public interface RestEngine {

    @PostMapping(value ="${camunda.rest.deployment.create}", consumes = "multipart/form-data")
    @Headers("Content-Type: multipart/form-data")
    ResponseEntity<?> uploadBpmnEngine(DeployBpmnEngineDto deployBpmnEngineDTO);
}
