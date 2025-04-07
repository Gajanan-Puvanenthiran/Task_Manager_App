package com.gajanan.Task.Manager.App.repository;

import com.gajanan.Task.Manager.App.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
