package br.com.itau.co8.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("jornadas/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JornadaController {

    private final RuntimeService runtimeService;

    @PostMapping("nome-jornada/{nomeJornada}/start")
    public String start(@PathVariable String nomeJornada) {

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(nomeJornada);
        return processInstance.getProcessInstanceId();
    }

}
