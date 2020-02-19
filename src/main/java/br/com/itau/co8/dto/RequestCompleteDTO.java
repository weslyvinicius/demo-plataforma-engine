package br.com.itau.co8.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCompleteDTO {

    @JsonProperty("parametrosEntrada")
    private List<EntradaRequestCompleteDTO> entradas;

}
