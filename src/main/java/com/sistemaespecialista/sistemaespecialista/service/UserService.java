package com.sistemaespecialista.sistemaespecialista.service;


import com.sistemaespecialista.sistemaespecialista.entities.RespostaEntity;
import com.sistemaespecialista.sistemaespecialista.entities.UserEntity;
import com.sistemaespecialista.sistemaespecialista.repository.RespostaRepository;
import com.sistemaespecialista.sistemaespecialista.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private RespostaRepository respostaRepository;

    public UserService(UserRepository userRepository, RespostaRepository respostaRepository) {
        this.userRepository = userRepository;
        this.respostaRepository = respostaRepository;
    }

    public UserEntity salvarUsuarioComRespostas(UserEntity user, List<RespostaEntity> respostas){

        try{
            UserEntity userSalvo = userRepository.save(user);

            for (RespostaEntity resposta : respostas) {
                resposta.setUsuario(userSalvo);
                respostaRepository.save(resposta);
            }
            return userSalvo;
        }catch (Exception e){
            System.out.println("Erro ao salvar o usuario e respostas:" + e.getMessage());
            return null;
        }
    }
}
