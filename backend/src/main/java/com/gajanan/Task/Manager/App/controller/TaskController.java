package com.gajanan.Task.Manager.App.controller;

import com.gajanan.Task.Manager.App.config.aspect.NoLogging;
import com.gajanan.Task.Manager.App.model.dto.RequestDTO;
import com.gajanan.Task.Manager.App.model.dto.ResponseDTO;
import com.gajanan.Task.Manager.App.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Slf4j
@Validated
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @NoLogging
    public ResponseEntity<List<ResponseDTO>>getAllTasks(){
        List<ResponseDTO> taskList=taskService.getAllTasks();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO>createTask(@Valid @RequestBody RequestDTO requestDTO){
        ResponseDTO createdResponseDTO=taskService.createTask(requestDTO);
        if(createdResponseDTO==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(createdResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO>getTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO>updateTask(@PathVariable Long id, @Valid @RequestBody RequestDTO requestDTO){
        ResponseDTO updatedResponseDTO=taskService.updateTask(id,requestDTO);
        if(updatedResponseDTO ==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(updatedResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO>deleteTask(@PathVariable Long id){
        ResponseDTO deletedTask=taskService.deleteTask(id);
        return new ResponseEntity<>(deletedTask, HttpStatus.OK);
    }
}
