package br.com.itau.co8.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeployBpmnDto {

    @NotEmpty
    private String nomeBpmn;

    @NonNull
    private MultipartFile file;

}
