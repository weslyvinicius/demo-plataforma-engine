package br.com.itau.co8.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import br.com.itau.co8.Dto.DeployBpmnDTO;
import br.com.itau.co8.Feign.RestEngine;
import br.com.itau.co8.Util.ConvertUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("deployment/create")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeployBpmnController {

    private final RestEngine restEngine;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void deployJornada( @ModelAttribute("upload") DeployBpmnDTO deployBpmnDTO){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        FileSystemResource file = new FileSystemResource(ConvertUtils.convert(deployBpmnDTO.getFile()));

        body.add("deployment-name", deployBpmnDTO.getNomeBpmn());
        body.add("file", file);
        body.add("enable-duplicate-filtering", Boolean.FALSE);
        body.add("deploy-changed-only", Boolean.FALSE);
        body.add("deployment-source", "local");

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        RestTemplate restTemplate =  new RestTemplate();
        ResponseEntity<String> result  = restTemplate.postForEntity("http://localhost:8080/rest/deployment/create",
                requestEntity, String.class);


        ConvertUtils.deleteFile(file.getFile());


/*
        DeployBpmnEngineDTO deployBpmnEngineDTO = DeployBpmnEngineDTO.builder()
                .deploymentName(deployBpmnDTO.getNomeBpmn())
                .file(ConvertUtils.convert(deployBpmnDTO.getFile()))
                .deployChangedOnly(Boolean.FALSE)
                .enableDuplicateFiltering(Boolean.FALSE)
                .deploymentSource("local")
                .build();

        final ResponseEntity<?> responseEntity = restEngine.uploadBpmnEngine(deployBpmnEngineDTO);

        ConvertUtils.deleteFile(deployBpmnEngineDTO.getFile());
*/
    }



}