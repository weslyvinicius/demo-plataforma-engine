package br.com.itau.co8.service;

import java.util.Optional;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itau.co8.dto.RequestCompleteDto;
import br.com.itau.co8.dto.ResponseStartJornadaDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JornadaService {

    private final RuntimeService runtimeService;

    public ResponseStartJornadaDto startJornada(String nomeJornada, RequestCompleteDto requestCompleteDTO) {
        ProcessInstance processInstance;
        if (Optional.ofNullable(requestCompleteDTO).isPresent()) {
            processInstance = runtimeService.startProcessInstanceByKey(nomeJornada,
                    requestCompleteDTO.getFormParam());
        }else{
            processInstance = runtimeService.startProcessInstanceByKey(nomeJornada);
        }
        return montaResponse(processInstance);

    }

    private ResponseStartJornadaDto montaResponse(ProcessInstance processInstance) {
        return ResponseStartJornadaDto.builder().processInstanceId(processInstance.getProcessInstanceId()).build();
    }

}
