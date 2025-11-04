package com.sistemaespecialista.sistemaespecialista.repository;

import com.sistemaespecialista.sistemaespecialista.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Long> {
}
