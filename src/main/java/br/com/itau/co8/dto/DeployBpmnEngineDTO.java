package br.com.itau.co8.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class    DeployBpmnEngineDTO {

    @JsonProperty(value = "deployment-name")
    private String deploymentName;

    private MultipartFile file;

    @JsonProperty(value = "enable-duplicate-filtering")
    private Boolean enableDuplicateFiltering = Boolean.FALSE;

    @JsonProperty(value = "deploy-changed-only")
    private Boolean deployChangedOnly = Boolean.FALSE;

    @JsonProperty(value = "deployment-source")
    private String deploymentSource = "local";
}
