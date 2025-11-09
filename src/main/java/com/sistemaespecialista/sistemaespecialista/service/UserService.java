package com.sistemaespecialista.sistemaespecialista.service;


import com.sistemaespecialista.sistemaespecialista.entities.UserEntity;
import com.sistemaespecialista.sistemaespecialista.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity salvarUsuario(UserEntity user) {
        try {
            return userRepository.save(user);

        } catch (Exception e) {
            System.out.println("Erro ao salvar o usuario: " + e.getMessage());
            throw e;
        }
    }
}