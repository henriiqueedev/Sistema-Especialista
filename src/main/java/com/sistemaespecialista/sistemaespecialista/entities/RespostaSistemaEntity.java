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

    @Column(name = "valor_gastos_fixos")
    private float valorGastosFixos;

    @Column(name = "valor_investimentos")
    private float valorInvestimentos;

    @Column(name = "valor_quitacao_dividas")
    private float valorQuitacaoDividas;

    @Column(name = "valor_reserva_emergencia")
    private float valorReservaEmergencia;

    @Column(name = "valor_objetivo")
    private float valorObjetivo;
}
