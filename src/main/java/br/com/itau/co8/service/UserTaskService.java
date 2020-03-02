package br.com.itau.co8.service;

import java.util.Map;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itau.co8.dto.UserTaskFormDto;
import br.com.itau.co8.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserTaskService {

    private final TaskService taskService;

    private final FormService formService;

    private final RuntimeService runtimeService;

    public void userTaskComplete(final String userTaskId, final String processInstanceId, final Map<String,
                Object> formParametos){

        final Execution execution = runtimeService.createNativeExecutionQuery()
                .sql(createQuery())
                .parameter("rootProcessInstanceId", processInstanceId.trim())
                .parameter("userTaskId", userTaskId)
                .parameter("ative", Boolean.TRUE.toString().toUpperCase())
                .singleResult();

        final String processInstanceIduserTask = ((ExecutionEntity) execution).getProcessInstanceId();

        Task task = taskService.createTaskQuery().processInstanceId(processInstanceIduserTask).singleResult();
        formService.submitTaskForm(task.getId(), formParametos);

    }

    private String createQuery(){

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM ACT_RU_EXECUTION ");
        sb.append(" WHERE ROOT_PROC_INST_ID_ = #{rootProcessInstanceId} ");
        sb.append(" AND ACT_ID_ = #{userTaskId} ");
        sb.append(" AND IS_ACTIVE_ = #{ative} ");
        return sb.toString();
    }

    public UserTaskFormDto formUserTask(String processInstanceId) {
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());

        if( taskFormData !=null)
            return UserTaskFormDto.builder()
                    .formKey(taskFormData.getFormKey())
                    .formFields(taskFormData.getFormFields())
                    .build();
        throw new NotFoundException();

    }
}
