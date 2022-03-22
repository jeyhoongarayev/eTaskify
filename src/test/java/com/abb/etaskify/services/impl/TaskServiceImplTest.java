package com.abb.etaskify.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abb.etaskify.db.entity.Task;
import com.abb.etaskify.db.repository.TaskRepository;
import com.abb.etaskify.mapper.TaskMapper;
import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.enums.TaskStatus;
import com.abb.etaskify.model.request.TaskRequest;
import com.abb.etaskify.model.response.TaskResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TaskServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TaskServiceImplTest {

    private static final LocalDate DEADLINE = LocalDate.ofEpochDay(1L);
    private static final String DESCRIPTION = "demo";
    private static final String ID = "1";
    private static final String ID_ORGANIZATION = "demo";
    private static final TaskStatus TASK_STATUS = TaskStatus.CREATED;
    private static final String TITLE = "demo";
    private static final String ID_USER = "2";

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskServiceImpl taskServiceImpl;


    @Test
    void testGetAllTasksByOrganization() {
        when(this.taskRepository.findByIdOrganizationAndTaskStatusNot((String) any(),
                (com.abb.etaskify.model.enums.TaskStatus) any())).thenReturn(new ArrayList<>());
        ResponseData<List<Task>> actualAllTasksByOrganization = this.taskServiceImpl
                .getAllTasksByOrganization(ID_ORGANIZATION);
        assertEquals(HttpStatus.NOT_FOUND.value(), actualAllTasksByOrganization.getCode());
        assertEquals(HttpStatus.NOT_FOUND.name(), actualAllTasksByOrganization.getMessage());
        assertNull(actualAllTasksByOrganization.getData());
        verify(this.taskRepository).findByIdOrganizationAndTaskStatusNot((String) any(),
                (com.abb.etaskify.model.enums.TaskStatus) any());
    }

    @Test
    void testGetAllTasksByOrganization2() {
        Task task = new Task();

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(this.taskRepository.findByIdOrganizationAndTaskStatusNot((String) any(), (TaskStatus) any()))
                .thenReturn(taskList);
        ResponseData<List<Task>> actualAllTasksByOrganization = this.taskServiceImpl
                .getAllTasksByOrganization(ID_ORGANIZATION);
        assertEquals(200, actualAllTasksByOrganization.getCode());
        assertEquals("OK", actualAllTasksByOrganization.getMessage());
        assertEquals(1, actualAllTasksByOrganization.getData().size());
        verify(this.taskRepository).findByIdOrganizationAndTaskStatusNot((String) any(), (TaskStatus) any());
    }

    @Test
    void testGetAllTasksByOrganization3() {
        when(this.taskRepository.findByIdOrganizationAndTaskStatusNot((String) any(),
                (com.abb.etaskify.model.enums.TaskStatus) any())).thenReturn(new ArrayList<>());
        ResponseData<List<Task>> actualAllTasksByOrganization = this.taskServiceImpl
                .getAllTasksByOrganization("Id Organization");
        assertEquals(404, actualAllTasksByOrganization.getCode());
        assertEquals("NOT_FOUND", actualAllTasksByOrganization.getMessage());
        assertNull(actualAllTasksByOrganization.getData());
        verify(this.taskRepository).findByIdOrganizationAndTaskStatusNot((String) any(),
                (com.abb.etaskify.model.enums.TaskStatus) any());
    }

    @Test
    void testGetAllTasksByOrganization4() {
        Task task = new Task();
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(this.taskRepository.findByIdOrganizationAndTaskStatusNot((String) any(), (TaskStatus) any()))
                .thenReturn(taskList);
        ResponseData<List<Task>> actualAllTasksByOrganization = this.taskServiceImpl
                .getAllTasksByOrganization(ID_ORGANIZATION);
        assertEquals(HttpStatus.OK.value(), actualAllTasksByOrganization.getCode());
        assertEquals(HttpStatus.OK.name(), actualAllTasksByOrganization.getMessage());
        assertEquals(1, actualAllTasksByOrganization.getData().size());
        verify(this.taskRepository).findByIdOrganizationAndTaskStatusNot((String) any(), (TaskStatus) any());
    }

    @Test
    void testGetAllTasksByUser() {
        when(this.taskRepository.findByIduserAndTaskStatusNot((String) any(),
                (com.abb.etaskify.model.enums.TaskStatus) any())).thenReturn(new ArrayList<>());
        ResponseData<List<Task>> actualAllTasksByUser = this.taskServiceImpl.getAllTasksByUser(ID_USER);
        assertEquals(HttpStatus.NOT_FOUND.value(), actualAllTasksByUser.getCode());
        assertEquals(HttpStatus.NOT_FOUND.name(), actualAllTasksByUser.getMessage());
        assertNull(actualAllTasksByUser.getData());
        verify(this.taskRepository).findByIduserAndTaskStatusNot((String) any(),
                (com.abb.etaskify.model.enums.TaskStatus) any());
    }

    @Test
    void testGetAllTasksByUser2() {
        Task task = new Task();
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(this.taskRepository.findByIduserAndTaskStatusNot((String) any(), (TaskStatus) any())).thenReturn(taskList);
        ResponseData<List<Task>> actualAllTasksByUser = this.taskServiceImpl.getAllTasksByUser(ID_USER);
        assertEquals(200, actualAllTasksByUser.getCode());
        assertEquals("OK", actualAllTasksByUser.getMessage());
        assertEquals(1, actualAllTasksByUser.getData().size());
        verify(this.taskRepository).findByIduserAndTaskStatusNot((String) any(), (TaskStatus) any());
    }

    @Test
    void testGetAllTasksByUser3() {
        when(this.taskRepository.findByIduserAndTaskStatusNot((String) any(),
                (com.abb.etaskify.model.enums.TaskStatus) any())).thenReturn(new ArrayList<>());
        ResponseData<List<Task>> actualAllTasksByUser = this.taskServiceImpl.getAllTasksByUser(ID_USER);
        assertEquals(404, actualAllTasksByUser.getCode());
        assertEquals("NOT_FOUND", actualAllTasksByUser.getMessage());
        assertNull(actualAllTasksByUser.getData());
        verify(this.taskRepository).findByIduserAndTaskStatusNot((String) any(),
                (com.abb.etaskify.model.enums.TaskStatus) any());
    }

    @Test
    void testGetAllTasksByUser4() {
        Task task = new Task();

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(this.taskRepository.findByIduserAndTaskStatusNot((String) any(), (TaskStatus) any())).thenReturn(taskList);
        ResponseData<List<Task>> actualAllTasksByUser = this.taskServiceImpl.getAllTasksByUser(ID_USER);
        assertEquals(200, actualAllTasksByUser.getCode());
        assertEquals("OK", actualAllTasksByUser.getMessage());
        assertEquals(1, actualAllTasksByUser.getData().size());
        verify(this.taskRepository).findByIduserAndTaskStatusNot((String) any(), (TaskStatus) any());
    }

    @Test
    void testCreate() {
        Task task = new Task();
        when(this.taskRepository.save((Task) any())).thenReturn(task);
        Task task1 = new Task();
        when(this.taskMapper.toTask((TaskRequest) any())).thenReturn(task1);
        TaskResponse taskResponse = new TaskResponse();
        when(this.taskMapper.toTaskResponse((Task) any())).thenReturn(taskResponse);
        ResponseData<TaskResponse> actualCreateResult = this.taskServiceImpl.create(new TaskRequest());
        assertEquals(200, actualCreateResult.getCode());
        assertEquals("SUCCESS", actualCreateResult.getMessage());
        assertSame(taskResponse, actualCreateResult.getData());
        verify(this.taskRepository).save((Task) any());
        verify(this.taskMapper).toTask((TaskRequest) any());
        verify(this.taskMapper).toTaskResponse((Task) any());
    }


    @Test
    void testUpdate() {
        Task task = new Task();
        Optional<Task> ofResult = Optional.of(task);
        Task task1 = new Task();
        when(this.taskRepository.save((Task) any())).thenReturn(task1);
        when(this.taskRepository.findByIdAndTaskStatusNot((String) any(), (TaskStatus) any())).thenReturn(ofResult);

        Task task2 = new Task();
        TaskResponse taskResponse = new TaskResponse();
        when(this.taskMapper.toTaskResponse((Task) any())).thenReturn(taskResponse);
        when(this.taskMapper.toTask((TaskRequest) any())).thenReturn(task2);
        ResponseData<TaskResponse> actualUpdateResult = this.taskServiceImpl.update(new TaskRequest(), ID);
        assertEquals(HttpStatus.CREATED.value(), actualUpdateResult.getCode());
        assertEquals(HttpStatus.CREATED.name(), actualUpdateResult.getMessage());
        assertSame(taskResponse, actualUpdateResult.getData());
        verify(this.taskRepository).save((Task) any());
        verify(this.taskRepository).findByIdAndTaskStatusNot((String) any(), (TaskStatus) any());
        verify(this.taskMapper).toTask((TaskRequest) any());
        verify(this.taskMapper).toTaskResponse((Task) any());
    }

    @Test
    void testUpdate2() {
        Task task = new Task();
        when(this.taskRepository.save((Task) any())).thenReturn(task);
        when(this.taskRepository.findByIdAndTaskStatusNot((String) any(), (TaskStatus) any())).thenReturn(Optional.empty());
        Task task1 = new Task();
        when(this.taskMapper.toTaskResponse((Task) any())).thenReturn(new TaskResponse());
        when(this.taskMapper.toTask((TaskRequest) any())).thenReturn(task1);
        ResponseData<TaskResponse> actualUpdateResult = this.taskServiceImpl.update(new TaskRequest(), ID);
        assertEquals(HttpStatus.NOT_FOUND.value(), actualUpdateResult.getCode());
        assertEquals(HttpStatus.NOT_FOUND.name(), actualUpdateResult.getMessage());
        assertNull(actualUpdateResult.getData());
        verify(this.taskRepository).findByIdAndTaskStatusNot((String) any(), (TaskStatus) any());
    }

    @Test
    void testUpdate3() {
        Task task = new Task();

        Optional<Task> ofResult = Optional.of(task);

        Task task1 = new Task();

        when(this.taskRepository.save((Task) any())).thenReturn(task1);
        when(this.taskRepository.findByIdAndTaskStatusNot((String) any(), (TaskStatus) any())).thenReturn(ofResult);

        Task task2 = new Task();

        TaskResponse taskResponse = new TaskResponse();
        when(this.taskMapper.toTaskResponse((Task) any())).thenReturn(taskResponse);
        when(this.taskMapper.toTask((TaskRequest) any())).thenReturn(task2);
        ResponseData<TaskResponse> actualUpdateResult = this.taskServiceImpl.update(new TaskRequest(), ID);
        assertEquals(HttpStatus.CREATED.value(), actualUpdateResult.getCode());
        assertEquals(HttpStatus.CREATED.name(), actualUpdateResult.getMessage());
        assertSame(taskResponse, actualUpdateResult.getData());
        verify(this.taskRepository).save((Task) any());
        verify(this.taskRepository).findByIdAndTaskStatusNot((String) any(), (TaskStatus) any());
        verify(this.taskMapper).toTask((TaskRequest) any());
        verify(this.taskMapper).toTaskResponse((Task) any());
    }

    @Test
    void testUpdate4() {
        Task task = new Task();
        when(this.taskRepository.save((Task) any())).thenReturn(task);
        when(this.taskRepository.findByIdAndTaskStatusNot((String) any(), (TaskStatus) any())).thenReturn(Optional.empty());

        Task task1 = new Task();
        when(this.taskMapper.toTaskResponse((Task) any())).thenReturn(new TaskResponse());
        when(this.taskMapper.toTask((TaskRequest) any())).thenReturn(task1);
        ResponseData<TaskResponse> actualUpdateResult = this.taskServiceImpl.update(new TaskRequest(), ID);
        assertEquals(HttpStatus.NOT_FOUND.value(), actualUpdateResult.getCode());
        assertEquals(HttpStatus.NOT_FOUND.name(), actualUpdateResult.getMessage());
        assertNull(actualUpdateResult.getData());
        verify(this.taskRepository).findByIdAndTaskStatusNot((String) any(), (TaskStatus) any());
    }
}

