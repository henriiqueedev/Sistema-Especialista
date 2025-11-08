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
            // 1. Buscar usuário
            Optional<UserEntity> optionalUser = userRepository.findById(usuarioId);
            if (optionalUser.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado!");
            }

            UserEntity user = optionalUser.get();

            // 2. Calcular valor disponível após gasto fixo
            float valorDisponivel = user.getSalario() - user.getGastoFixo();
            if (valorDisponivel <= 0) {
                throw new RuntimeException("O usuário não possui saldo disponível após gastos fixos.");
            }

            // 3. Criar entidade de resposta do sistema
            RespostaSistemaEntity respostaSistema = new RespostaSistemaEntity();
            respostaSistema.setUsuario(user);

            float divida = 0;
            float reserva = 0;
            float investimento = 0;
            float objetivo = 0;

            // 4. Aplicar regras conforme o perfil
            switch (user.getPerfil().toLowerCase()) {
                case "conservador":
                    divida = valorDisponivel * 0.60f;
                    reserva = valorDisponivel * 0.30f;
                    investimento = valorDisponivel * 0.10f;
                    break;
                case "moderado":
                    divida = valorDisponivel * 0.40f;
                    reserva = valorDisponivel * 0.30f;
                    investimento = valorDisponivel * 0.20f;
                    objetivo = valorDisponivel * 0.10f;
                    break;
                case "agressivo":
                    divida = valorDisponivel * 0.20f;
                    reserva = valorDisponivel * 0.20f;
                    investimento = valorDisponivel * 0.50f;
                    objetivo = valorDisponivel * 0.10f;
                    break;
                default:
                    throw new RuntimeException("Perfil inválido: " + user.getPerfil());
            }

            // 5. Definir valores calculados
            respostaSistema.setPercentualQuitacaoDividas(divida);
            respostaSistema.setPercentualReservaEmergencia(reserva);
            respostaSistema.setPercentualInvestimentos(investimento);
            respostaSistema.setPercentualObjetivo(objetivo);

            // 6. Salvar no banco
            return respostaSistemaRepository.save(respostaSistema);

        } catch (Exception e) {
            System.out.println("Erro ao gerar respostas do sistema: " + e.getMessage());
            throw e;
        }
    }
}
