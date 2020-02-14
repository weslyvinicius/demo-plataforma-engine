package br.com.itau.co8.Controller;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("Deploy")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeployBpmn {

    private final RuntimeService runtimeService;

    private final ProcessEngine processEngine;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void deployJornada(){


    }


}