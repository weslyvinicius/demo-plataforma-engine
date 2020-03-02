package br.com.itau.co8.service;

import br.com.itau.co8.dto.RequestCompleteDto;
import br.com.itau.co8.dto.ResponseStartJornadaDto;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JornadaService {

    private final RuntimeService runtimeService;

    public ResponseStartJornadaDto startJornada(String nomeJornada, RequestCompleteDto requestCompleteDTO) {

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(nomeJornada, requestCompleteDTO.getFormParam());
        return montaResponse(processInstance);

    }

    private ResponseStartJornadaDto montaResponse(ProcessInstance processInstance) {

        ResponseStartJornadaDto responseStartJornadaDto = new ResponseStartJornadaDto();
        responseStartJornadaDto.setProcessInstanceId(processInstance.getProcessInstanceId());

        return responseStartJornadaDto;
    }

}
