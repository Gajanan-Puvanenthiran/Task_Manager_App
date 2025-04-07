package com.gajanan.Task.Manager.App.service;

import com.gajanan.Task.Manager.App.model.dto.RequestDTO;
import com.gajanan.Task.Manager.App.model.dto.ResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface TaskService {
    List<ResponseDTO> getAllTasks();

    ResponseDTO createTask(@Valid RequestDTO requestDTO);

    ResponseDTO getTask(Long id);

    ResponseDTO updateTask(Long id, @Valid RequestDTO requestDTO);

    ResponseDTO deleteTask(Long id);
}
