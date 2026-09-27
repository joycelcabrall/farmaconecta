package br.com.farmaconecta.interfaces.rest.controller;

import br.com.farmaconecta.application.dto.CadastrarEstoqueRequest;
import br.com.farmaconecta.application.dto.EstoqueResponse;
import br.com.farmaconecta.application.dto.MovimentarEstoqueRequest;

import br.com.farmaconecta.application.usecase.CadastrarEstoqueUseCase;
import br.com.farmaconecta.application.usecase.ListarEstoquePorUbsUseCase;
import br.com.farmaconecta.application.usecase.RegistrarEntradaEstoqueUseCase;
import br.com.farmaconecta.application.usecase.RegistrarSaidaEstoqueUseCase;

import br.com.farmaconecta.domain.model.Estoque;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    private final CadastrarEstoqueUseCase cadastrarEstoqueUseCase;
    private final ListarEstoquePorUbsUseCase listarEstoquePorUbsUseCase;
    private final RegistrarEntradaEstoqueUseCase registrarEntradaEstoqueUseCase;
    private final RegistrarSaidaEstoqueUseCase registrarSaidaEstoqueUseCase;

    public EstoqueController(
            CadastrarEstoqueUseCase cadastrarEstoqueUseCase,
            ListarEstoquePorUbsUseCase listarEstoquePorUbsUseCase,
            RegistrarEntradaEstoqueUseCase registrarEntradaEstoqueUseCase,
            RegistrarSaidaEstoqueUseCase registrarSaidaEstoqueUseCase
    ) {
        this.cadastrarEstoqueUseCase = cadastrarEstoqueUseCase;
        this.listarEstoquePorUbsUseCase = listarEstoquePorUbsUseCase;
        this.registrarEntradaEstoqueUseCase = registrarEntradaEstoqueUseCase;
        this.registrarSaidaEstoqueUseCase = registrarSaidaEstoqueUseCase;
    }

    @PostMapping
    public ResponseEntity<EstoqueResponse> cadastrar(
            @Valid @RequestBody CadastrarEstoqueRequest request
    ) {

        Estoque estoque = cadastrarEstoqueUseCase.executar(
                request.ubsId(),
                request.medicamentoId(),
                request.lote(),
                request.validade(),
                request.quantidade()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(EstoqueResponse.de(estoque));
    }

    @GetMapping("/ubs/{ubsId}")
    public ResponseEntity<List<EstoqueResponse>> listarPorUbs(
            @PathVariable Long ubsId
    ) {

        List<EstoqueResponse> resposta =
                listarEstoquePorUbsUseCase.executar(ubsId)
                        .stream()
                        .map(EstoqueResponse::de)
                        .toList();

        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/{id}/entrada")
    public ResponseEntity<EstoqueResponse> registrarEntrada(
            @PathVariable Long id,
            @Valid @RequestBody MovimentarEstoqueRequest request
    ) {

        Estoque estoque = registrarEntradaEstoqueUseCase.executar(
                id,
                request.quantidade()
        );

        return ResponseEntity.ok(EstoqueResponse.de(estoque));
    }

    @PostMapping("/{id}/saida")
    public ResponseEntity<EstoqueResponse> registrarSaida(
            @PathVariable Long id,
            @Valid @RequestBody MovimentarEstoqueRequest request
    ) {

        Estoque estoque = registrarSaidaEstoqueUseCase.executar(
                id,
                request.quantidade()
        );

        return ResponseEntity.ok(EstoqueResponse.de(estoque));
    }
}