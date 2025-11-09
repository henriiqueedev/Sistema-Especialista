package com.sistemaespecialista.sistemaespecialista.controller;

import com.sistemaespecialista.sistemaespecialista.entities.RespostaSistemaEntity;
import com.sistemaespecialista.sistemaespecialista.service.RespostaSistemaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas-sistema")
public class RespostaSistemaController {

    private final RespostaSistemaService respostaSistemaService;

    public RespostaSistemaController(RespostaSistemaService respostaSistemaService) {
        this.respostaSistemaService = respostaSistemaService;
    }

    @PostMapping("/gerar/{usuarioId}")
    public RespostaSistemaEntity gerarRespostaSistema(@PathVariable Long usuarioId) {
        try {
            return respostaSistemaService.gerarRespostaSistema(usuarioId);
        } catch (Exception e) {
            System.out.println("Erro ao gerar resposta do sistema: " + e.getMessage());
            return null;
        }
    }
}
