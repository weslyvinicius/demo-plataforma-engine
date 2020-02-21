package br.com.itau.co8.controller;

import java.util.HashMap;
import java.util.Optional;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.runtime.Execution;
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

    private final RuntimeService runtimeService;

    @PostMapping(value = "{userTaskId}/process-instance-id/{processInstanceId}/complete")
    public void completeTask(@PathVariable(value = "userTaskId") String userTaskId,
                             @PathVariable(value = "processInstanceId") String processInstanceId,
                             @RequestBody(required = false) RequestCompleteDto requestCompleteDTO) {

        final Execution execution = runtimeService.createNativeExecutionQuery()
                .sql(createQuery())
                .parameter("rootProcessInstanceId", processInstanceId.trim())
                .parameter("userTaskId", userTaskId)
                .parameter("ative", Boolean.TRUE.toString().toUpperCase())
                .singleResult();

        final String processInstanceIduserTask = ((ExecutionEntity) execution).getProcessInstanceId();

        Task task = taskService.createTaskQuery().processInstanceId(processInstanceIduserTask).singleResult();

        if(Optional.ofNullable(requestCompleteDTO).isPresent()) {
            formService.submitTaskForm(task.getId(), requestCompleteDTO.getFormParam());
        }else {
            formService.submitTaskForm(task.getId(), new HashMap<>());
		}
	}

	private String createQuery(){

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM ACT_RU_EXECUTION ");
        sb.append(" WHERE ROOT_PROC_INST_ID_ = #{rootProcessInstanceId} ");
        sb.append(" AND ACT_ID_ = #{userTaskId} ");
        sb.append(" AND IS_ACTIVE_ = #{ative} ");
        return sb.toString();
    }
}
