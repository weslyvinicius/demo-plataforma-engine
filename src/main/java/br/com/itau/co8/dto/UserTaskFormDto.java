package br.com.itau.co8.dto;

import java.util.List;

import org.camunda.bpm.engine.form.FormField;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTaskFormDto {

    private String formKey;

    private List<FormField> formFields;

}
