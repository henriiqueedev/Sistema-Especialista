package com.sistemaespecialista.sistemaespecialista.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perguntas")
@Entity
public class PerguntaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pergunta; // o texto da pergunta

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL)
    private List<RespostaEntity> respostas; // todas as respostas associadas a esta pergunta
}
