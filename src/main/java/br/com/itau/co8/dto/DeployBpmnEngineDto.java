package br.com.itau.co8.dto;

import java.io.File;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeployBpmnEngineDto {

    @FormProperty(value = "deployment-name")
    private String deploymentName;

    @FormProperty(value = "file")
    private File file;

    @FormProperty(value = "enable-duplicate-filtering")
    private boolean enableDuplicateFiltering;

    @FormProperty(value = "deploy-changed-only")
    private boolean deployChangedOnly;

    @FormProperty(value = "deployment-source")
    private String deploymentSource;

    public DeployBpmnEngineDto(String deploymentName, File file, boolean enableDuplicateFiltering,
            boolean deployChangedOnly, String deploymentSource) {
        this.deploymentName = deploymentName;
        this.file = file;
        this.enableDuplicateFiltering = enableDuplicateFiltering;
        this.deployChangedOnly = deployChangedOnly;
        this.deploymentSource = deploymentSource;
    }
}
