package br.com.itau.co8.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.itau.co8.dto.DeployBpmnEngineDTO;
import feign.Headers;


@FeignClient(url = "localhost:8080/rest", name = "demo-platafoma-engine")
public interface RestEngine {

    @PostMapping(value ="/deployment/create", consumes = "multipart/form-data")
    @Headers("Content-Type: multipart/form-data")
    ResponseEntity<?> uploadBpmnEngine(DeployBpmnEngineDTO deployBpmnEngineDTO);
}
