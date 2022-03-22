package com.abb.etaskify.api;

import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.request.TaskRequest;
import com.abb.etaskify.model.response.TaskResponse;
import com.abb.etaskify.services.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/task")
public class TaskController {

    private final TaskServiceImpl taskService;

    @PostMapping
    public ResponseEntity<ResponseData<TaskResponse>> create(@RequestBody TaskRequest taskRequest){
       return ResponseEntity.ok(taskService.create(taskRequest));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<ResponseData<TaskResponse>> updateOrganization(@RequestBody TaskRequest taskRequest,
                                                             @PathVariable String taskId){
        return ResponseEntity.ok(taskService.update(taskRequest, taskId));
    }

}
