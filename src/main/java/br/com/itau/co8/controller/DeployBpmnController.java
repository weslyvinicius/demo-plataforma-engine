package br.com.itau.co8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.co8.dto.DeployBpmnDTO;
import br.com.itau.co8.dto.DeployBpmnEngineDTO;
import br.com.itau.co8.feign.RestEngine;
import br.com.itau.co8.Util.ConvertUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("deployment/create")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeployBpmnController {

    private final RestEngine restEngine;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String deployJornada( @ModelAttribute("upload") DeployBpmnDTO deployBpmnDTO){

        DeployBpmnEngineDTO deployBpmnEngineDTO = DeployBpmnEngineDTO.builder()
                .deploymentName(deployBpmnDTO.getNomeBpmn())
                .file(ConvertUtils.convert(deployBpmnDTO.getFile()))
                .deployChangedOnly(Boolean.FALSE)
                .enableDuplicateFiltering(Boolean.FALSE)
                .deploymentSource("local")
                .build();

        final ResponseEntity<?> responseEntity = restEngine.uploadBpmnEngine(deployBpmnEngineDTO);

        deployBpmnEngineDTO.getFile().delete();

        return "Deploy Realizado com Sucesso !!!";

    }



}