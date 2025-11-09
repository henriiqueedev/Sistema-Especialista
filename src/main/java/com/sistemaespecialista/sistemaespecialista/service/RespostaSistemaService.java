package com.sistemaespecialista.sistemaespecialista.service;

import com.sistemaespecialista.sistemaespecialista.entities.RespostaSistemaEntity;
import com.sistemaespecialista.sistemaespecialista.entities.UserEntity;
import com.sistemaespecialista.sistemaespecialista.repository.RespostaSistemaRepository;
import com.sistemaespecialista.sistemaespecialista.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespostaSistemaService {

    private final RespostaSistemaRepository respostaSistemaRepository;
    private final UserRepository userRepository;

    public RespostaSistemaService(RespostaSistemaRepository respostaSistemaRepository, UserRepository userRepository) {
        this.respostaSistemaRepository = respostaSistemaRepository;
        this.userRepository = userRepository;
    }

    public RespostaSistemaEntity gerarRespostaSistema(Long usuarioId) {
        try {
            Optional<UserEntity> optionalUser = userRepository.findById(usuarioId);
            if (optionalUser.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado!");
            }

            UserEntity user = optionalUser.get();

            float salario = user.getSalario();
            float gastoFixo = user.getGastoFixo();
            float valorDisponivel = salario - gastoFixo;

            if (valorDisponivel <= 0) {
                throw new RuntimeException("O usuário não possui saldo disponível após gastos fixos.");
            }

            RespostaSistemaEntity respostaSistema = new RespostaSistemaEntity();
            respostaSistema.setUsuario(user);
            respostaSistema.setValorGastosFixos(gastoFixo);

            float divida = 0;
            float reserva = 0;
            float investimento = 0;
            float objetivo = 0;

            switch (user.getPerfil().toLowerCase()) {
                case "conservador":
                    divida = valorDisponivel * 0.40f;
                    reserva = valorDisponivel * 0.30f;
                    investimento = valorDisponivel * 0.20f;
                    objetivo = valorDisponivel * 0.10f;
                    break;

                case "moderado":
                    divida = valorDisponivel * 0.30f;
                    reserva = valorDisponivel * 0.25f;
                    investimento = valorDisponivel * 0.30f;
                    objetivo = valorDisponivel * 0.15f;
                    break;

                case "arrojado":
                    divida = valorDisponivel * 0.20f;
                    reserva = valorDisponivel * 0.20f;
                    investimento = valorDisponivel * 0.45f;
                    objetivo = valorDisponivel * 0.15f;
                    break;

                default:
                    throw new RuntimeException("Perfil inválido: " + user.getPerfil());
            }

            respostaSistema.setValorQuitacaoDividas(roundTwoDecimals(divida));
            respostaSistema.setValorReservaEmergencia(roundTwoDecimals(reserva));
            respostaSistema.setValorInvestimentos(roundTwoDecimals(investimento));
            respostaSistema.setValorObjetivo(roundTwoDecimals(objetivo));

            return respostaSistemaRepository.save(respostaSistema);

        } catch (Exception e) {
            System.out.println("Erro ao gerar respostas do sistema: " + e.getMessage());
            return null;
        }
    }
    private float roundTwoDecimals(float value) {
        return Math.round(value * 100.0f) / 100.0f;
    }
}
