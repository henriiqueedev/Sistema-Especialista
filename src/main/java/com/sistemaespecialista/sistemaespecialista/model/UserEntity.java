package com.sistemaespecialista.sistemaespecialista.model;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float salario;

    private String perfil;

    private int filhos;

    private float divida;

    private float reserva;

    private String objetivo;

}