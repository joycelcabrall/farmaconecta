package br.com.farmaconecta.interfaces.rest.controller;

import br.com.farmaconecta.application.dto.AtualizarUbsRequest;
import br.com.farmaconecta.application.dto.CadastrarUbsRequest;
import br.com.farmaconecta.application.dto.UbsResponse;

import br.com.farmaconecta.application.usecase.AtualizarUbsUseCase;
import br.com.farmaconecta.application.usecase.BuscarUbsPorIdUseCase;
import br.com.farmaconecta.application.usecase.CadastrarUbsUseCase;
import br.com.farmaconecta.application.usecase.ListarUbsUseCase;

import br.com.farmaconecta.domain.model.Ubs;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ubs")
public class UbsController {

    private final CadastrarUbsUseCase cadastrarUbsUseCase;
    private final ListarUbsUseCase listarUbsUseCase;
    private final BuscarUbsPorIdUseCase buscarUbsPorIdUseCase;
    private final AtualizarUbsUseCase atualizarUbsUseCase;

    public UbsController(
            CadastrarUbsUseCase cadastrarUbsUseCase,
            ListarUbsUseCase listarUbsUseCase,
            BuscarUbsPorIdUseCase buscarUbsPorIdUseCase,
            AtualizarUbsUseCase atualizarUbsUseCase
    ) {
        this.cadastrarUbsUseCase = cadastrarUbsUseCase;
        this.listarUbsUseCase = listarUbsUseCase;
        this.buscarUbsPorIdUseCase = buscarUbsPorIdUseCase;
        this.atualizarUbsUseCase = atualizarUbsUseCase;
    }

    @PostMapping
    public ResponseEntity<UbsResponse> cadastrar(
            @Valid @RequestBody CadastrarUbsRequest request
    ) {

        Ubs ubs = cadastrarUbsUseCase.executar(
                request.nome(),
                request.cnes(),
                request.endereco(),
                request.cidade(),
                request.uf()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UbsResponse.de(ubs));
    }

    @GetMapping
    public ResponseEntity<List<UbsResponse>> listar() {

        List<UbsResponse> resposta = listarUbsUseCase
                .executar()
                .stream()
                .map(UbsResponse::de)
                .toList();

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbsResponse> buscarPorId(
            @PathVariable Long id
    ) {

        return buscarUbsPorIdUseCase
                .executar(id)
                .map(ubs -> ResponseEntity.ok(UbsResponse.de(ubs)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbsResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarUbsRequest request
    ) {

        return atualizarUbsUseCase
                .executar(
                        id,
                        request.nome(),
                        request.endereco(),
                        request.cidade(),
                        request.uf()
                )
                .map(ubs -> ResponseEntity.ok(UbsResponse.de(ubs)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}