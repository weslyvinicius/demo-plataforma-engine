package br.com.itau.co8.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.itau.co8.dto.DeployBpmnDTO;
import br.com.itau.co8.dto.DeployBpmnEngineDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("deployment/create")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeployBpmnController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void deployJornada( @ModelAttribute("upload") DeployBpmnDTO deployBpmnDTO){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("deployment-name", deployBpmnDTO.getNomeBpmn());
        body.add("file", new FileSystemResource(convert(deployBpmnDTO.getFile())));
        body.add("enable-duplicate-filtering", Boolean.FALSE);
        body.add("deploy-changed-only", Boolean.FALSE);
        body.add("deployment-source", "local");

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        RestTemplate restTemplate =  new RestTemplate();
        ResponseEntity<String> result  = restTemplate.postForEntity("http://localhost:8080/rest/deployment/create",
                requestEntity, String.class);

    }

    public static File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return convFile;
    }


}