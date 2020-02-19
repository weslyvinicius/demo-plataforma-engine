package br.com.itau.co8.controller;

import br.com.itau.co8.dto.RequestCompleteDTO;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;


@RestController
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("user-tasks/v1")
public class UserTaskController {

    private final TaskService taskService;

    private final FormService formService;

    @PostMapping(value = "processInstanceId/{processInstanceId}/complete")
    public void completeTask(@PathVariable(value = "processInstanceId") String processInstanceId,
                             @RequestBody(required = false) RequestCompleteDTO requestCompleteDTO) {

        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();

        if(Optional.ofNullable(requestCompleteDTO.getFormParam()).isPresent()) {
            formService.submitTaskForm(task.getId(), requestCompleteDTO.getFormParam());
        }else {
            formService.submitTaskForm(task.getId(), new HashMap<>());
        }

    }
}
