package br.com.itau.co8.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.co8.dto.RequestCompleteDto;
import br.com.itau.co8.dto.UserTaskFormDto;
import br.com.itau.co8.service.UserTaskService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/v1/user-tasks")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserTaskController {

   private final UserTaskService userTaskService;

    @PostMapping(value = "{userTaskId}/{processInstanceId}/complete")
    public void completeTask(@PathVariable(value = "userTaskId") String userTaskId,
                             @PathVariable(value = "processInstanceId") String processInstanceId,
                             @RequestBody(required = false) RequestCompleteDto requestCompleteDTO) {

        Map<String, Object> formParametros = Optional.ofNullable(requestCompleteDTO).isPresent() ?
                requestCompleteDTO.getFormParam() : new HashMap<>();

        userTaskService.userTaskComplete(userTaskId, processInstanceId, formParametros);
	}

    @GetMapping(value = "{userTaskId}/{processInstanceId}/form")
    @ResponseStatus(HttpStatus.OK)
    public UserTaskFormDto formTask(@PathVariable(value = "userTaskId") String userTaskId,
            @PathVariable(value = "processInstanceId") String processInstanceId) {
        return userTaskService.formUserTask(userTaskId, processInstanceId);
    }

}
