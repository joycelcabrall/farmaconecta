package br.com.farmaconecta.interfaces.rest.controller;

import br.com.farmaconecta.application.dto.AtualizarMedicamentoRequest;
import br.com.farmaconecta.application.dto.CadastrarMedicamentoRequest;
import br.com.farmaconecta.application.dto.MedicamentoResponse;

import br.com.farmaconecta.application.usecase.AtualizarMedicamentoUseCase;
import br.com.farmaconecta.application.usecase.BuscarMedicamentoPorIdUseCase;
import br.com.farmaconecta.application.usecase.CadastrarMedicamentoUseCase;
import br.com.farmaconecta.application.usecase.ListarMedicamentosUseCase;

import br.com.farmaconecta.domain.model.Medicamento;

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
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final CadastrarMedicamentoUseCase cadastrarMedicamentoUseCase;
    private final ListarMedicamentosUseCase listarMedicamentosUseCase;
    private final BuscarMedicamentoPorIdUseCase buscarMedicamentoPorIdUseCase;
    private final AtualizarMedicamentoUseCase atualizarMedicamentoUseCase;

    public MedicamentoController(
            CadastrarMedicamentoUseCase cadastrarMedicamentoUseCase,
            ListarMedicamentosUseCase listarMedicamentosUseCase,
            BuscarMedicamentoPorIdUseCase buscarMedicamentoPorIdUseCase,
            AtualizarMedicamentoUseCase atualizarMedicamentoUseCase
    ) {
        this.cadastrarMedicamentoUseCase = cadastrarMedicamentoUseCase;
        this.listarMedicamentosUseCase = listarMedicamentosUseCase;
        this.buscarMedicamentoPorIdUseCase = buscarMedicamentoPorIdUseCase;
        this.atualizarMedicamentoUseCase = atualizarMedicamentoUseCase;
    }

    @PostMapping
    public ResponseEntity<MedicamentoResponse> cadastrar(
            @Valid @RequestBody CadastrarMedicamentoRequest request
    ) {

        Medicamento medicamento = cadastrarMedicamentoUseCase.executar(
                request.nome(),
                request.principioAtivo(),
                request.concentracao(),
                request.formaFarmaceutica(),
                request.unidadeMedida()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MedicamentoResponse.de(medicamento));
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoResponse>> listar() {

        List<MedicamentoResponse> resposta = listarMedicamentosUseCase
                .executar()
                .stream()
                .map(MedicamentoResponse::de)
                .toList();

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> buscarPorId(
            @PathVariable Long id
    ) {

        return buscarMedicamentoPorIdUseCase
                .executar(id)
                .map(medicamento ->
                        ResponseEntity.ok(
                                MedicamentoResponse.de(medicamento)
                        )
                )
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarMedicamentoRequest request
    ) {

        return atualizarMedicamentoUseCase
                .executar(
                        id,
                        request.nome(),
                        request.principioAtivo(),
                        request.concentracao(),
                        request.formaFarmaceutica(),
                        request.unidadeMedida()
                )
                .map(medicamento ->
                        ResponseEntity.ok(
                                MedicamentoResponse.de(medicamento)
                        )
                )
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }
}