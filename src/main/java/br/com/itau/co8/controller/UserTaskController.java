package br.com.itau.co8.controller;

import br.com.itau.co8.dto.RequestCompleteDto;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;


@RestController
@RequestMapping("/v1/user-tasks")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class UserTaskController {

    private final TaskService taskService;

    private final FormService formService;

    @PostMapping(value = "{processInstanceId}/complete")
    public void completeTask(@PathVariable(value = "processInstanceId") String processInstanceId,
                             @RequestBody(required = false) RequestCompleteDto requestCompleteDTO) {
    	
    	TaskQuery taskQuery = taskService.createTaskQuery().processInstanceId(processInstanceId);
        Task task = taskQuery.singleResult();

        if(Optional.ofNullable(requestCompleteDTO).isPresent()) {
            formService.submitTaskForm(task.getId(), requestCompleteDTO.getFormParam());
        }else {
            formService.submitTaskForm(task.getId(), new HashMap<>());
		}
		System.out.println("bla");
	}
}
