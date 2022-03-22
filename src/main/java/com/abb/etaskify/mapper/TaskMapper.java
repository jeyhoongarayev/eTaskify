package com.abb.etaskify.mapper;

import com.abb.etaskify.db.entity.Task;
import com.abb.etaskify.model.request.TaskRequest;
import com.abb.etaskify.model.response.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface TaskMapper {

    Task toTask(TaskRequest taskRequest);

    TaskResponse toTaskResponse(Task task);

}
