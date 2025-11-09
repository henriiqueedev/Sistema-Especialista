package com.sistemaespecialista.sistemaespecialista.repository;

import com.sistemaespecialista.sistemaespecialista.entities.RespostaSistemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RespostaSistemaRepository extends JpaRepository<RespostaSistemaEntity, Long> {
    Optional<RespostaSistemaEntity> findByUsuarioId(Long usuarioId);
}
