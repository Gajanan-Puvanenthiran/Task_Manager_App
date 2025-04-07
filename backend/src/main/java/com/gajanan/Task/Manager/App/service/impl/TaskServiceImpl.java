package com.gajanan.Task.Manager.App.service.impl;

import com.gajanan.Task.Manager.App.model.dto.RequestDTO;
import com.gajanan.Task.Manager.App.model.dto.ResponseDTO;
import com.gajanan.Task.Manager.App.model.entity.Task;
import com.gajanan.Task.Manager.App.repository.TaskRepository;
import com.gajanan.Task.Manager.App.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> modelMapper.map(task, ResponseDTO.class))
                .toList();
    }

    @Override
    public ResponseDTO createTask(RequestDTO requestDTO) {
        Task task = modelMapper.map(requestDTO, Task.class);
        taskRepository.save(task);
        return modelMapper.map(task, ResponseDTO.class);

    }

    @Override
    public ResponseDTO getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Task not found"));
        return modelMapper.map(task, ResponseDTO.class);
    }

    @Override
    public ResponseDTO updateTask(Long id, RequestDTO requestDTO) {
        Task task=taskRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Task not found"));
        modelMapper.map(requestDTO,task);
        taskRepository.save(task);
        return modelMapper.map(task, ResponseDTO.class);
    }

    @Override
    public ResponseDTO deleteTask(Long id) {
        Task task=taskRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Task not found"));
        taskRepository.delete(task);
        return modelMapper.map(task, ResponseDTO.class);

    }
}
