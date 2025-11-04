package com.sistemaespecialista.sistemaespecialista.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "respostas")
@Entity
public class RespostaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resposta;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuario;

    @ManyToOne
    @JoinColumn(name = "pergunta_id", nullable = false)
    private PerguntaEntity pergunta;
}
