package com.sistemaespecialista.sistemaespecialista.service;


import com.sistemaespecialista.sistemaespecialista.entities.RespostaEntity;
import com.sistemaespecialista.sistemaespecialista.entities.UserEntity;
import com.sistemaespecialista.sistemaespecialista.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity salvarUsuarioComRespostas(UserEntity user) {
        try {
            if (user.getRespostas() != null) {
                for (RespostaEntity resposta : user.getRespostas()) {
                    resposta.setUsuario(user);
                }
            }

            return userRepository.save(user);

        } catch (Exception e) {
            System.out.println("Erro ao salvar o usuario e respostas: " + e.getMessage());
            throw e;
        }
    }
}