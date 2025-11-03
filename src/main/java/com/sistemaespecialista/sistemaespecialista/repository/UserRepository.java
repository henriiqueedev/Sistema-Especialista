package com.sistemaespecialista.sistemaespecialista.repository;

import com.sistemaespecialista.sistemaespecialista.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Long> {
}
