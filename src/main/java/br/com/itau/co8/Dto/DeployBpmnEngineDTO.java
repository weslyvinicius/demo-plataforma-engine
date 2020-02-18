package br.com.itau.co8.Dto;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeployBpmnEngineDTO {

    @JsonProperty(value = "deployment-name")
    private String deploymentName;

    @JsonProperty(value = "file")
    private File file;

    @JsonProperty(value = "enable-duplicate-filtering")
    private boolean enableDuplicateFiltering;

    @JsonProperty(value = "deploy-changed-only")
    private boolean deployChangedOnly;

    @JsonProperty(value = "deployment-source")
    private String deploymentSource;

    @JsonCreator
    public DeployBpmnEngineDTO(String deploymentName, File file, boolean enableDuplicateFiltering,
            boolean deployChangedOnly, String deploymentSource) {
        this.deploymentName = deploymentName;
        this.file = file;
        this.enableDuplicateFiltering = enableDuplicateFiltering;
        this.deployChangedOnly = deployChangedOnly;
        this.deploymentSource = deploymentSource;
    }
}
