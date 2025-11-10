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
            float reservaAtual = user.getReserva();
            float dividaAtual = user.getDivida();
            String objetivoFinanceiro = user.getObjetivo();

            float valorDisponivel = salario - gastoFixo;
            if (valorDisponivel <= 0) {
                throw new RuntimeException("O usuário não possui saldo disponível após gastos fixos.");
            }

            RespostaSistemaEntity respostaSistema = new RespostaSistemaEntity();
            respostaSistema.setUsuario(user);
            respostaSistema.setValorGastosFixos(gastoFixo);

            float divida = 0, reserva = 0, investimento = 0, objetivo = 0;
            StringBuilder diagnostico = new StringBuilder("Análise do sistema: ");

            switch (user.getPerfil().toLowerCase()) {
                case "conservador":
                    divida = 0.40f;
                    reserva = 0.30f;
                    investimento = 0.20f;
                    objetivo = 0.10f;
                    break;
                case "moderado":
                    divida = 0.30f;
                    reserva = 0.25f;
                    investimento = 0.30f;
                    objetivo = 0.15f;
                    break;
                case "arrojado":
                    divida = 0.20f;
                    reserva = 0.20f;
                    investimento = 0.45f;
                    objetivo = 0.15f;
                    break;
                default:
                    throw new RuntimeException("Perfil inválido: " + user.getPerfil());
            }

            float reservaNecessaria = gastoFixo * 6;
            if (reservaAtual >= reservaNecessaria) {
                diagnostico.append("Reserva de emergência completa. ");
                float redistribuir = reserva;
                reserva = 0;
                float total = divida + investimento + objetivo;
                divida += (divida / total) * redistribuir;
                investimento += (investimento / total) * redistribuir;
                objetivo += (objetivo / total) * redistribuir;
            }

            if (objetivoFinanceiro == null || objetivoFinanceiro.trim().isEmpty()) {
                diagnostico.append("Sem objetivo financeiro definido. ");
                float redistribuir = objetivo;
                objetivo = 0;
                float total = divida + investimento + reserva;
                divida += (divida / total) * redistribuir;
                investimento += (investimento / total) * redistribuir;
                reserva += (reserva / total) * redistribuir;
            }

            if (dividaAtual <= 0) {
                diagnostico.append("Sem dívidas registradas. ");
                float redistribuir = divida;
                divida = 0;
                float total = reserva + investimento + objetivo;
                reserva += (reserva / total) * redistribuir;
                investimento += (investimento / total) * redistribuir;
                objetivo += (objetivo / total) * redistribuir;
            }

            respostaSistema.setValorQuitacaoDividas(roundTwoDecimals(valorDisponivel * divida));
            respostaSistema.setValorReservaEmergencia(roundTwoDecimals(valorDisponivel * reserva));
            respostaSistema.setValorInvestimentos(roundTwoDecimals(valorDisponivel * investimento));
            respostaSistema.setValorObjetivo(roundTwoDecimals(valorDisponivel * objetivo));

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
