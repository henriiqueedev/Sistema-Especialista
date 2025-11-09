package com.sistemaespecialista.sistemaespecialista.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float salario;
    private float gastoFixo;

    private String perfil;
    private int filhos;
    private float divida;
    private float reserva;
    private String objetivo;
}