package com.sistemaespecialista.sistemaespecialista.repository;

import com.sistemaespecialista.sistemaespecialista.entities.PerguntaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository <PerguntaEntity, Long> {
}
