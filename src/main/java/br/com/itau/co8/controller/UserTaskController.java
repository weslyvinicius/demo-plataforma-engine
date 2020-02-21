package br.com.itau.co8.controller;

import java.util.HashMap;
import java.util.Optional;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.co8.dto.RequestCompleteDto;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/v1/user-tasks")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class UserTaskController {

    private final TaskService taskService;

    private final FormService formService;

    @PostMapping(value = "{userTaskId}/complete")
    public void completeTask(@PathVariable(value = "userTaskId") String userTaskId,
                             @RequestBody(required = false) RequestCompleteDto requestCompleteDTO) {

        Task task = taskService.createTaskQuery().taskDefinitionKey("userTaskId").singleResult();

        if(Optional.ofNullable(requestCompleteDTO).isPresent()) {
            formService.submitTaskForm(task.getId(), requestCompleteDTO.getFormParam());
        }else {
            formService.submitTaskForm(task.getId(), new HashMap<>());
        }
    }
}
