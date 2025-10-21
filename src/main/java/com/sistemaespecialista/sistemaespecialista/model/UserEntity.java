package com.sistemaespecialista.sistemaespecialista.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private float salario;

    @NotNull
    private String perfil;

    private int filhos;

    private float divida;
    
    private float reserva;

    private String objetivo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<RespostaEntity> respostas;


}