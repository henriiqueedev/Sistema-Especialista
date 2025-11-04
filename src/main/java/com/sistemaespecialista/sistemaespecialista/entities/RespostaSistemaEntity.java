package com.sistemaespecialista.sistemaespecialista.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "respostas_sistema")
public class RespostaSistemaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;

    private float percentualGastosFixos;
    private float percentualInvestimentos;
    private float percentualQuitacaoDividas;
    private float percentualReservaEmergencia;
    private float percentualObjetivo;
}
