package br.com.itau.co8.Controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("start/jornada")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StartJornadaController {

    private final RuntimeService runtimeService;

    @PostMapping("/{nomeJornada}")
    public String start(@PathVariable String nomeJornada) {

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(nomeJornada);
        return processInstance.getProcessInstanceId();
    }

}