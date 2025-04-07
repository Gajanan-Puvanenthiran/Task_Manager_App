package com.gajanan.Task.Manager.App.repository;

import com.gajanan.Task.Manager.App.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
