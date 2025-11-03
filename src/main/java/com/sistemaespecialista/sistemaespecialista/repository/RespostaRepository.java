package com.sistemaespecialista.sistemaespecialista.repository;

import com.sistemaespecialista.sistemaespecialista.model.PerguntaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository <PerguntaEntity, Long> {
}
