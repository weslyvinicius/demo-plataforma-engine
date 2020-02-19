package br.com.itau.co8.controller;

import br.com.itau.co8.dto.Entrada;
import br.com.itau.co8.dto.RequestCompleteDTO;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("user-tasks")
public class UserTaskController {

    private final TaskService taskService;

    private final FormService formService;

    @PostMapping(value = "/v1/{processInstanceId}/complete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void completeTask(@PathVariable(value = "processInstanceId") String processInstanceId,
                             @RequestBody RequestCompleteDTO requestCompleteDTO) {

        Map<String, Object> parametrosEntrada = new HashMap<>();

        for (Entrada t : requestCompleteDTO.getEntradas()) {
            parametrosEntrada.put(t.getId(), t.getValor());
        }

        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        formService.submitTaskForm(task.getId(), parametrosEntrada);

    }
}
