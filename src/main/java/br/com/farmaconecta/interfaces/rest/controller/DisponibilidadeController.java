package br.com.farmaconecta.interfaces.rest.controller;

import br.com.farmaconecta.application.dto.DisponibilidadeResponse;
import br.com.farmaconecta.application.usecase.ConsultarDisponibilidadeUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidade")
public class DisponibilidadeController {

    private final ConsultarDisponibilidadeUseCase consultarDisponibilidadeUseCase;

    public DisponibilidadeController(
            ConsultarDisponibilidadeUseCase consultarDisponibilidadeUseCase
    ) {
        this.consultarDisponibilidadeUseCase = consultarDisponibilidadeUseCase;
    }

    @GetMapping("/medicamentos/{medicamentoId}")
    public ResponseEntity<List<DisponibilidadeResponse>> consultarPorMedicamento(
            @PathVariable Long medicamentoId
    ) {

        List<DisponibilidadeResponse> resposta =
                consultarDisponibilidadeUseCase.executar(medicamentoId);

        return ResponseEntity.ok(resposta);
    }
}