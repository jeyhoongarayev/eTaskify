package com.abb.etaskify.db.repository;

import com.abb.etaskify.db.entity.Task;
import com.abb.etaskify.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,String> {

    List<Task> findByIdOrganizationAndTaskStatusNot(String organizationId, TaskStatus taskStatus);
    List<Task> findByIduserAndTaskStatusNot(String userId, TaskStatus taskStatus);

    Optional<Task> findByIdAndTaskStatusNot(String taskId, TaskStatus taskStatus);

}
